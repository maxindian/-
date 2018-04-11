package com.wy.servlet;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import com.wy.java.*;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.ProgressListener;


public class Manager_QAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String Ext_Name = "java,m,py,pdf,gif,jpg,jpeg,png,bmp,swf,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		String Stu_name=request.getParameter("Stu_name");
		String Stu_num=request.getParameter("Stu_num");
		String Stu_class_name=request.getParameter("Stu_class");
		String Ir_ID_Str=request.getParameter("Ir_ID");
		String Stu_ID_Str=request.getParameter("Stu_ID");
		HttpSession sess=request.getSession();
		
		DBHandle dbh=new DBHandle();
		
		String pageNowStr=request.getParameter("pageNow");
		//String title = request.getParameter("title");
		String title = "";
		//System.out.println("pageNow:"+pageNowStr);
		String dir = "";
		
		String roleStr = request.getParameter("signal");
		int role = Integer.parseInt(roleStr);
	    //System.out.println("role:"+role);
	    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Date date = new Date();
		String sql="";
		String sql1= "";
		if(flag.equals("add")){
			String savePath = this.getServletContext().getRealPath("wenda/upload");// 得到上传文件的保存目录，将上传文件存放在WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			//System.out.println("savePath:"+savePath);
			dir = savePath;
			File saveFileDir = new File(savePath);
			//System.out.println(savePath);
			if (!saveFileDir.exists()) {
				// 创建临时目录
				saveFileDir.mkdirs();
			         }
				// 上传时生成临时文件保存目录
			String tmpPath = this.getServletContext().getRealPath("wenda/tem");
			File tmpFile = new File(tmpPath);
			if (!tmpFile.exists()) {
				 // 创建临时目录
				tmpFile.mkdirs();
			        }
			String message = "";
			try {
		      // 使用Apache文件上传组件处理文件上传步骤：
		      // 1.创建一个DiskFileItemFactory工厂
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中
		      factory.setSizeThreshold(1024 * 10);// 设置缓冲区的大小为100KB，如果不指定，那么默认缓冲区的大小是10KB
		      // 设置上传时生成的临时文件的保存目录
		      factory.setRepository(tmpFile);
		      // 2.创建一个文件上传解析器
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // 监听文件上传进度
		      upload.setProgressListener(new ProgressListener(){
		       @Override
		       public void update(long readedBytes, long totalBytes, int currentItem) {
		           // TODO Auto-generated method stub
		           System.out.println("当前已处理：" + readedBytes + "-----------文件大小为：" + totalBytes + "--" + currentItem);
		               }
		             });
		      // 解决上传文件名的中文乱码问题
	          upload.setHeaderEncoding("UTF-8");
	          // 3.判断提交上来的数据是否是上传表单的数据
	          if (!ServletFileUpload.isMultipartContent(request)) {
	               // 按照传统方式获取数据
	               return;
	               }
	          // 设置上传单个文件的最大值
	          upload.setFileSizeMax(1024 * 1024 * 100);// 1M
	          // 设置上传文件总量的最大值，就是本次上传的所有文件的总和的最大值
	          upload.setSizeMax(1024 * 1024 *300 );// 10M
	          List items = upload.parseRequest(request);
	          Iterator itr = items.iterator();
	          while (itr.hasNext()) {
	                 FileItem item = (FileItem) itr.next();
	                 // 如果fileitem中封装的是普通的输入想数据
	                  if (item.isFormField()) {
	                        String name = item.getFieldName();
	                        // 解决普通输入项数据中文乱码问题
	                        String value = item.getString("UTF-8");
	                        // value = new String(value.getBytes("iso8859-1"),"UTF-8")
	                        title = value;
	                   } else{// 如果fileitem中封装的是上传文件
	                          // 得到上传文件的文件名
	                         String fileName = item.getName();
	                         System.out.println("文件名：" + fileName);
	                         
	                         if (fileName == null && fileName.trim().length() == 0) {
	                           continue;
	                           }
                             // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的
                             // 如: C:\Users\H__D\Desktop\1.txt 而有些则是 ： 1.txt
                             // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
	                         //dir += "\\"+fileName;
	                         fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	                         //dir =  fileName;           
	                         // 得到上传文件的扩展名
	                         String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	                         // 检查扩展名
                             // 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
	                         System.out.println("上传的文件的扩展名是：" + fileExt);
	                         if(!Ext_Name.contains(fileExt)){
	                        	 System.out.println("上传文件扩展名是不允许的扩展名：" + fileExt);
	                        	 message = message + "文件：" + fileName + "，上传文件扩展名是不允许的扩展名：" + fileExt + "<br/>";
	                        	 break;
	                                     }
	                 
	                         // 检查文件大小
	                          if(item.getSize() == 0) continue;
	                          if(item.getSize() > 1024 * 1024 * 300){
	                          System.out.println("上传文件大小：" + item.getSize());
	                          message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax() + "<br/>";
	                          break;}                     
                             // 得到存文件的文件名
                             String saveFileName = makeFileName(fileName);
	                         //String saveFileName = fileName;
                             dir += "\\"+saveFileName;
                             //保存文件方法一// 获取item中的上传文件的输入流
                             InputStream is = item.getInputStream();
                             //创建一个文件输出流
                             FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
                             //创建一个缓冲区
                            
                             byte buffer[] = new byte[1024];
                             //判断输入流中的数据是否已经读完的标致
                             int len = 0;
                             while((len = is.read(buffer)) > 0){
                                 out.write(buffer, 0, len);}
                             //关闭输出流
                             out.close();
                             //关闭输入流
                             is.close();
                             //删除临时文件
                             item.delete();
                             if(title.length() == 0){      
                  
                     	        title =  fileName.substring(fileName.indexOf("_") + 1);
                     	      }
                            // System.out.println(title);
                             message = message + "文件：" + fileName + "，上传成功<br/>"; 
                             
                             //File uploadedFile = new File(savePath, saveFileName);                             
                             //item.write(uploadedFile);
                             
	                       }//else
	                 
	                 }//while
	                             
	           } //try
				catch (FileSizeLimitExceededException e) {
	                  message = message + "上传文件大小超过限制<br/>";
	                  e.printStackTrace();
	           } catch (Exception e) {
	                             // TODO Auto-generated catch block
	                  e.printStackTrace();
	            } finally {
	                  request.setAttribute("message", message);
	                                   
	            }			
			 String zhan_dir ="";			 
			 for( int i=0;i<dir.length();i++){
				 char c=dir.charAt(i);
				 if (c == '\\'){
					 zhan_dir += "\\"+"\\";
				 }else{
					 zhan_dir += c;
				 }		 				 
			 }
			 //System.out.println("dir:"+dir);
			 //System.out.println("zhan_dir:"+zhan_dir);
			 sql1="select max(ID) from qa_source ";
			 int maxId = dbh.getFlag(sql1);
			 sql="insert into qa_source (ID,titleName,directory,SubT) values("+(maxId+1)+",'"+title+"','"+zhan_dir+"','"+df.format(date.getTime())+"')";
			 if(dbh.Update(sql)){
				 if(role==731){
					 response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=731");}
				 else if(role==0){response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=0");}
			}else{
				 if(role==731){
					 response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=731");}
				 else if(role==0){response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=0");}
			}
		}else if(flag.equals("delete")){
			
			int Ir_ID=Integer.parseInt(Ir_ID_Str);
			ArrayList<qa_list> al=new ArrayList<qa_list>();
			String sql10 ="select * from qa_source where ID='"+Ir_ID+"'";
		  	al= dbh.getQa_list(sql10);
		  	//System.out.println("===========");
		  	String dir1 = al.get(0).getDirecter();
		  	//System.out.println("+++++++++");
		  	File f=new File(dir1);  
		  	f.delete();  
		  	
			sql="delete from qa_source where ID='"+Ir_ID+"'";
			sql1 = "update qa_source set ID=ID-1 where ID>'"+Ir_ID+"' ";
			//System.out.println("===========10");
			if((dbh.Update(sql)&&dbh.Update(sql1))){
				if(role==731){
				response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=731");}
				else if(role==0){response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=0");}
			}else{
				if(role==731){
					response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=731");}
				else if(role==0){response.sendRedirect("ManagerServlet?flag=wenda&pageNow=1&signal=0");}
			}
		}else if(flag.equals("download")){
			int Ir_ID=Integer.parseInt(Ir_ID_Str);
			//String fileName = request.getParameter("filename"); // 2323928392489-美人鱼.avi
			//fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
			 // 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
			sql="select * from qa_source where ID='"+Ir_ID+"'";
			ArrayList<qa_list> al=new ArrayList<qa_list>();
//			al=dbh.getIr_list("select * from ir_source limit "+pageSize*(pageNow-1)+","+pageSize);
		  	al= dbh.getQa_list(sql);
		  	String dir1 = al.get(0).getDirecter();
		  	String fileName = dir1.substring(dir1.lastIndexOf("\\") + 1);
			File file = new File(dir1);
			// 如果文件不存在
			if (!file.exists()) {
			            request.setAttribute("message", "您要下载的资源已被删除！！");
			          request.getRequestDispatcher("/message.jsp").forward(request, response);
			          return;
			       }
			         // 处理文件名
			    String realname = fileName.substring(fileName.indexOf("_") + 1);
			       // 设置响应头，控制浏览器下载该文件
			    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		        // 读取要下载的文件，保存到文件输入流
		         FileInputStream in = new FileInputStream(dir1);
		         // 创建输出流
			     OutputStream out = response.getOutputStream();
		         // 创建缓冲区
			         byte buffer[] = new byte[1024];
			         int len = 0;
			        // 循环将输入流中的内容读取到缓冲区当中
			        while ((len = in.read(buffer)) > 0) {
			             // 输出缓冲区的内容到浏览器，实现文件下载
			             out.write(buffer, 0, len);
			         }
			        // 关闭文件输入流
			         in.close();
			         // 关闭输出流
			        out.close();				
		}
	}
	
	
private String makeFileName(String fileName) {
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
   return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

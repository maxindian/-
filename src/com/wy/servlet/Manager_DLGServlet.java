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

public class Manager_DLGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String Ext_Name = "pdf,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,jsp,java";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		String Ir_ID_Str=request.getParameter("Ir_ID");
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String pageNowStr=request.getParameter("pageNow");
		String title = "";
		String dir = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sql="";
		String sql1= "";
		String roleStr = request.getParameter("signal");
		int role = Integer.parseInt(roleStr);
		
		if(flag.equals("add")){
			String savePath = this.getServletContext().getRealPath("duihua/upload");
			dir = savePath;
			File saveFileDir = new File(savePath);
			if (!saveFileDir.exists()) {
				saveFileDir.mkdirs();
			         }
			String tmpPath = this.getServletContext().getRealPath("duihua/tem");
			File tmpFile = new File(tmpPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdirs();
			        }
			String message = "";
			try {

		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      factory.setSizeThreshold(1024 * 40);
		      factory.setRepository(tmpFile);
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      upload.setProgressListener(new ProgressListener(){
		      @Override
		      public void update(long readedBytes, long totalBytes, int currentItem) {
		       
		           //System.out.println("当前已处理：" + readedBytes + "-----------文件大小为：" + totalBytes + "--" + currentItem);
		               }
		             });
		      
	          upload.setHeaderEncoding("UTF-8");
	          if (!ServletFileUpload.isMultipartContent(request)) {
	               return;
	               }
	          upload.setFileSizeMax(1024 * 1024 * 100);// 1M
	          upload.setSizeMax(1024 * 1024 *300 );// 10M
	          List items = upload.parseRequest(request);
	          Iterator itr = items.iterator();
	          while (itr.hasNext()) {
	                 FileItem item = (FileItem) itr.next();
	                  if (item.isFormField()) {
	                        String name = item.getFieldName();
	                        String value = item.getString("UTF-8");
	                        title = value;
	                   } else{
	                         String fileName = item.getName();  
	                         if (fileName == null && fileName.trim().length() == 0) {
	                           continue;
	                           }                        
	                         
	                         fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	                         String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();	                       
	                         if(!Ext_Name.contains(fileExt)){
	                        	 message = message + "文件：" + fileName + "，上传文件扩展名是不允许的扩展名：" + fileExt + "<br/>";
	                        	 break;
	                                     }
	                          if(item.getSize() == 0) continue;
	                          if(item.getSize() > 1024 * 1024 * 300){
	                          //System.out.println("上传文件大小：" + item.getSize());
	                          message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax() + "<br/>";
	                          break;}  
	                          String saveFileName = makeFileName(fileName);
	                          dir += "\\"+saveFileName;
	                         // String saveFileName = fileName;
                             //保存文件方法一// 获取item中的上传文件的输入流
                             InputStream is = item.getInputStream();
                             FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
                             byte buffer[] = new byte[1024];
                             int len = 0;
                             while((len = is.read(buffer)) > 0){
                                 out.write(buffer, 0, len);}
                             out.close();      
                             is.close();    
                             item.delete();
                             if(title.length() == 0){             
                            	 title =  fileName.substring(fileName.indexOf("_") + 1);
                     	      }
                             message = message + "文件：" + fileName + "，上传成功<br/>"; 
	                       }//else
	                 }//while               
	           } //try
				catch (FileSizeLimitExceededException e) {
	                  message = message + "上传文件大小超过限制<br/>";
	                  e.printStackTrace();
	           } catch (Exception e) {      
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
			 sql1="select max(ID) from gen_source ";
			 int maxId = dbh.getFlag(sql1);
			 sql="insert into gen_source (ID,titleName,directory,SubT) values("+(maxId+1)+",'"+title+"','"+zhan_dir+"','"+df.format(date.getTime())+"')";
			 if(dbh.Update(sql)){
				 if(role==731){
					 response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=731");}
				 else if(role==0){ response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=0");}
			 }else{
				 if(role==731){
					 response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=731");}
				 else if(role==0){ response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=0");}
			}
		}else if(flag.equals("delete")){
			
			int Ir_ID=Integer.parseInt(Ir_ID_Str);
			ArrayList<gen_list> al=new ArrayList<gen_list>();
			String sql10 ="select * from gen_source where ID='"+Ir_ID+"'";
		  	al= dbh.getGen_list(sql10);
		  	String dir1 = al.get(0).getDirecter();
		  	File f=new File(dir1);  
		  	f.delete();  
		  	
			sql="delete from gen_source where ID='"+Ir_ID+"'";
			sql1 = "update gen_source set ID=ID-1 where ID>'"+Ir_ID+"' ";
			if(dbh.Update(sql)&&dbh.Update(sql1)){
				if (role==731){
					response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=731");}
				else if(role==0){
					response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=0");
				}
			}else{if (role==731){
				response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=731");}
			else if(role==0){
				response.sendRedirect("ManagerServlet?flag=duihua&pageNow=1&signal=0");
			}}
		}else if(flag.equals("download")){
			int Ir_ID=Integer.parseInt(Ir_ID_Str);
			sql="select * from gen_source where ID='"+Ir_ID+"'";
			ArrayList<gen_list> al=new ArrayList<gen_list>();
		  	al= dbh.getGen_list(sql);
		  	String dir1 = al.get(0).getDirecter();
		  	String fileName = dir1.substring(dir1.lastIndexOf("\\") + 1);
			File file = new File(dir1);
			
			if (!file.exists()) {
			          request.setAttribute("message", "您要下载的资源已被删除！！");
			          request.getRequestDispatcher("/message.jsp").forward(request, response);
			          return;
			       }
			    String realname = fileName.substring(fileName.indexOf("_") + 1);
			    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		        FileInputStream in = new FileInputStream(dir1);
			    OutputStream out = response.getOutputStream();
			        byte buffer[] = new byte[1024];
			        int len = 0;
			        while ((len = in.read(buffer)) > 0) {	
			             out.write(buffer, 0, len);
			         }
			        in.close();		 
			        out.close();			
			
		}
}	
	private String makeFileName(String fileName) {
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
   return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

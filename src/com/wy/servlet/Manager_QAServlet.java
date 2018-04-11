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
	    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		Date date = new Date();
		String sql="";
		String sql1= "";
		if(flag.equals("add")){
			String savePath = this.getServletContext().getRealPath("wenda/upload");// �õ��ϴ��ļ��ı���Ŀ¼�����ϴ��ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
			//System.out.println("savePath:"+savePath);
			dir = savePath;
			File saveFileDir = new File(savePath);
			//System.out.println(savePath);
			if (!saveFileDir.exists()) {
				// ������ʱĿ¼
				saveFileDir.mkdirs();
			         }
				// �ϴ�ʱ������ʱ�ļ�����Ŀ¼
			String tmpPath = this.getServletContext().getRealPath("wenda/tem");
			File tmpFile = new File(tmpPath);
			if (!tmpFile.exists()) {
				 // ������ʱĿ¼
				tmpFile.mkdirs();
			        }
			String message = "";
			try {
		      // ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
		      // 1.����һ��DiskFileItemFactory����
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // ���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼����
		      factory.setSizeThreshold(1024 * 10);// ���û������Ĵ�СΪ100KB�������ָ������ôĬ�ϻ������Ĵ�С��10KB
		      // �����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
		      factory.setRepository(tmpFile);
		      // 2.����һ���ļ��ϴ�������
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // �����ļ��ϴ�����
		      upload.setProgressListener(new ProgressListener(){
		       @Override
		       public void update(long readedBytes, long totalBytes, int currentItem) {
		           // TODO Auto-generated method stub
		           System.out.println("��ǰ�Ѵ���" + readedBytes + "-----------�ļ���СΪ��" + totalBytes + "--" + currentItem);
		               }
		             });
		      // ����ϴ��ļ�����������������
	          upload.setHeaderEncoding("UTF-8");
	          // 3.�ж��ύ�����������Ƿ����ϴ���������
	          if (!ServletFileUpload.isMultipartContent(request)) {
	               // ���մ�ͳ��ʽ��ȡ����
	               return;
	               }
	          // �����ϴ������ļ������ֵ
	          upload.setFileSizeMax(1024 * 1024 * 100);// 1M
	          // �����ϴ��ļ����������ֵ�����Ǳ����ϴ��������ļ����ܺ͵����ֵ
	          upload.setSizeMax(1024 * 1024 *300 );// 10M
	          List items = upload.parseRequest(request);
	          Iterator itr = items.iterator();
	          while (itr.hasNext()) {
	                 FileItem item = (FileItem) itr.next();
	                 // ���fileitem�з�װ������ͨ������������
	                  if (item.isFormField()) {
	                        String name = item.getFieldName();
	                        // �����ͨ����������������������
	                        String value = item.getString("UTF-8");
	                        // value = new String(value.getBytes("iso8859-1"),"UTF-8")
	                        title = value;
	                   } else{// ���fileitem�з�װ�����ϴ��ļ�
	                          // �õ��ϴ��ļ����ļ���
	                         String fileName = item.getName();
	                         System.out.println("�ļ�����" + fileName);
	                         
	                         if (fileName == null && fileName.trim().length() == 0) {
	                           continue;
	                           }
                             // ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·����
                             // ��: C:\Users\H__D\Desktop\1.txt ����Щ���� �� 1.txt
                             // �����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
	                         //dir += "\\"+fileName;
	                         fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	                         //dir =  fileName;           
	                         // �õ��ϴ��ļ�����չ��
	                         String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	                         // �����չ��
                             // �����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
	                         System.out.println("�ϴ����ļ�����չ���ǣ�" + fileExt);
	                         if(!Ext_Name.contains(fileExt)){
	                        	 System.out.println("�ϴ��ļ���չ���ǲ��������չ����" + fileExt);
	                        	 message = message + "�ļ���" + fileName + "���ϴ��ļ���չ���ǲ��������չ����" + fileExt + "<br/>";
	                        	 break;
	                                     }
	                 
	                         // ����ļ���С
	                          if(item.getSize() == 0) continue;
	                          if(item.getSize() > 1024 * 1024 * 300){
	                          System.out.println("�ϴ��ļ���С��" + item.getSize());
	                          message = message + "�ļ���" + fileName + "���ϴ��ļ���С�������ƴ�С��" + upload.getFileSizeMax() + "<br/>";
	                          break;}                     
                             // �õ����ļ����ļ���
                             String saveFileName = makeFileName(fileName);
	                         //String saveFileName = fileName;
                             dir += "\\"+saveFileName;
                             //�����ļ�����һ// ��ȡitem�е��ϴ��ļ���������
                             InputStream is = item.getInputStream();
                             //����һ���ļ������
                             FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
                             //����һ��������
                            
                             byte buffer[] = new byte[1024];
                             //�ж��������е������Ƿ��Ѿ�����ı���
                             int len = 0;
                             while((len = is.read(buffer)) > 0){
                                 out.write(buffer, 0, len);}
                             //�ر������
                             out.close();
                             //�ر�������
                             is.close();
                             //ɾ����ʱ�ļ�
                             item.delete();
                             if(title.length() == 0){      
                  
                     	        title =  fileName.substring(fileName.indexOf("_") + 1);
                     	      }
                            // System.out.println(title);
                             message = message + "�ļ���" + fileName + "���ϴ��ɹ�<br/>"; 
                             
                             //File uploadedFile = new File(savePath, saveFileName);                             
                             //item.write(uploadedFile);
                             
	                       }//else
	                 
	                 }//while
	                             
	           } //try
				catch (FileSizeLimitExceededException e) {
	                  message = message + "�ϴ��ļ���С��������<br/>";
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
			//String fileName = request.getParameter("filename"); // 2323928392489-������.avi
			//fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
			 // �ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
			sql="select * from qa_source where ID='"+Ir_ID+"'";
			ArrayList<qa_list> al=new ArrayList<qa_list>();
//			al=dbh.getIr_list("select * from ir_source limit "+pageSize*(pageNow-1)+","+pageSize);
		  	al= dbh.getQa_list(sql);
		  	String dir1 = al.get(0).getDirecter();
		  	String fileName = dir1.substring(dir1.lastIndexOf("\\") + 1);
			File file = new File(dir1);
			// ����ļ�������
			if (!file.exists()) {
			            request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
			          request.getRequestDispatcher("/message.jsp").forward(request, response);
			          return;
			       }
			         // �����ļ���
			    String realname = fileName.substring(fileName.indexOf("_") + 1);
			       // ������Ӧͷ��������������ظ��ļ�
			    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		        // ��ȡҪ���ص��ļ������浽�ļ�������
		         FileInputStream in = new FileInputStream(dir1);
		         // ���������
			     OutputStream out = response.getOutputStream();
		         // ����������
			         byte buffer[] = new byte[1024];
			         int len = 0;
			        // ѭ�����������е����ݶ�ȡ������������
			        while ((len = in.read(buffer)) > 0) {
			             // ��������������ݵ��������ʵ���ļ�����
			             out.write(buffer, 0, len);
			         }
			        // �ر��ļ�������
			         in.close();
			         // �ر������
			        out.close();				
		}
	}
	
	
private String makeFileName(String fileName) {
		//Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
   return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

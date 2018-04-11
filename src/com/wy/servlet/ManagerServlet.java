package com.wy.servlet;

import java.io.IOException;
import com.wy.java.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");//设置编码方式避免中文乱码
		DBHandle dbh=new DBHandle();
		int pageSize=10;
		int pageCount=0;
		int pageNow=1;
		int rowCount=0;//声明四个局部变量,用于分页处理
		String flag=request.getParameter("flag");
		String pageNowStr=request.getParameter("pageNow");
		
		
		String roleStr = request.getParameter("signal");
		int role = Integer.parseInt(roleStr);
	    //System.out.println("role:"+role);
		if(pageNowStr==null||pageNowStr=="") pageNowStr="1";//默认pageNow=1，避免一些不必要的异常，如null（空）对象异常
		pageNow=Integer.parseInt(pageNowStr);
		HttpSession sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		
		if (flag.equals("xinxijiansuo")){
		  	ArrayList<Ir_list> al=new ArrayList<Ir_list>();
		  	al= dbh.getIr_list("select * from ir_source limit "+pageSize*(pageNow-1)+","+pageSize);
			rowCount=dbh.getRowCount("select count(*) from ir_source");
			request.setAttribute("tea_num", "");					
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;	   
		    request.setAttribute("Ir_list1", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");	
			if (role==731){
			request.getRequestDispatcher("xinxijiansuo/xinxijiansuo.jsp?role=731").forward(request,response);}
			else{request.getRequestDispatcher("xinxijiansuo/xinxijiansuo_user.jsp?role=0").forward(request,response);}
		}else if (flag.equals("wenda")){
		  	ArrayList<qa_list> al=new ArrayList<qa_list>();
		  	al= dbh.getQa_list("select * from qa_source limit "+pageSize*(pageNow-1)+","+pageSize);
			rowCount=dbh.getRowCount("select count(*) from qa_source");
			request.setAttribute("tea_num", "");					
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;	   
		    request.setAttribute("Ir_list1", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			if(role==731){
			request.getRequestDispatcher("wenda/wenda.jsp?role=731").forward(request,response);}
			else{request.getRequestDispatcher("wenda/wenda_user.jsp?role=0").forward(request,response);}
		}else if (flag.equals("yuedulijie")){
		  	ArrayList<yd_list> al=new ArrayList<yd_list>();
		  	al= dbh.getYd_list("select * from yd_source limit "+pageSize*(pageNow-1)+","+pageSize);
			rowCount=dbh.getRowCount("select count(*) from yd_source");
			request.setAttribute("tea_num", "");					
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;	   
		    request.setAttribute("Ir_list1", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			if(role==731){
			request.getRequestDispatcher("yuedulijie/yuedulijie.jsp?role=731").forward(request,response);}
			else{request.getRequestDispatcher("yuedulijie/yuedulijie_user.jsp?role=0").forward(request,response);}
		}else if (flag.equals("liangzi")){
		  	ArrayList<qutum_list> al=new ArrayList<qutum_list>();
		  	al= dbh.getQu_list("select * from qutum_source limit "+pageSize*(pageNow-1)+","+pageSize);
			rowCount=dbh.getRowCount("select count(*) from qutum_source");
			request.setAttribute("tea_num", "");					
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;	   
		    request.setAttribute("Ir_list1", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			//System.out.print(role);
			if (role==731){
			request.getRequestDispatcher("liangzi/liangzi.jsp?role=731").forward(request,response);}
			else{request.getRequestDispatcher("liangzi/liangzi_user.jsp?role=0").forward(request,response);}
			
		}else if (flag.equals("duihua")){
		  	ArrayList<gen_list> al=new ArrayList<gen_list>();
		  	al= dbh.getGen_list("select * from Gen_source limit "+pageSize*(pageNow-1)+","+pageSize);
			rowCount=dbh.getRowCount("select count(*) from Gen_source");
			request.setAttribute("tea_num", "");					
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;	   
		    request.setAttribute("Ir_list1", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			if (role==731){
			request.getRequestDispatcher("duihua/duihua.jsp?role=731").forward(request,response);}
			else{request.getRequestDispatcher("duihua/duihua_user.jsp?role=0").forward(request,response);}
			
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	
	}

}

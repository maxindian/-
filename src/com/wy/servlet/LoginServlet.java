package com.wy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wy.java.*;

import com.wy.java.DBHandle;

import java.util.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		DBHandle dbh=new DBHandle();
		String user=request.getParameter("user");
		String psd=request.getParameter("psd");
		String flag=request.getParameter("flag"); 
		boolean b=false;
		HttpSession sess=request.getSession();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<String> password=new ArrayList<String>();
		int identity = -1;
		
			if(flag.equals("login")){
				password = dbh.getPassword("select passwd from user_list where name='"+user+"'");
				identity = dbh.getFlag("select flag1 from user_list where name='"+user+"'");
				
				for(int i=0;i<password.size();i++){		
					if(password.get(i).equals(psd)){						
						b=true;
						break;
					}	
				}
				
				if(b==true){	
					if (identity == 0){
						//request.setAttribute("label", "1");
						response.sendRedirect("show_manage.jsp?identity=0&label=1");
					}else if(identity == 1){
						//request.setAttribute("label", "1");
						response.sendRedirect("show_manage.jsp?identity=731&label=1");					
					}else{
						response.sendRedirect("Login.jsp?flag=error");
					}				
				}else{
					
					response.sendRedirect("Login.jsp?flag=error");
				}
				
			}else if(flag.equals("invalidate")){
				sess.invalidate();
				response.sendRedirect("Login.jsp");
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

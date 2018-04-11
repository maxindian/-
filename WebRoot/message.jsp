<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <% 
	String user=(String)session.getAttribute("biao");
	System.out.println("biao:"+user);
	if(user==null){
		//response.sendRedirect("index.jsp?flag=login");
	}
	

%>
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>消息提示</title>
 </head>
 <body>
 	<form action="index.jsp"  method="post" name="add" target="_self" id="add">
 		<ul>
				<%
			    	String flag=request.getParameter("biao");
			    	System.out.println("flag:"+flag);
			    	if(flag!=null){
			    		if(flag.equals("1")){			    			
			    			
			    			%>
			    			<script language="javascript">
			    			window.alert("添加成功")
			    			</script>
			    			
			    			<%
			    		}
			    	}
			     %>
			  </ul>
			 <input type="submit" class="adinput" value="返回" id="submit" "/>
			
			</form>
     ${message }<br>
     ${biao}<br>
    
</body>
 </html>
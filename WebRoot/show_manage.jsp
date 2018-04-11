<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
String name = request.getParameter("identity");
String label =  request.getParameter("label");
int la = Integer.parseInt(label);
if(la!=1){
response.sendRedirect("Login.jsp");
}
%>  


<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<title>资源管理系统</title>
</head>
<frameset rows="88,*,50" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp?identity=<%=name%>" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="left.jsp?identity=<%=name%>" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="index.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />    
  </frameset>
  <frame src="bottom.jsp" name="bottomFrame" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>	
</noframes>
</html>
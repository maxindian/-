<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("identity");
int role = Integer.parseInt(name);
%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>固定资产管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<body style="background:url(images/topbg.gif) repeat-x;">
    <div class="topleft">
    	<a href="main.html" target="_parent"><img src="images/logo.png" title="系统首页" style="height: 73px; width: 80px; "/></a>
    </div>        
  
    <div class="topright">    
    	<ul>
    		<li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span></li>
    		<li><a href="#">关于</a></li>
    		<li><a href="Login.jsp" target="_parent"">退出</a></li>
    	</ul>     
    	<div class="user">
    	<%
    		if(role== 0)
    		{%>
    		<span>user</span>    		
    		<%}else{%>
    		<span>admin</span>
    		<%}
    	
    	 %>
    		
    	</div>        
    </div>
</body>
</html>



<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>欢迎登录资源管理系统</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js"></script>
		<script src="js/cloud.js" type="text/javascript"></script>
		<script language="javascript">
			function check()
		{
			if((document.login.user.value)=='')
			{
				window.alert('用户名称不能为空，请输入用户名');	
				document.login.user.focus();
				return false;
			}else if((document.login.psd.value)=='')
			{
				window.alert('密码不能为空，请输入密码');
				document.login.psd.focus();
				return false;
			}else 
			{
				return true;
			}
		}
			$(function() {
				$('.loginbox').css({
					'position': 'absolute',
					'left': ($(window).width() - 692) / 2
				});
				$(window).resize(function() {
					$('.loginbox').css({
						'position': 'absolute',
						'left': ($(window).width() - 692) / 2
					});
				})
			});
			$(function() {
				
				// 密码部分
				$('#login_showPwd').focus(function() {
					var text_value = $(this).val();
					if(text_value == this.defaultValue) {
						$('#login_showPwd').hide();
						$('#login_password').show().focus();
					}
				});
				$('#login_password').blur(function() {
					var text_value = $(this).val();
					if(text_value == "") {
						$('#login_showPwd').show();
						$('#login_password').hide();
					}
				});
			});
		</script>
	</head>

	<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
		<div id="mainBody">
			<div id="cloud1" class="cloud"></div>
			<div id="cloud2" class="cloud"></div>
		</div>
		<div class="logintop">
			<span>欢迎登录资源管理系统</span>
			<ul>
				<li>
					<a href="#">回首页</a>
				</li>
				<li>
					<a href="#">帮助</a>
				</li>
				<li>
					<a href="#">关于</a>
				</li>
			</ul>
		</div>
		
		<div class="loginbody">
			<span class="systemlogo"></span>
			
			<div class="loginbox">
				
				<ul>
				<%
			    	String flag=request.getParameter("flag");
			    	if(flag!=null){
			    		if(flag.equals("error")){			    			
			    			
			    			%>
			    			<script language="javascript">
			    			window.alert("输入的密码不正确")
			    			</script>
			    			
			    			<%
			    		}
			    	}
			     %>
				<form action="LoginServlet?flag=login" method="post" name="login" target="_self" id="login">
			      	<li><input name="user" type="text" id="user" class="loginuser" value="请输入用户名" onclick="JavaScript:this.value=''" /></li>
					<li>
						<input type="password" name="psd" class="loginpwd" value="请输入您的密码" id="psd" onclick="JavaScript:this.value=''"  />
						
						
					</li>
					<li>
						<input type="submit" class="loginbtn" name="submit" id="submit" value="登录" onClick="javascript:return check()"/>
						
					</li>              
      			</form>					
				</ul>
			</div>
		</div>
		<div class="loginbm">Copyright © 2018 - 2040  无版权.</div>
	</body>

</html>
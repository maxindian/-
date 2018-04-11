<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("identity");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>固定资产管理系统</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js">
		</script>
		<script type="text/javascript">
			function GetQueryString(name)
				{
				     var reg
				 = new RegExp("(^|&)"+
				 name +"=([^&]*)(&|$)");
				     var r
				 = window.location.search.substr(1).match(reg);
				     if(r!=null)return  unescape(r[2]);return null;
				}
			$(function() {
				//导航切换
				$(".menuson li").click(function() {
					$(".menuson li.active").removeClass("active")
					$(this).addClass("active");
				});
				$('.title').click(function() {
					var $ul = $(this).next('ul');
					$('dd').find('ul').slideUp();
					if($ul.is(':visible')) {
						$(this).next('ul').slideUp();
					} else {
						$(this).next('ul').slideDown();
					}
				});
			})
		</script>
	</head>

	<body style="background:#f0f9fd;">
		<div class="lefttop"><span></span>导航菜单</div>
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico04.png" /></span>
					<a href="index.jsp" target="rightFrame">首页</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /></span>研究领域
				</div>
				<ul class="menuson" style="display: inherit;">
					<li class="active"><cite></cite>
						<a href="ManagerServlet?flag=wenda&pageNow=1&signal=<%=name%>" target="rightFrame">问答</a><i></i></li>
					<li><cite></cite>
						<a href="ManagerServlet?flag=liangzi&pageNow=1&signal=<%=name%>" target="rightFrame">量子</a><i></i></li>
					<li><cite></cite>
						<a href="ManagerServlet?flag=xinxijiansuo&pageNow=1&signal=<%=name%>" target="rightFrame">信息检索</a><i></i></li>
					<li><cite></cite>
						<a href="ManagerServlet?flag=duihua&pageNow=1&signal=<%=name%>" target="rightFrame">对话</a><i></i></li>
					<li><cite></cite>
						<a href="ManagerServlet?flag=yuedulijie&pageNow=1&signal=<%=name%>" target="rightFrame">阅读理解</a><i></i></li>
				</ul>
			</dd>
			<dd>
				
			</dd>
			<dd>
				
			</dd>
			<dd><!-- 
				<div class="title"><span><img src="images/leftico03.png" /></span>权限管理</div>
				<ul class="menuson">
					<li><cite></cite>
						<a href="#">自定义</a><i></i></li>
					<li><cite></cite>
						<a href="#">常用资料</a><i></i></li>
					<li><cite></cite>
						<a href="#">信息列表</a><i></i></li>
					<li><cite></cite>
						<a href="#">其他</a><i></i></li>
				</ul> -->
			</dd>
		</dl>
	</body>

</html>


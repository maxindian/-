<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String role = request.getParameter("signal");
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.js"></script>

		<script type="text/javascript">
			$(document).ready(function() {
				$(".click").click(function() {
					$(".tip").fadeIn(200);
				});

				$(".tiptop a").click(function() {
					$(".tip").fadeOut(200);
				});

				$(".sure").click(function() {
					$(".tip").fadeOut(100);
				});

				$(".cancel").click(function() {
					$(".tip").fadeOut(100);
				});
			});
			var isCheckAll = false;

			function swapCheck() {
				if(isCheckAll) {
					$("input[type='checkbox']").each(function() {
						this.checked = false;
					});
					isCheckAll = false;
				} else {
					$("input[type='checkbox']").each(function() {
						this.checked = true;
					});
					isCheckAll = true;
				}
			}
		</script>

	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">首页</a>
				</li>
				<li>
					<a href="#">信息检索</a>
				</li>
				<li>
					<a href="#">添加</a>
				</li>
			</ul>
		</div>

		<div class="rightinfo">
			<form action="../Manager_IRServlet?flag=add&(${pageContext.request.contextPath }/upload)&signal=<%=role %>" enctype="multipart/form-data" method="post" name="add" target="_self" id="add">
			<table class="tablelist" cellpadding="0" cellspacing="0">
				
				<tbody>
				
					<tr>
						<td>文件标题</td>
						<td style="text-align: center;"><input name="title" type="text" id="title" class="adinput" placeholder="请输入文件标题" /></td>
					</tr>
					<tr>
						<td>选择文件</td>
						<td style="text-align: center;"><input type="file" class="adinput" id="file" name="file1" multiple="multiple"></td>
					</tr>
					<tr>
						<td>上传文件</td>
						<td style="text-align: center;"><input type="submit" class="adinput" value="上传" id="submit" style="background-image: url(../images/buttonbg.png);"/></td>
					</tr>
				</tbody>
			</table>
			</form>
			
			<div class="tip">
				<div class="tiptop"><span>提示信息</span>
					<a></a>
				</div>

				<div class="tipinfo">
					<span><img src="../images/ticon.png" /></span>
					<div class="tipright">
						<p>是否确认对信息的修改 ？</p>
						<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
					</div>
				</div>

				<div class="tipbtn">
					<input name="" type="button" class="sure" value="确定" />&nbsp;
					<input name="" type="button" class="cancel" value="取消" />
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>
	</body>

</html>
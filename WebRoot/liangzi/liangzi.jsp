<%@ page language="java" import="java.util.*,com.wy.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String role = request.getParameter("role");

%>
<% 
	String user=(String)session.getAttribute("user");
	if(user==null){
		//response.sendRedirect("index.jsp?flag=login");
	}
	ArrayList<qutum_list> al=(ArrayList<qutum_list>)request.getAttribute("Ir_list1");
	
	String name=(String)request.getAttribute("name");

%>

<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript">
			$(document).ready(function() {
				$(".click").click(function() {
					$(".tip").fadeIn(200);
				});
				$(".addclick").click(function() {
					location.href = "liangzi/liangzi_add.jsp?signal=<%=role%>";
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
			 function check(){
			  	return window.confirm("你确定要删除该信息吗？");
			  }
		</script>
		
	</head>

	<body >
		
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">首页</a>
				</li>
				<li>
					<a href="#">量子</a>
				</li>
				<li>
					<a href="#">列表</a>
				</li>
			</ul>
		</div>

		<div class="rightinfo">
			
			<div class="tools">

				<ul class="toolbar">
					<li class=addclick><span><img src="images/t01.png" /></span>添加</li>
				</ul>
			</div>
			<table class="tablelist">
			
				<form action="ManagerServlet?flag=liangzi&signal=<%=role%>" method="post" id="form1" >
				<thead>
					<tr>
						<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
						<th>标题</th>
						<th>发布时间</th>
						<th>删除</th>
						<th>下载</th>
					</tr>
				</thead>
				<tbody>					
					<tr>
						
						<% 
					       //String[] color={"FFCCFF","#CC99FF","#FFFFCC"};
					       if(al==null||al.size()==0){
					      
					      %>
					      <tr bgcolor="#FFCCFF">
					        <td align="center" colspan="7">对不起，没有相关记录</td>		        
					      </tr>
					      <%
						      }else{
						      	for(int i=0;i<al.size();i++){
						      		qutum_list st = al.get(i);%>
						      	<tr>
						        <td align="center" height="25"><%=st.getQutum_ID()%></td>
						        <td align="center"><%=st.getTitle_name() %></td>
						        <td align="center"><%=st.getCurrent_time() %></td>
						  
						        <td align="center">
						    		<a href="Manager_QutumServlet?flag=delete&Ir_ID=<%=st.getQutum_ID()%>&signal=<%=role%>" onClick="javascript:return check()">删除</a></td>>
						    		
						    	<td align="center">
						    		<a href="Manager_QutumServlet?flag=download&Ir_ID=<%=st.getQutum_ID() %>&signal=<%=role %>" onClick="">下载</a></td>
						      </tr>
						      		<%
						      	}
						      }
					      %>        
					
						
				</tbody>
					 
			</table>
			<tr><td></td></tr>
			<tr>
					  	<%
						  	int pageCount=Integer.parseInt((String)request.getAttribute("pageCount"));
						  	int pageNow=Integer.parseInt((String)request.getAttribute("pageNow"));
					  	 %>
					    <td align="center" height="30">共有&nbsp;<font color="#FF0000"><%=request.getAttribute("rowCount") %></font>&nbsp;条记录，
					    &nbsp;共&nbsp;<font color="#FF0000"><%=pageCount%></font>&nbsp;页，
					    &nbsp;&nbsp;&nbsp;&nbsp;当前第&nbsp;<font color="#FF0000"><%=pageNow %></font>&nbsp;页
					    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=liangzi&pageNow=1&signal=<%=role%>">首页</a>		
					    <% if(pageNow!=1){		    
					    %>
					    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=liangzi&pageNow=<%=pageNow-1 %>&signal=<%=role%>">上一页</a>
					    <%
					     }		    
					    %>
					      <% if(pageNow!=pageCount){		    
					    %>
					    &nbsp;&nbsp;<a href="ManagerServlet?flag=liangzi&pageNow=<%=pageNow+1 %>&signal=<%=role%>">下一页</a>
					    <%
					     }		    
					    %>   
					    &nbsp;&nbsp;<a href="ManagerServlet?flag=liangzi&pageNow=<%=pageCount %>&signal=<%=role%>">尾页</a>
					    &nbsp;&nbsp;
					      <label>
					      <select name="pageNow" id="select">
					      <option></option>
					        <%
					        for(int i=1;i<=pageCount;i++){
					        
					        %>
					        <option><%=i %></option>
					        <%
					        }
					        %>		        
					      </select>
					      <input type="submit" name="button" id="button" value="go" style="width: 60px; height: 40px;"/>
					    </label></td>
					  </tr>
				</form>
			
			<div class="tip" >
				<div class="tiptop"><span>提示信息</span>
					<a></a>
				</div>

				<div class="tipinfo">
					<span><img src="images/ticon.png" /></span>
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
			<div class="tip" id="add">
				<div class="tiptop"><span>提示信息</span>
					<a></a>
				</div>

				<div class="tipinfo">
					<span><img src="images/ticon.png" /></span>
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
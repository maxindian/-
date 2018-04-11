<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	String user=(String)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("index.jsp?flag=login");
	}
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table width="920" height="260" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="160" colspan="4" background="images/top.jpg"></td>
        </tr>
      <tr bgcolor="#CCCCCC">
	      <td  headers="30">&nbsp;</td>
	      <td colspan="2" align="right">欢迎你：<font color="#1100aa"><%=session.getAttribute("user") %></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                角色：<font color="#1100aa"><%=session.getAttribute("role") %></font></td>
	      <td  align="center"><a href="LoginServlet?flag=invalidate">注销</a></td>
      </tr>      
      <tr bgcolor="#CCCCCC">
        <td width="78" height="30">&nbsp;</td>
        <td width="589"><font color="#1100aa">      
        <marquee id="scrollArea1" scrollamount="5"  onmouseout="this.start()" onMouseOver="this.stop()"
              scrolldelay="130" direction="right" width="100%" >
                         欢迎来到在线考试系统后台管理
       </marquee>  
    </font></td>
        <td width="46">&nbsp;</td>
        <td width="230" align="center"><font color="#000000">
       <script language="JavaScript" type="text/javascript">    
       d = new Date();
       if(d.getMinutes()<10){
       	document.write((d.getFullYear())+"年"+(d.getMonth()+1)+"月"+d.getDate()+ "日&nbsp;"+d.getHours()+":0"+d.getMinutes());
       }else{
       	document.write((d.getFullYear())+"年"+(d.getMonth()+1)+"月"+d.getDate()+ "日&nbsp;"+d.getHours()+":"+d.getMinutes());
       }				
  </script>
    </font><font color="#000000">
          <script language="JavaScript" type="text/javascript">
			d = new Date();
			switch (d.getDay()) {
			case 0:
			strweek="日";
			break;
			case 1:
			strweek="一";
			break;
			case 2:
			strweek="二";
			break;
			case 3:
			strweek="三";
			break;
			case 4:
			strweek="四";
			break;
			case 5:
			strweek="五";
			break;
			case 6:
			strweek="六";
			break;
			}
	document.write("星期"+ strweek);
	</script>
    </font></td>
      </tr>
      <tr bgcolor="#CCCCCC">
       <td colspan="5" height="40"><table width="100%" height="40" border="0" align="center" background="images/navigation_bar.jpg">
         <tr>
           <td><div align="center"><a href="ManagerServlet?flag=teacher&pageNow=1">教师管理</a></div></td>
           <td><div align="center"><a href="ManagerServlet?flag=student&pageNow=1">学生管理</a></div></td>
           <td><div align="center"><a href="ManagerServlet?flag=course&pageNow=1">课程管理</a></div></td>
           <td><div align="center"><a href="ManagerServlet?flag=class&pageNow=1">班级管理</a></div></td>
           <td><div align="center"><a href="ManagerServlet?flag=assign&pageNow=1">分配课程</a></div></td>
           <td><div align="center"><a href="ManagerServlet?flag=query&pageNow=1">查询系统</a></div></td>
           <td><div align="center"><a href="Manager_psdAlter.jsp">修改密码</a></div></td>
         </tr>
       </table></td>
      </tr>
    </table>
</body>
</html>
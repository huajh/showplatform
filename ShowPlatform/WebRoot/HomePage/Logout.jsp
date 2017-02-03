<%@page import="JavaBeans.People.Member"%>
<%@page import="Path.PathInfo"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Logout.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>
  </head>
 
  <%
	session.setAttribute("Member", null);	
  	response.setHeader("refresh","2;url=Index.jsp");//定时跳转
  	session.invalidate();//注销

  %>
  <body>
    <header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
 	 <h3>你好,你已经退出本系统,两秒后跳会首页</h3>
 	 <h3>如没有跳转,请按<a href="Index.jsp">这里</a></h3>	

  <footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
  </body>
  

</html>


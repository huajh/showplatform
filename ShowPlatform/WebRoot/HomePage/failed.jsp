<%@page import="JavaBeans.People.Member"%>
<%@page import="Path.PathInfo"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
	//String msg = request.getParameter("msg");
	String msg = (String)request.getParameter("msg");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>failedPage</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
</head>
<style type="text/css">

</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<section id="information">
	<div id="infor_head">
		<%=request.getParameter("msg") %>
		<li ><a href="<%=path %>/HomePage/Index.jsp">回到主页</a></li>
    </div>
</section>

<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>

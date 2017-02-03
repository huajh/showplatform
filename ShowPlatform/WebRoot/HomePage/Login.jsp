<%@page import="JavaBeans.People.Member"%>
<%@page import="InterOperate.LoginType"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
	//String msg = request.getParameter("msg");
%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>用户登陆</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>
</head>
<style type="text/css">

</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>

<div id="container">
<form action="<%=basePath %>servlet/LoginServlet?loginType=<%=LoginType.MEMBERTYPE%>" method="post" class="niceform" target="_self">
	<h1>用户登陆</h1>
    <br>
    <label for="username">用户名：</label>
	<input type="text" id="username" name="uid" size="18" />
	<% String msg = (String)request.getParameter("msg");
		if(msg!=null)
			out.println("<font color='red'>"+msg+"</font>");
	%>	
	<br /><br/>
	<label for="pwd">密码：</label>
	<input type="password" id="passwordinput" name="pwd" size="20" /><br /><br/>
	<input type="submit" value="提交" /><input type="button" value="取消" />
</form>
</div>

<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>


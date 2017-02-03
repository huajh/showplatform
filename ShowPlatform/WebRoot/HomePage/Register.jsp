<%@page import="JavaBeans.People.Member"%>
<%@page import="InterOperate.LoginType"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>新用户注册</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>

<script type="text/javascript">

</script>
</head>
<style type="text/css">

</style>
<body>

<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>

<div id="container">
<form action="<%=basePath %>servlet/RegisterServlet" method="post" name = "Register" class="niceform" target="_self">
	<h1>新用户注册</h1>
    <br>
    <label for="username">用户名：</label>
	<input type="text" id="textinput" name="uid" size="16" />
		<% String msg = (String)request.getParameter("msg");
		if(msg!=null)
			out.println("<font color='red'>"+msg+"</font>");
		%>	
	<br /><br/>
	    <label for="email">邮箱：</label>
	<input type="text" id="passwordinput" name="RegisterEmail" size="15" />
			<% String msg2 = (String)request.getParameter("msg2");
		if(msg2!=null)
			out.println("<font color='red'>"+msg2+"</font>");
		%>	
	<br /><br />	
	<label for="password">密码：</label>
	<input type="password" id="passwordinput" name="pwd" size="16" />
		<% String msg3 = (String)request.getParameter("msg3");
		if(msg3!=null)
			out.println("<font color='red'>"+msg3+"</font>");
		%>	
	<br /><br/>
    <label for="confirm">确认密码：</label>
	<input type="password" id="passwordinput" name="repwd" size="15" /><br /><br />
	<input type="submit" value="提交" /><input type="button" value="取消" />
</form>
</div>

<footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>

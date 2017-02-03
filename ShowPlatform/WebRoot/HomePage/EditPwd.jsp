<%@page import="JavaBeans.People.Member"%>
<%@page import="InterOperate.LoginType"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="gb2312" />
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="../CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="../CSS/form_style.css">
<script language="javascript" type="text/javascript" src="../niceforms.js"></script>
</head>
<style type="text/css">
#row{ float:right; margin-right:650px;}
</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<div id="container">
<form action="" method="post"  target="_blank">
	<h1>修改密码</h1>
    <br>
    <div id="row">
    <label for="username">用户名：</label>
	<input type="text" name="uid" value ="<%=member.getMemberID() %>" size="18" />
    </div><br /><br/>
    <div id="row">
    <label for="password">旧密码：</label>
	<input type="password" name="oldPwd" size="18" />
    </div><br /><br/>
    <div id="row">
	<label for="password">新密码：</label>
	<input type="password" name="newPwd" size="18" />
    </div><br /><br/>
    <div id="row">
    <label for="confirm">确认新密码：</label>
    <input type="password" name="reNewPwd" size="18" /><br /><br />
    </div>
    <div id="row">
	<input type="submit" value="提交" /><input type="button" value="取消" />
    </div>
</form>
</div>

<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>

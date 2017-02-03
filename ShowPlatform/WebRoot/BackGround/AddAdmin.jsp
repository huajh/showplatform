<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="JavaBeans.People.Administor"%>
<%@page import="Path.PathInfo"%>
<%@page import="InterOperate.PublicOperator"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加管理员</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />

  </head>
  
  <style type="text/css">
input{ font-size:18px; padding-right:5px;}
#addAdminer
{
	float:left;
	margin-left:10px;
	margin-top:10px;
	width:300px;
}
#row{ float:right; margin-right:5px;}
</style>
<body>
<div id="addAdminer">
	<h3>添加新管理员</h3>
    <form action="<%=basePath %>servlet/Admin/addAdminServlet" method="post" name="newAdminer" class="niceform" target="_self">
    <br>
    <div id="row">
    <label for="worksName">用户名：</label>
	<input type="text" name="account" size="17" /><br /><br/>
    </div>
     <div id="row">
    <label for="password">密码：</label>
	<input type="password" name="pwd" size="17" /><br /><br/>
    </div>
    <div id="row">
    <label for="confirm">确认密码：</label>
	<input type="password" name="confirmPwd" size="17" /><br /><br/>
	<% String msg = (String)request.getParameter("msg");
		 if(msg!=null)
			out.println("<font color='red'>"+msg+"</font>");
	%>
	</div>
    <div id="row">
    <input type="submit" name="yes" value="确定" />
    <input type="reset" name="no" value="取消" />
	</div>
</form>
</div>
</body>
</html>

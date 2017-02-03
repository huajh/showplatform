<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.Components.News"%>
<%@page import="JavaBeans.People.Administor" %>
<%@page import="Path.PathInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发布新闻</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />
  </head>
  <style type="text/css">
#style1{font-size:20px;}
#style2{font-size:17px;}
</style>
<body>
<div id="layout"><div align="center">	 
</div><h2 align="center">添加新闻</h2>
	<form enctype="multipart/form-data" method="post" action="<%=basePath %>servlet/AddNews?" name="addNews" class="niceform" target="_self">
    	新闻标题：<input id="style1" width="100" type="text" name="title" />
    	新闻类型：<select id="choice" size="1">
					<option value="op1">主页新闻</option>
                    <option value="op2">比赛新闻</option>
			</select>
    	<br />
        新闻内容：<textarea id="style1" rows="10" cols="105" name="newsContent"></textarea>
        <input id="style2" type="submit" name="submit" value="发布" />
        <input id="style2" type="reset" name="cancel" value="取消" />
    </form>
</div>
</body>
</html>

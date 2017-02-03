<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.Components.News"%>
<%@page import="JavaBeans.People.Administor" %>
<%@page import="Path.PathInfo" %>
<%@page import="java.util.Collection" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有新闻</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />

  </head>
  
<style type="text/css">
#edit{ float:right; margin-right:5px;}
</style>
<body>
<div id="layout">
<h3>所有新闻</h3>
<table width=900 border="0" cellpadding="5" cellspacing="1" bgcolor="#add3ef">
  <%
  	Collection newsCollection = PublicOperator.getAllNews();
  	if(newsCollection!=null)
  	{
  		Iterator it = newsCollection.iterator();
  		while(it.hasNext())
  		{
  			News news = (News)it.next();
  			
  			out.println("<tr bgcolor='#eff3ff'><td>新闻标题：");
  			out.println(news.getTitle());
  			out.println("</td></tr>");
  			
  			out.println("<tr bgcolor='#eff3ff'><td>发布时间：");
  			out.println(news.getCreateTime());
  			out.println("</td></tr>");
  			
  			out.println("<tr bgcolor='#eff3ff'><td>发布人：");
  			out.println(news.getAuthor());
  			out.println("</td></tr>");
  			
  			out.println("<tr bgcolor='#eff3ff'><td>新闻内容：");
  			out.println(news.getNewsContent());
  			out.println("<a id='edit' href='EditNews.jsp'>编辑</a><a id='edit' href='DeleteNewsServlet.java'>删除</a></td>");
  			
  			out.println("<tr bgcolor='#eff3ff'><td>浏览次数：");
  			out.println(news.getVisitTimes());
  			out.println("</td></tr>");
  		}
  	}
   %>
</table>
</div>
</body>
</html>

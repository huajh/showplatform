<%@page import="java.text.SimpleDateFormat"%>
<%@page import="JavaBeans.Components.News"%>
<%@page import="JavaBeans.People.Guest"%>
<%@page import="JavaBeans.People.Member"%>
<%@page import="Path.PathInfo"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.getSession(true).setAttribute("Current",Guest.GuestType);	
	Member member = (Member)session.getAttribute("Member");
	int newsID = Integer.parseInt(request.getParameter("NewsID"));
	News news = new News(newsID);		
	news.load();
	news.visited();
	int visitTimes = news.getVisitTimes();
	String title = news.getTitle().trim();
	String author = news.getAuthor();
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM");
	String contest = news.getNewsContent();
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
<title>赛事新闻</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
</head>
<style type="text/css">

</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="mainContent">
<aside id="main">
	<div class="mainbox">
		<h2><a href="<%=path %><%=PathInfo.HOMEPAGE %>" id="return"> <<主页</a>
		<a href="<%=path %><%=PathInfo.IndexNewsList %>" id="return"> <<新闻列表 </a></h2>
        <h1 align="center"><%=title %></h1>
        <h4 align="center">发布人:<%=author %></h4>
        <h4 align="center">发布时间:<%=df.format(news.getLastEditTime())%></h4>
        <h4 align="center">浏览次数:<%=visitTimes %></h4>
        <p id="news">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%=news.getNewsContent() %>
        
        
</p>
<br />
<a href="<%=path %><%=PathInfo.IndexNewsList %>" id="return"> 返回 </a>
	</div>
</section>
<footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>


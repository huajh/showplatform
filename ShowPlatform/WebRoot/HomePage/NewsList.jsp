<%@page import="java.text.SimpleDateFormat"%>
<%@page import="JavaBeans.Contest.Contest"%>
<%@page import="JavaBeans.Components.News"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.People.Member"%>
<%@page import="Path.PathInfo"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");	
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
<title>赛事新闻</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
</head>
<style type="text/css">
#pageul{ float:left;}
#pageul li{ float:left;margin-left:5px;}
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
		</h2>
		<ul>	
		<%
			Collection newsCollection = PublicOperator.getNews(News.IndexNews,1,10);
			if(newsCollection!=null)
			{
				Iterator it = newsCollection.iterator();
				while(it.hasNext())
				{
					System.out.println("has Next");
					News news = (News)it.next();						
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String title = news.getTitle().trim();					
					if(title.length()>30)
					{
						title = title.substring(0,30)+"...";
					}
					String date  = df.format(news.getLastEditTime());
					int visits = news.getVisitTimes();
					%>					
					<li> <%=date %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="<%=path %><%=PathInfo.IndexNews %>?NewsID=<%=news.getNewsID()%>"><%=title%></a>
						&nbsp;&nbsp;&nbsp;(<font color="#FF0000"><%=visits %></font>)					
					</li>
					
					<%
				}	
			}
		%>
			<li><a>上一页</a>&nbsp;&nbsp;[1]<a>&nbsp;&nbsp;[2]</a><a>&nbsp;&nbsp;上一页</a></li>
        </ul>
        	
	</div>
	
</aside>
</section>
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>


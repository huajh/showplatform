<%@page import="JavaBeans.Works.Works"%>
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
	//String msg = request.getParameter("msg");
	String msg = (String)request.getAttribute("msg");			
	
%>	

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>作品展示平台</title>
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
		<h2>赛事新闻<a href="<%=path%><%=PathInfo.IndexNewsList %>" id="more">更多>>></a></h2>
		<ul>	
		<%
			Collection newsCollection = PublicOperator.getNews(News.IndexNews,1,10);
			if(newsCollection!=null)
			{
				Iterator it = newsCollection.iterator();
				while(it.hasNext())
				{
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
					<li><a href="<%=path %><%=PathInfo.IndexNews %>?NewsID=<%=news.getNewsID()%>"><%=title%> (<%=date %>)</a>						
					</li>
					<%
				}						
			}
		%>
        </ul>
	</div>
    	<div class="mainbox">
		<h2>热门比赛<a href="<%=path%><%=PathInfo.ContestList%>" id="more">更多>>></a></h2>
		<%
		Collection contests = PublicOperator.getContest(1,9);
		if(contests!=null)
		{
			Iterator it = contests.iterator();
			int i;
			for(i=0;i<9 && it.hasNext();i++)
			{	
				System.out.println(i);
				Contest contest = (Contest)it.next();
				String title = contest.getTitle().trim();
				String href = path+PathInfo.ContestMain+"?ContestID="+contest.getContestID();
				String src =application.getContextPath()+contest.getContestPath()+"Main.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=title %></a></li>
					<%
				}else if(i%3==1)  //1
				{	
					%>					
					<li class="litest_last"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=title %></a></li>
					<% 				
				}
				else 
				{
					%>
					<li ><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=title %></a></li>
					</ul>
					<%
				}			
			}	
			if(i!=9)
			{
				out.println("</ul>");
			}
		}	
		%>
	</div>
    <div class="mainbox">
		<h2>人气选手<a href="<%=path%><%=PathInfo.DesignersList%>" id="more">更多>>></a></h2>
			<%
		Collection members = PublicOperator.getTopMember(9);
		if(members!=null)
		{
			Iterator it = members.iterator();
			int i;
			for(i=0;i<9 && it.hasNext();i++)
			{	
				System.out.println(i);
				Member topmember = (Member)it.next();
				String name = topmember.getMemberID();
				String href = path+PathInfo.DesignerDetail+"?MemberID="+topmember.getMemberID();
				String src =application.getContextPath()+topmember.getMemberPath()+"head.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<%
				}else if(i%3==1)  //1
				{	
					%>					
					<li class="litest_last"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<% 				
				}
				else 
				{
					%>
					<li ><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					</ul>
					<%
				}			
			}	
			if(i!=9)
			{
				out.println("</ul>");
			}
		}	
		%>
	</div>
    <div class="mainbox">
		<h2>人气作品</h2>
			<%
		Collection topWorks = PublicOperator.getTopWorks(Contest.Start_Score,12);
		if(topWorks!=null)
		{		
			Iterator it = topWorks.iterator();
			int i;
			for(i=0;i<9 && it.hasNext();i++)
			{	
				System.out.println(i);
				
				Works works = (Works)it.next();
				String name = works.getName();
				String href = path+PathInfo.DesignworksDetail+"?WorksID="+works.getWorksID();
				String src =application.getContextPath()+works.getWorksPath()+"/Main/main.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<%
				}else if(i%3==1)  //1
				{	
					%>					
					<li class="litest_last"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<% 				
				}
				else 
				{
					%>
					<li ><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					</ul>
					<%
				}			
			}	
			if(i!=9)
			{
				out.println("</ul>");
			}
		}	
		%>
	</div>
</aside>
</section>
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="gb2312" />
<title>赛事</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
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
        	<h2>申请比赛<a id="more" href="<%=path %>/HomePage/PostContest.jsp">点击申请</a></h2>
            <ul>
            	<li id="question">如何申请比赛？</li>
                <li id="answer">就这样申请</li>
                <li id="question">如何申请比赛？</li>
                <li id="answer">就这样申请</li>
                <li id="question">如何申请比赛？</li>
                <li id="answer">就这样申请</li>
            </ul>
        </div>
        
        <div class="mainbox">
            <h1>设计类：</h1>
		<%
		Collection contests = PublicOperator.getContest(Contest.DesignType,1,9);
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
        <br />
        <h1>文字类：</h1>
		<%
		Collection contests2 = PublicOperator.getContest(Contest.LiteratureType,1,9);
		if(contests2!=null)
		{
			Iterator it2 = contests2.iterator();
			int i2;
			for(i2=0;i2<9 && it2.hasNext();i2++)
			{	
				Contest contest = (Contest)it2.next();
				String title = contest.getTitle().trim();
				String href = path+PathInfo.ContestMain+"?ContestID="+contest.getContestID();
				String src =application.getContextPath()+contest.getContestPath()+"Main.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i2%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=title %></a></li>
					<%
				}else if(i2%3==1)  //1
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
			if(i2!=9)
			{
				out.println("</ul>");
			}
		}	
		%>
        <br />
        <h1>摄影类：</h1>
		<%
		Collection contests3 = PublicOperator.getContest(Contest.PhotographyType,1,9);
		if(contests2!=null)
		{
			Iterator it3 = contests3.iterator();
			int i3;
			for(i3=0;i3<9 && it3.hasNext();i3++)
			{	
				Contest contest = (Contest)it3.next();
				String title = contest.getTitle().trim();
				String href = path+PathInfo.ContestMain+"?ContestID="+contest.getContestID();
				String src =application.getContextPath()+contest.getContestPath()+"Main.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i3%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=title %></a></li>
					<%
				}else if(i3%3==1)  //1
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
			if(i3!=9)
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

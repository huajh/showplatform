<%@page import="java.io.File"%>
<%@page import="JavaBeans.Works.Works"%>
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
	String cid = request.getParameter("ContestID");
	if(cid==null)
	{
		out.println("<script>");
		out.println("alert('some error occur~~')");
		out.println("location.href='"+request.getContextPath()+PathInfo.LOGIN+"'</script>");
		return ;
	}
	int contestID = Integer.parseInt(cid);
	Contest contest = new Contest(contestID);
	contest.load();	
	Works myWorks =null;
	if(member!=null)
	{
	    Collection C_works = member.getMyContestWorks(contestID);
	    
		if(C_works!=null)
		{		
			Iterator it = C_works.iterator();
			while(it.hasNext())
			{
				myWorks = (Works)it.next();
				break;
			}
		}
	}	
%>	
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
<title>比赛详情</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/personalInformation_style.css">
</head>
<style type="text/css">
p{ padding-left:10px;}
#rank{ padding-left:10px; font-size:18px; color:#999999;}
select{ font-size:17px;}
#join{ font-size:24px; color:#999999; }
#join a{ font-size:18px;}
b{ color:#FF9900;}
</style>
<body>
<header id="header_style">
	<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="mainContent">

	<aside id="main">
    <div class="mainbox">
    	<h2>比赛介绍</h2>
    	<ul class="cols">
		<li class="colsf">
		<a href="<%=path %><%=PathInfo.ContestMain%>?ContestID=<%=cid %>"><img width="305" height="200" alt="" 
		src="<%=application.getContextPath()%><%=contest.getContestPath()%>Main.jpg"><br></a>
		</li>
		<li class="colsl">        
        <p  style="font-size:20px;"><b>比赛名称：</b><%=contest.getTitle() %></p>
        <p  style="font-size:16px;"><b>比赛类别：</b><%=contest.getContestType() %></p>
        <p  style="font-size:16px;"><b>比赛级别：</b><%=contest.getContestLevel() %></p>
        <p  style="font-size:16px;"><b>主办方：</b><%=contest.getOrganizerID() %></p>
        <p  style="font-size:16px;"><b>比赛状态：</b><%=contest.getContestStatus() %></p>        
        <p  style="font-size:16px;"><b>比赛时间：from </b><%=contest.getBeignTime() %> <b>to</b> <%=contest.getEndTime() %></p> 
        </li>
        </ul>       
        <p  style="font-size:16px;"><b>比赛简介：</b><%=contest.getContestContent() %></p>
    </div>    
    <div class="mainbox">    
    <%
    	if(member==null)
    	{
    		%>
        	<h2>加入比赛</h2>
        	 <div align="center" id="join">点击<a href="<%=path %><%=PathInfo.LOGIN%>">登陆</a>参加比赛        	 
        	 </div>        
        		<%    
    	}
    	else if(myWorks!=null)
    	{   
    		out.println("<h2>我的成绩</h2>");
    		if(myWorks.getIsAllow()== Works.request)
    		{
    			%>
    			<div align="center" id="join">您的作品还在审核中...</div>
    			<%
    		}else if(myWorks.getIsAllow()== Works.Refuse)
    		{
    			 %>
    			 <div align="center" id="join">您的作品审核未通过<a href="<%=path %><%=PathInfo.PostWorks%>?ContestID=<%=contest.getContestID() %>">点此继续参加</a></div>
    			 <%
    		}else if(myWorks.getIsAllow()== Works.none)
    		{
   			 %>
			 <div align="center" id="join">您尚未参加比赛<a href="<%=path %><%=PathInfo.PostWorks%>?ContestID=<%=contest.getContestID() %>">点此参加</a></div>
			 <% 			
    		}
    		else {
    		%>   
    			<ul class="colsex">
		<li class="colsf">  		
       			 <p style="font-size:16px;"><b>作品名称：</b><a href="<%=path %><%=PathInfo.DesignworksDetail%>?WorksID=<%=myWorks.getWorksID() %>"><%=myWorks.getName() %></a></p>
       			 <p style="font-size:16px;"><b>创作地点：</b><%=myWorks.getCreativePlace() %></p>
       			 <p style="font-size:16px;"><b>评论次数：</b><%=myWorks.getCommentTimes() %></p>
			     <p style="font-size:16px;"><b>上传时间：</b><%=myWorks.getLoadTime() %></p>
			             </li>
		<li class="colsl">
			     <p style="font-size:16px;"><b>总浏览量：</b><%=myWorks.getVisitTimes() %></p>
			     <p style="font-size:16px;"><b>平均得分：</b><%=myWorks.getAvgScore() %></p>
			     <p style="font-size:16px;"><b>获奖情况：</b><%=myWorks.getAwards() %></p>        
		         <p style="font-size:16px;"><b>打分次数：</b><%=myWorks.getScoreTimes() %></p>
		         </li>
	</ul>
    		<%
    		}
    	}else
    	{
    		%>
    	<h2>加入比赛</h2>
    	 <div align="center" id="join">您尚未参加比赛<a href="<%=path %><%=PathInfo.PostWorks%>?ContestID=<%=contest.getContestID() %>">点此参加</a></div>        
    		<%    		
    	}
    %>  
    </div>
		<div class="mainbox">
        	<h2>作品展示</h2>
            <label id="rank">作品排列方式：</label>
            <select>
            	<option>按访问量</option>
                <option>按积分</option>
            </select>
			<%
		Collection topWorks = PublicOperator.getContestWorks(contestID,9);
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
				String src =application.getContextPath()+works.getWorksPath()+"Main.jpg";					
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
   	 	<h5 align="center"><a>显示更多</a></h5>
        </div>
    </aside>
</section>
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
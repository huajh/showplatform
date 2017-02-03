<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection" %>
<%@page import="JavaBeans.People.Administor"%>
<%@page import="Path.PathInfo"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.Components.News"%>
<%@page import="JavaBeans.Contest.Contest"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>处于评分阶段的比赛</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />

  </head>
<body>
<div id="layout" align="center">	
<h3 align="center">处于评分阶段的比赛</h3>
<table align="center" bordercolor="#CCCCCC" border="1" height="70" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
	<tr>
    	<th width="100">比赛类型</th>
        <th width="150">比赛名称</th>
        <th width="100">主办方</th>
        <th width="150">比赛开始时间</th>
        <th width="150">比赛结束时间</th>
        <th width="120">结束比赛</th>
    </tr>
    <%
    	Collection contestCollection = PublicOperator.getScoringContest();
    	if(contestCollection!=null)
    	{
    		Iterator it = contestCollection.iterator();
    		while(it.hasNext())
    		{
    			Contest contest =(Contest)it.next();
	    		out.println("<tr>");
	    		out.println("<th width='100'>");
	    		out.println(contest.getContestType());
	    		out.println("</th>");
	    		
	    		out.println("<th width='150'>");
	    		out.println(contest.getTitle());
	    		out.println("</th>");
	    		
	    		out.println("<th width='100'>");
	    		out.println(contest.getOrganizerID());	    		
	    		out.println("</th>");
	    			    		
	    		out.println("<th width='150'>");
	    		out.println(contest.getBeignTime());
	    		out.println("</th>");
	    		
	    		out.println("<th width='150'>");
	    		out.println(contest.getEndTime());
	    		out.println("</th>");	
	    		
	    		out.println("<th width='120'>");    		
	    	%>
	    		<form action="<%=basePath %>servlet/FinishContest?contestID=<%=contest.getContestID()%>" method="post" class="niceform" target="_self">
	    	<%
	    		out.println("<input type='submit' align='middle' name='permit' value='结束比赛' />");
	    		out.println("</th>");
	    	%>
	    		</form>
	    	<%
	    		out.println("</tr>");
    		}
    	}
     %>
</table>
</div>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection" %>
<%@page import="JavaBeans.People.Administor"%>
<%@page import="Path.PathInfo"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.Works.Works"%>
<%@page import="JavaBeans.Contest.Contest"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>未审核作品列表</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />
	<script src="../JS/jquery-1.7.1.min.js"></script>
	<script>
	$(function(){
		$('#choice').change(function(){
			location.href = 'ExamineWorksList.jsp?ContestID=' + $(this).val();
		});
	})();
	</script>
  </head>
<style type="text/css">
#choice{ font-size:16px;}
</style>
<body>
<div id="layout"><div align="center">	 
</div><h3 align="center">待审核作品列表</h3><div align="center"> 
选择比赛：<select id="choice" name="ContestID" size="1"> 
		<option value=""></option>
	<%
   		String contestID = request.getParameter("ContestID");
		Collection contestCollection = PublicOperator.getUploadingContest();
    	if(contestCollection!=null)
    	{
    		Iterator it = contestCollection.iterator();
    		while(it.hasNext())
    		{
    			Contest contest = (Contest)it.next();    
    			out.println("<option value="+contest.getContestID());
    			if (null != contestID && contestID.equals(String.valueOf(contest.getContestID()))){
    			out.println(" selected='selected'");
    			}			
    			out.println(">"); 
    			out.println(contest.getTitle());
    			out.println("</option>");   			    			
    		}
   		}
	 %>
</select>
<br> 
</div><table align="center" bordercolor="#CCCCCC" border="1" height="50" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
	<tr>
    	<th width="100">作品名称</th>
        <th width="100">创作地点</th>     
        <th width="100">作者名称</th>
        <th width="100">上传时间</th>
        <th width="100">作品下载</th>
        <th width="120">是否通过审核</th>
    </tr>
     <%
    	if (null == contestID || contestID.equals(""))
    	 return;   	
    	//Collection worksCollection = PublicOperator.getUnallowedWorks(Integer.parseInt(contestID));
    	Collection worksCollection = PublicOperator.getContestWorks(Integer.parseInt(contestID));
    	if(worksCollection!=null) 
		{
    		Iterator it = worksCollection.iterator();
    		while(it.hasNext())
    		{
    			Works works = (Works)it.next();
    			
	    		out.println("<tr>");
	    		out.println("<th width='100'>");
	    		out.println(works.getName());
	    		out.println("</th>");
	    		
	    		out.println("<th width='100'>");
	    		out.println(works.getCreativePlace());
	    		out.println("</th>");
	    		
	    		out.println("<th width='100'>");
	    		out.println(works.getMemberID());	    		
	    		out.println("</th>");
	    			    		
	    		out.println("<th width='100'>");
	    		out.println(works.getLoadTime());
	    		out.println("</th>");
	    			    		
	    		out.println("<th width='100'>");
	    		out.println("<a id='edit' href='"+ path+ works.getWorksPath()+"'>作品下载</a>");
	    		out.println("</th>");
   		
	    		out.println("<th width='120'>");
	    	%>
	    		<form action="<%=basePath %>servlet/AcceptWorks?worksID=<%=works.getWorksID()%>" method="post" class="niceform" target="_self">
	    		<input type="hidden" name="ContestID" value="<%= works.getContestID() %>" />
	    	<%
	    		out.println("<input type='submit' name='permit' value='是' />&nbsp&nbsp&nbsp");
	    	%>
	    	</form>
	    		<form action="<%=basePath %>servlet/RefuseWorks?worksID=<%=works.getWorksID()%>" method="post" class="niceform" target="_self">
	    		<input name='unpermit' type='submit' value='否' />
	    		</form>
	    	<%
	    		out.println("</th>");
	    		out.println("</tr>");
    		}
    	}
     %>
</table>
</div>
   
</table>
</div>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection" %>
<%@page import="JavaBeans.People.Administor"%>
<%@page import="Path.PathInfo"%>
<%@page import="InterOperate.PublicOperator"%>
<%@page import="JavaBeans.People.Member"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Administor administor = (Administor)session.getAttribute("Administor");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主办方身份认证</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style.css" type="text/css" />

  </head>
  
  <body>
<div id="layout" align="center">	
<h3 align="center">待认证主办方列表</h3>
<table align="center" bordercolor="#CCCCCC" border="1" height="70" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
	<tr>
    	<th width="100">用户名</th>
        <th width="100">真实姓名</th>
        <th width="50">性别</th>
        <th width="50">年龄</th>
        <th width="100">出生日期</th>
        <th width="150">邮箱</th>
        <th width="100">毕业学校</th>
        <th width="150">是否同意申请比赛</th>
    </tr>
    <%
    	Collection memberCollection = PublicOperator.getRequestHost();
    	if(memberCollection!=null)
    	{
    		Iterator it = memberCollection.iterator();
    		while(it.hasNext())
    		{
    			Member member = (Member)it.next();
    			
    			out.println("<tr><th width='100'>");
    			out.println(member.getMemberID());
    			out.println("</th>");
    			
    			out.println("<th width='100'>");
    			out.println(member.getRealName());
    			out.println("</th>");
    			
    			out.println("<th width='50'>");
    			out.println(member.getSex());
    			out.println("</th>");
    			
    			out.println("<th width='50'>");
    			out.println(member.getAge());
    			out.println("</th>");
    			
    			out.println("<th width='100'>");
    			out.println(member.getBirthday());
    			out.println("</th>");
    			
    			out.println("<th width='150'>");
    			out.println(member.getRegisterEmail());
    			out.println("</th>");
    			
    			out.println("<th width='100'>");
    			out.println(member.getGraduateSchool());
    			out.println("</th>");
    			    			
    			out.println("<th width='150'>");   			
	    	%>
	    		<form action="<%=basePath %>servlet/AcceptHost?memberID=<%=member.getMemberID()%>" method="post" class="niceform" target="_self">
	    	<%
	    		out.println("<input type='submit' name='permit' value='是' />&nbsp&nbsp&nbsp");
	    	%>
	    		<form action="<%=basePath %>servlet/RefuseHost?memberID=<%=member.getMemberID()%>" method="post" class="niceform" target="_self">
	    		<input name='unpermit' type='submit' value='否' />
	    	<%
	    		out.println("</th>");
	    	%>
	    		</form>
	    	<%
	    		out.println("<tr>");
    		}
    	}
     %>
</table>
</div>
</body>
</html>

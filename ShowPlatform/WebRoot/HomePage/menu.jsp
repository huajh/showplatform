<%@page import="Path.PathInfo"%>
<%@page import="JavaBeans.People.Member"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Member member = (Member)session.getAttribute("Member");
	
%>

<menu id="main_menu">
    	<li ><a href="<%=path %><%=PathInfo.HOMEPAGE %>">主页</a></li>
        <li ><a href="<%=path %><%=PathInfo.ContestList %>">赛事</a></li>
        <li><a href="<%=path %><%=PathInfo.DesignersList %>">人气选手</a></li>
        <li><a href="<%=path %><%=PathInfo.DesignWorksList %>">人气作品</a></li>
        <% if(member!=null){
        	%>
        	<li><a href="<%=path %><%=PathInfo.InfoCenter %>">个人中心</a></li>
        	<%
        }        
        %>       
        <li><a href="<%=path %>/HomePage/about.jsp">关于我们</a></li>
        
</menu>
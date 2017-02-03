<%@page import="JavaBeans.People.Member"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	Member member = (Member)session.getAttribute("Member");
	if(member==null)
	{
		%>
		请<a href="<%=path %>/HomePage/Login.jsp" id="Login">登陆，</a>
		<a href="<%=path %>/HomePage/Register.jsp" id="Register">免费注册</a>
		<% 
	}else{
		%>
		欢迎登录，<a href="<%=path %>/HomePage/InfoCenter.jsp" id="InforConter"><%=member.getMemberID() %></a>
		<a href="<%=path %>/HomePage/Logout.jsp" id="Login">注销</a>
		<a id="new_message">您有新消息</a>
		<% 
	}
%>
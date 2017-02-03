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

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>作品展示平台</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
</head>
<style type="text/css">
h2 { background:#ff911a; padding:2px 6px; border-bottom:1px solid #ed6400; color:#fff; font-size:20px;}
#iBox{ height:300px; margin-bottom:20px;}
#iPhoto{ float:left; margin:10px;}
#iInfor{ float:left; margin-top:10px;text-shadow: 0 -1px 3px #202020;}
</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>

<jsp:include page="menu.jsp"></jsp:include>
<section id="mainContent">

<aside id="main">
	<div id="iBox" >
		<h2>前端开发人员</h2>
        <div id="iPhoto">
        	<img  width="360" height="240" src="aboutus/h_large_SqF5_68d90003498d2f75.jpg" />
        </div>
        <div id="iInfor">
        	<ul>
            	<li>姓名：不告诉你就不告诉</li>
                <li>性别：男</li>
                <li>年龄：20</li>
                <li>常用ID：findingsea</li>
                <li>小号？：？？？</li>
                <li>手机：1X9X9X8X4X7</li>
                <li>邮寄地址：你猜。。。</li>
            </ul>
        </div>
	</div>
    	<div id="iBox" >
		<h2>后台、数据库开发人员1</h2>
        <div id="iPhoto">
        	<img   width="360" height="240"   src="aboutus/h_large_5hm5_6d8e0001019b2f74.jpg" />
        </div>
        <div id="iInfor">
        	<ul>
            	<li>姓名：</li>
                <li>性别：</li>
                <li>年龄：</li>
                <li>常用ID：</li>
                <li>小号？：</li>
                <li>手机：</li>
                <li>邮寄地址：</li>
            </ul>
        </div>
	</div>
    	<div id="iBox" >
		<h2>后台、数据库开发人员2</h2>
        <div id="iPhoto">
        	<img  width="360" height="240"  src="aboutus/h_large_WwEl_615500010c302f76.jpg" />
        </div>
        <div id="iInfor">
        	<ul>
            	<li>姓名：</li>
                <li>性别：</li>
                <li>年龄：</li>
                <li>常用ID：</li>
                <li>小号？：</li>
                <li>手机：</li>
                <li>邮寄地址：</li>
            </ul>
        </div>
	</div>
    
</aside>
</section>

<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>


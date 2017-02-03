<%@page import="Path.PathInfo"%>
<%@page import="JavaBeans.People.Member"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
	if(member==null)
	{
		out.println("<script>");
		out.println("alert('请先登陆~~')");
		out.println("location.href='"+request.getContextPath()+PathInfo.LOGIN+"'</script>");
	}	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
<title>正在进行的比赛</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>
<script language="javascript" type="text/javascript" src="<%=path%>/JS/niceforms.js"></script>
<script language="javascript" type="text/ecmascript" src="<%=path%>/JS/selectB.js"></script>
</head>
<style type="text/css">
select{ width:100px; font-size:15px;}
#row{ width:470px;float:left;margin-left:10px;}
</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="mainContent">
	<aside id="main">
		<div class="mainbox">
        	<h2>正在进行的比赛</h2>
            <ul>
            	<li>比赛名称：</li>
                <li>比赛类型：</li>
                <li>开始时间：</li>
                <li>结束时间：</li>
                <li>当前状态：</li>
                <li>参赛人数：</li>
                <li>已上交作品数量：</li>
                <li>已上交作品打包下载：</li>
            </ul>
        </div>
    </aside>
</section>

<footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>



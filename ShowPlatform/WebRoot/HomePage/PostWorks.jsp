<%@page import="Path.PathInfo"%>
<%@page import="JavaBeans.People.Member"%>
<%@ page language="java" import="java.util.*;" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");
	int ContestID = Integer.parseInt(request.getParameter("ContestID"));
	if(member==null)
	{
		out.println("<script>");
		out.println("alert('请先登陆~~')");
		out.println("location.href='"+request.getContextPath()+PathInfo.LOGIN+"'</script>");
	}else
	{
		if(!member.canPostWorks())
		{
			out.println("<script>");
			out.println("alert('您还没有上传作品的权限~')");
			out.println("location.href='"+request.getContextPath()+PathInfo.HOMEPAGE+"'</script>");			
		}
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
	<title>作品上传</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/index_style.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/form_style.css"/>
	<script language="javascript" type="text/javascript" src="<%=path %>/JS/niceforms.js"></script>
</head>

<style type="text/css">

</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>	
<jsp:include page="menu.jsp"></jsp:include>
<section id="container1">
	<h1>上传作品</h1>
    <form action="<%=basePath %><%=PathInfo.MemberServlet%>/PostWorksServlet?ContestID=<%=ContestID %>" method="post" 
    class="niceform" target="_self"  enctype="multipart/form-data" >
    <br>
    <label for="worksName">作品名称：</label>
	<input type="text" id="textinput" name=Name size="20" />
	<br /><br/>
	
    <label for=CreativePlace>创作地点：</label>
	<input type="text" id="textinput" name="CreativePlace" size="20" /><br /><br/>
    
    <label for="summary">摘要：</label>
	<textarea id="textareainput" name="Summary" rows="15" cols="40"></textarea><br />
    
    <label for="photo">上传主要图片：</label>
    <input type="file" name="file1" /><br /><br />
    <label for="photo">上传附属图片：</label>
    <input type="file" name="file2" /><br /><br />				
    <label for="file">作品附件：</label>
    <input type="file" name="file3" /><br /><br />
	<input type="submit" value="提交" /><input type="button" value="取消" />
</form>

</section>
<section id="preview">
	<h1>图片预览</h1>
    <div id="image">
    <img src="\ShowPlatform\Root\Works\sf14.jpg" />
    </div>
</section>
<footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>


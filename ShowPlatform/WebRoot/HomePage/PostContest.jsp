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
	else
	{
		if(!member.canHostContest())
		{
			out.println("<script>");
			out.println("alert('您还没有主办比赛的权限~')");
			out.println("location.href='"+request.getContextPath()+PathInfo.ContestList+"'</script>");			
		}
	}	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
<title>申请比赛</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>
<script language="javascript" type="text/javascript" src="<%=path%>/JS/niceforms.js"></script>
<script language="javascript" type="text/ecmascript" src="<%=path%>/JS/selectB.js"></script>
</head>
<style type="text/css">
select{ width:100px; font-size:15px;}
#row{ width:470px;float:left;margin-left:10px;}
#rowBtn{ float:right;margin-right:250px; }
#rowBtn input{font-size:17px;}
</style>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="container">
	<h1>申请比赛</h1> 
    <form action="<%=basePath %><%=PathInfo.MemberServlet%>/PostContestServlet" method="post"
     target="_self"  enctype="multipart/form-data"> 
    <br>
    <div id="row">
    <label for="contestName">比赛名称：</label>
	<input type="text" id="textinput" name="Title" size="40" />
	<% String msg = (String)request.getParameter("msg");
		if(msg!=null)
			out.println("<font color='red'>"+msg+"</font>");
	%>
	<br /><br/>
	<label for="contestName">比赛类型：</label>
    <select size="1"  name="ContestType"  >
		<option value="Design">设计类</option>
		<option value="Photography">摄影类</option>
		<option value="Literature">文学类</option>
	</select>
	<% String msg2 = (String)request.getParameter("msg2");
		if(msg2!=null)
			out.println("<font color='red'>"+msg2+"</font>");
	%>	
	</div>
	<div id="row">
    <label for="beginTime">开始时间：</label>
    <input type="text" onfocus="HS_setDate(this)" name="BeignTime"><br /><br />
    <label for="endTime">结束时间：</label>
    <input type="text" onfocus="HS_setDate(this)" name="EndTime"><br /><br />
    </div>

    <label for="intro">比赛说明：</label>
    <% String msg3 = (String)request.getParameter("msg3");
		if(msg3!=null)
			out.println("<font color='red'>"+msg3+"</font>");
	%>
    <br />    
	<textarea id="textareainput" name="ContestContent" rows="15" cols="100"></textarea><br />
	    <label for="photo">上传图片：</label>	  
    <input type="file" name="upload" /><br />
      <label>支持 jpg, gif, png jpeg格式的图片，不要超过 2MB。建议图片尺寸大于 100×100</label><br>
      <div id="rowBtn">
	<input type="submit" value="提交" /><input type="button" value="取消" />
	</div>
</form>
</section>

<footer id="footer">
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>



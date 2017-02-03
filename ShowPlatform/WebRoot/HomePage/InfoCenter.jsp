<%@page import="InterOperate.SortType"%>
<%@page import="JavaBeans.Works.Works"%>
<%@page import="Filter.PictureFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Path.PathInfo"%>
<%@page import="JavaBeans.People.Member"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
	Member member = (Member)session.getAttribute("Member");	
	if(member == null)
	{
		out.println("<script>");
		out.println("alert('请先登陆~~')");
		out.println("location.href='"+request.getContextPath()+PathInfo.LOGIN+"'</script>");
		return ;
	}
	Date date  = member.getBirthday();
	String  birthday ="";
	if(date!=null)
	{
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		birthday = format.format(date);
	}
	String headPath = path+member.getMemberPath()+"head.jpg";
	
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>作品展示平台</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/personalInformation_style.css">
<script language="javascript" type="text/javascript" src="<%=path%>/JS/userinfo.js"></script>
</head>
<style type="text/css">
#edit
{
	float:right;
	margin-right:200px;
	color:#999;
	font-size:15px;
}
#pwd
{
	float:right;
	margin-right:20px;
	color:#999;
	font-size:15px;
}
#join{ font-size:24px; color:#999999; }
#join a{ font-size:18px;}
</style>
<script type="text/javascript">

</script>
<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="information">
	<div id="infor_head">		
    	<a><img src="<%=headPath%>" width="300"></a>
    </div>
    <div id="infor">
    	 <div id="infor_name">
    		<h1><%=member.getMemberID() %></h1>
    	</div>
        <br>
        <div id="infor_intro">
    		<h4>视觉设计</h4>
            	<p>互动设计师，热爱艺术插画，喜欢自由无拘的感觉，喜欢在回忆里寻找灵感，用绘画记录事物。</p>
				<p>我的微博：<a>http://weibo.com/veiray</a></p>
        </div>
    </div>
</section>
<hr>
<section id="record">
	<h1>档案：<a href="<%=path %><%=PathInfo.EditInfo%>" id="edit">编辑</a><a href="<%=path %><%=PathInfo.EditPwd%>"  id="pwd">修改密码</a></h1>	
    <ul>
    	<li><b>姓名：</b><%=member.getRealName() %></li>    
    	<li><b>性别：</b><%=member.getSex() %></li>	
    	<li><b>年龄：</b><%=member.getAge() %></li>
    	<li><b>生日：</b><%=birthday%></li>
    	<li><b>星座：</b><%=member.getConstellation() %></li>    	      
    	<li><b>职业：</b><%=member.getProfession() %></li>
        <li><b>来自：</b><%=member.getLiveProvince()%><%=member.getLiveCity() %></li>
        <li><b>邮箱：</b><a ><%=member.getRegisterEmail() %></a></li>
        <li><b>个人简历</b><%=member.getIntroduction() %>只是测试：这是个喧嚣的世界我从未觉得安静过他的繁荣，他的昌盛带给人们却只是更多的疲惫更多的抱怨于是我捂住双耳不去听他的疲惫，不去听他的昌盛不去听他的繁荣，也不去听他的抱怨于是我以为我的世界安静了只是，这世上总有那么一人</li>
    </ul>
</section>
<hr>
<section id="works">
	<h1>作品展示</h1>
    <br>
	<div id="slider">	
	<%
	Collection topWorks = member.getMyWorksBySort(SortType.OrderByAvgScore);	
	if(topWorks!=null)
	{		
		System.out.println("not null");
		Iterator it = topWorks.iterator();
		while(it.hasNext())
		{						
			Works works = (Works)it.next();
			String name = works.getName();
			System.out.println("name: "+name);
			String href = path+PathInfo.DesignworksDetail+"?WorksID="+works.getWorksID();
			String src =application.getContextPath()+works.getWorksPath()+"/Main/"+"main.jpg";			
			System.out.println("works.getWorksPath(): "+works.getWorksPath());
			System.out.println(href);
			System.out.println(src);
			%>
			<div class="slide">
			<a href="<%=href%>"><img class="diapo" src="<%=src%>" alt=""></a>
				<div class="text">
				  <span class="title"><%=name %></span>
				</div>
			</div>
			<%
		}
	}
	%>
	</div>

<script type="text/javascript">
/* ==== start script ==== */
slider.init();
</script>
</section>
<hr>
<section id="record">
	<h1>我主办的比赛：</h1>
	    	<%
    		Collection C_Contest = member.getMyHostContest();
    		if(C_Contest == null)
    		{
    			%>
    			<div align="center" id="join">点击<a href="<%=path %><%=PathInfo.LOGIN%>">登陆</a>参加比赛  </div>
    			<%	
    		}else
    		{	
    		%>
    			<ul>
    			<%
    			
    			%>    		 		  		
    			<li><b>比赛一</b><a ><label id="ing">（正在进行）</label></a></li>
		        <li><b>比赛二</b><a href="<%=path %><%=PathInfo.EditMyContestInfo%>"><label id="apply">（正在申请）</label></a></li>
		        <li><b>比赛三</b><label id="ed">（已结束）</label></li>
		        <li><b>比赛四</b><label id="ed">（已结束）</label></li>
		        <li><b>比赛五</b><label id="ed">（已结束）</label></li>
		        
		        </ul>		   
    		<%
    		}
    		
    	%>
</section>
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
<%@page import="JavaBeans.Components.Score"%>
<%@page import="JavaBeans.Components.Comment"%>
<%@page import="InterOperate.SortType"%>
<%@page import="java.io.File"%>
<%@page import="Filter.PictureFilter"%>
<%@page import="JavaBeans.Works.DesignWorks"%>
<%@page import="JavaBeans.Works.Works"%>
<%@page import="JavaBeans.People.Member"%>
<%@page import="Path.PathInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Member member = (Member)session.getAttribute("Member");	
	int worksID = Integer.parseInt(request.getParameter("WorksID"));
	DesignWorks works = new DesignWorks(worksID);	
	if(!works.isExist())
	{
		out.println("<script>");
		out.println("alert('该作品不存在')");
		out.println("location.href='"+request.getContextPath()+PathInfo.DesignersList+"'</script>");
		
	}
	works.load();
	Member Designer = new Member(works.getMemberID().trim());
	Designer.load();
	String filePath = application.getRealPath("/")+Designer.getMemberPath();
	File file = new File(filePath);
	File[] files = file.listFiles(new PictureFilter());
	int i;
	for(i=0;i<files.length;i++)
	{
		if(files[i].getName().replaceAll("[.][^.]+$", "").equalsIgnoreCase("head"))
		{
			break;
		}				
	}
	String headPath = path+Designer.getMemberPath()+files[i].getName();			
	System.out.println(headPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>作品展示平台</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/personalInformation_style.css">
<script language="javascript" type="text/javascript" src="<%=path%>/JS/niceforms.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/JS/userinfo.js"></script>
</head>
<script type="text/javascript">

</script>
<style type="text/css">
select{ width:50px; font-size:15px;}
#score{ width:35px;}
#con{ color:#FF9900; font-size:18px; }
#date{ color:#999999; font-size:15px;}
#rely{ float:right;}
#join{ font-size:24px; color:#999999; }
#join a{ font-size:18px;}
.bubble-box
        {
            background: #EEE;
            width: 200px;
            margin-bottom: 30px;
        }
        .bubble-box .wrap
        {
            background: #EEE; /* 修正IE6 */
            _position: relative;
            _z-index: 10;
        }
        .arrow-top
        {
            border-left: 20px solid #EEE;
            border-top: 20px solid #FFF;
            margin-left: 20px;
        }
        .arrow-top, .arrow-bottom
        {
            width: 140px;
        }
        .arrow-top .wrap, .arrow-bottom .wrap
        {
            width: 550px;
            padding: 12px 10px 12px 10px;
            margin-left: -40px;
        }
</style>
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
    		<h1><%=Designer.getMemberID() %></h1>
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
<section id="showImage">	
	<%
		String worksPath = application.getRealPath("/")+works.getWorksPath();		
		String mainpicPath = path+works.getWorksPath()+"/Main/main.jpg";		
		System.out.println("mainpicPath: "+mainpicPath);
		%>
		<div id="image"><img src="<%=mainpicPath%>" align="absmiddle" alt="" width="500" height="350" ></div>	
		<% 		
		String morepicPath = worksPath +"/more/";	
		File morefile = new File(morepicPath);		
		File[] morePics = morefile.listFiles(new PictureFilter());		
		
		for(int ii=0;ii<morePics.length;ii++)
		{			
			String morepath =  path+works.getWorksPath()+"/more/"+morePics[ii].getName();
			System.out.println("morepath : "+morepath);
			%>
			<div id="image"><img src="<%=morepath%>" align="absmiddle"  width="500" height="350"  alt=""></div>				
			<% 					
		}	
	%>        
</section>
<br>
	<%
		Collection comments = works.getALlComment();
		if(comments!=null)
		{		
			System.out.println("not null");
			Iterator it = comments.iterator();
			while(it.hasNext())
			{						
				Comment comment = (Comment)it.next();				
				String memberID = comment.getMemberID();
				String replymemberID = comment.getReplyMemberID();
				String commentContent = comment.getCommentContent();
				Date commentTime = comment.getCommentTime();
				int score = Score.getScore(memberID,worksID);
			%>
			<section id="content">
				<label id="con"><%=memberID %></label>
			    <label id="date">发表于<%=commentTime %></label>
			    <label id="date">评分：<%=score %></label>
			    <div class="bubble-box arrow-top">
			    <div class="wrap">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%
					if(replymemberID!=null)
					{
						%>
						<a href="<%=path %><%=PathInfo.DesignerDetail%>?MemberID=<%=replymemberID %>">@<%=replymemberID %></a>
						<%
					}
				%>
				<%=commentContent %>
				</p>
			    </div></div>
			    <a id="rely" target="_self" href="#new_content">回复他</a>
			</section>
			<br>
			<br>
			<%
			}
		}else
		{
			%>
		<section id="content">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>还没人评论呢，快抢沙发~~
<%--		  <a id="rely" target="_self" href="#new_content">回复他</a>--%>
		</section>
		<br>
		<br>
				<%
		}
	%>
<a rel="#new_content"></a>
<section id="new_content">

	<%
		if(member!=null)
		{
			%>
			<form action="<%=basePath%><%=PathInfo.MemberServlet%>/DoCommentServlet?worksID=<%=worksID %>" method="post" >
			<h1>发表评论</h1>
		
		    <textarea rows="10" cols="80" name="Commentcontent"></textarea>
		    <br>
		    评分：<select name = "score">
		    	<option value = '1'>1</option>
		    	<option value = '2'>2</option>
		    	<option value = '3'>3</option>
		    	<option value = '4'>4</option>
		    	<option value = '5'>5</option>
		    	<option value = '6'>6</option>
		    	<option value = '7'>7</option>
		    	<option value = '8'>8</option>
		    	<option value = '9'>9</option>
		    	<option value = '10'>10</option>
		    </select>
		    <input type="submit" value="提交">
		   </form>
			<% 
		}else
		{
			%>
        	<div align="center" id="join">点击<a href="<%=path %><%=PathInfo.LOGIN%>">登陆</a>发表评论        	 
        	</div>   
			<%
		}
	%>
</section>
<hr>
<section id="works">
	<h1>作品展示</h1>
    <br>
	<div id="slider">	
	<%
	Collection topWorks = Designer.getMyWorksBySort(SortType.OrderByAvgScore);	
	if(topWorks!=null)
	{		
		System.out.println("not null");
		Iterator it = topWorks.iterator();
		while(it.hasNext())
		{						
			Works works2 = (Works)it.next();
			String name = works2.getName();
			System.out.println("name: "+name);
			String href = path+PathInfo.DesignworksDetail+"?WorksID="+works2.getWorksID();
			String src =application.getContextPath()+works2.getWorksPath()+"/Main/"+"main.jpg";			
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
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
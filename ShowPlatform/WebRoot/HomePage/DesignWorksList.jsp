<%@page import="JavaBeans.Works.Works"%>
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
<link rel="stylesheet" type="text/css" href="<%=path %>/CSS/personalInformation_style.css">
<script language="javascript" type="text/javascript" src="<%=path%>/JS/userinfo.js"></script>
</head>
<script type="text/javascript">

</script>

<body>
<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<section id="mainContent">

	<aside id="main">
    <div class="mainbox">
		<h2>人气作品</h2>
			<%
		Collection topWorks = PublicOperator.getTopWorks(Contest.Start_Score,12);
		if(topWorks!=null)
		{		
			Iterator it = topWorks.iterator();
			int i;
			for(i=0;i<9 && it.hasNext();i++)
			{	
				System.out.println(i);
				
				Works works = (Works)it.next();
				String name = works.getName();
				String href = path+PathInfo.DesignworksDetail+"?WorksID="+works.getWorksID();
				String src =application.getContextPath()+works.getWorksPath()+"/Main/main.jpg";				
				System.out.println(href);
				System.out.println(src);
				if(i%3==0)
				{
					%>
					<ul class="ulflow">				
					<li class="liflow"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<%
				}else if(i%3==1)  //1
				{	
					%>					
					<li class="litest_last"><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					<% 				
				}
				else 
				{
					%>
					<li ><a href="<%=href %>"><img width="305" height="200" alt="" src="<%=src%>"><br><%=name %></a></li>
					</ul>
					<%
				}			
			}	
			if(i!=9)
			{
				out.println("</ul>");
			}
		}	
		%>
    <h5 align="center"><a>显示更多</a></h5>
        </div>
    </aside>

</section>

<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
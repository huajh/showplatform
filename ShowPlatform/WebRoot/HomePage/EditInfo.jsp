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
	if(member==null)
	{
		out.println("<script>");
		out.println("alert('请先登陆~~')");
		out.println("location.href='"+request.getContextPath()+PathInfo.LOGIN+"'</script>");
	}		
	String headPath = path+member.getMemberPath()+"head.jpg";
%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset= "utf-8"/>
<title>设置个人信息</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/index_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/form_style.css"/>
<script language="javascript" type="text/ecmascript" src="<%=path%>/JS/selectB.js"></script>
</head>
<style type="text/css">
#row{ width:470px;float:left; margin-left:5px;}
#rowContent{ width:800px;float:left; margin-left:5px;}
#rowDetail{float:right;margin-right:170px;margin-bottom:20px;}
#rowSex{float:left;margin-left:73px;margin-bottom:20px;}
#headImg{float:left;margin-left:0;width:100px;}
#headTxt{float:left;margin-left:9px;width:300px;}
#upload{font-family:Arial, Helvetica, sans-serif;font-size:15px;color:#404040;}
#rowBtn{ float:right;margin-right:20px;  }
#rowBtn input{font-size:17px;}
#rowBtn1{ float:right;margin-right:5px;}
#rowBtn2{ float:right;  }
</style>
<script type="text/javascript">
function show(){
	//	var PicPath = document.getElementById("upload").value;
		document.getElementById("upload").innerHTML="<IMG src="+PicPath+"/>";
	   }
</script>
<body>

<header id="header_style">
<jsp:include page="Head.jsp"></jsp:include>
</header>
<jsp:include page="menu.jsp"></jsp:include>
<div id="container">
<form action="<%=basePath%><%=PathInfo.MemberServlet%>/EditInfoServlet" method="post"
	name = "editInfo" target="_self" enctype="multipart/form-data" >
	<h1>修改个人信息</h1>
    <br>
    <div id="row">
    <div id="head">
    	<div id="headImg">
    	<img width="100" height="70" src="<%=headPath%>" />
    	</div>
    	<div id="headTxt">	
    	<input type="file" name="head" id="upload" />
    	<label>支持 jpg, gif, png jpeg 格式的图片，不要超过 2MB。建议图片尺寸大于 100×100</label><br>
    	<input id="upload" style="color:#FFF;background-color:#F60;" type="button" name="upload" value="开始上传" onclick="javascript:show();">
    	</div>
    </div>
    <br><br><br><br>
    
	<div id="rowSex">
    <label for="sex">性别：</label>
    <input type="radio" name="Sex" checked=checked  value="男" /><label for="option1">男</label>
    <input type="radio" name="Sex" value="女" /><label for="option2">女</label>
  	</div>
    <div id="rowDetail">
    <label for="age">年龄：</label>
    <input type="text" id="textinput" name="Age" value = "<%=member.getAge() %>" size="20" /></div>
        <div id="rowDetail">
    <label for="age">姓名：</label>
    <input type="text" id="textinput" name="RealName"  value ="<%=member.getRealName() %>" size="20" /></div>
    <div id="rowDetail">
    <label for="birthday">出生日期：</label>    
    <input type="text" onfocus="HS_setDate(this)" value = "<%=member.getBirthday() %>" name="Birthday">
	<% String msg = (String)request.getParameter("msg");
		if(msg!=null)
			out.println("<font color='red'>"+msg+"</font>");
	%>	
    </div>
    </div>
    <div id="row">
    <div id="rowDetail">
    <label for="email">邮箱：</label>
    <input type="text"  name="email" value="<%=member.getRegisterEmail() %>" size="20" />
    </div><br>
    <div id="rowDetail">
    <label for="Constellation">星座：</label>
    <input type="text" name="Constellation" value="<%=member.getConstellation() %>" size="20" /></div><br>
    <div id="rowDetail">
    <label for="LiveProvince">现居省份：</label>
    <input type="text" name="LiveProvince" vlaue ="<%=member.getLiveProvince() %>" size="20" /></div><br>
    <div id="rowDetail">
    <label for="LiveCity">现居城市：</label>
    <input type="text"  name="LiveCity" value ="<%=member.getLiveCity() %>" size="20" /></div><br>
    <div id="rowDetail">
    <label for="GraduateSchool">毕业学校：</label>
    <input type="text" name="GraduateSchool" value="<%=member.getGraduateSchool() %>" size="20" /></div><br>
    <div id="rowDetail">
    <label for="Profession">职业：</label>
    <input type="text" name="Profession" value = "<%=member.getProfession() %>"size="20" /></div><br>
	</div>
	<div id="rowContent">
	<label for="Introduction">个人简介：</label>
	<textarea id="textareainput" name="Introduction"  rows="5" cols="100"></textarea><br />
	<br />
	<div id="rowBtn">
	<div id="rowBtn2">
	<input type="submit" value="提交" />
	</div>
	<div id="rowBtn1">
	<input type="button" value="取消" />
	</div>
	</div>
	</div>
</form>
</div>
<footer id="footer">
<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>

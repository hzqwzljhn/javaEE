<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<base target="_blank">
<link rel="stylesheet" href="style/backstage.css">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		
<script type="text/javascript">
function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); 
　　 return (arr=document.cookie.match(reg))?unescape(arr[2]):null;
}
function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
}
function chenkuserid(){
	id=getCookie("userid");
	console.log(id)
	if(id==null||id=="")
	{
		alert("您还没有登录，请登录...");
	window.location.href="login.jsp";
	}
}

var xmlhttp;
if (window.XMLHttpRequest)
{
    xmlhttp=new XMLHttpRequest();
}
else
{
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");    
}
function check(){


var aaa="method=logout";

xmlhttp.open("GET","/bigwork/Userservlet?"+aaa,true);
xmlhttp.onreadystatechange=function()
{
	if (xmlhttp.readyState==4 && xmlhttp.status==200)
	{	
		if(xmlhttp.responseText==111){
			alert("退出成功")
			delCookie("userid");
			window.location.href="login.jsp";
		}
		
	}
};
xmlhttp.send();

}

</script>
</head>
<body onload='chenkuserid()'>
	<div class="head">
		<div class="logo fl">
			<a href="#"></a>
		</div>
		<h3 class="head_text fr">平时成绩管理系统</h3>
	</div>
	<div class="operation_user clearfix">

		<div class="link fr">
			<a href="manager.jsp" target="_parent" class="icon icon_i">首页</a><span></span><a
				href="" target="_parent" onclick="check()"
				class="icon icon_e">退出</a>
		</div>
	</div>
	<div class="content clearfix">
		<div class="main">
			<div class="cont">
				<!--右侧内容-->
				<iframe src="main.jsp" name="manage1"
					style="width: 100%; height: 100%"></iframe>
			</div>
		</div>
		<div class="menu">
			<div class="cont">
				<div class="title">管理员</div>
				<ul class="mList">
					<li>
						<h3>
							<a href="TeacherServlet?method=loadAllTeacher" target="manage1"
								style="font-size: 18px">教师管理</a>
						</h3>

					</li>

				</ul>
			</div>
		</div>
	</div>

</body>
</html>
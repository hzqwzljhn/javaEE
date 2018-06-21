<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--登录页面-->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>平时成绩管理系统</title>
<link type="text/css" rel="stylesheet" href="style/reset.css">
<link type="text/css" rel="stylesheet" href="style/main.css">
<script type="text/javascript">

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

var userid=into.userid.value
var password=into.password.value
var aaa="method=logincheck&userid="+userid+"&password="+password;

xmlhttp.open("GET","/bigwork/Userservlet?"+aaa,true);
xmlhttp.onreadystatechange=function()
{
	if (xmlhttp.readyState==4 && xmlhttp.status==200)
	{
		if(xmlhttp.responseText==11){
			console.log("teacher");
			window.location.href="teacher.jsp";
		}else if(xmlhttp.responseText==12){
			window.location.href="manager.jsp";
		}else{
			alert("账号或密码错误，请重新输入");
			window.location.href="login.jsp";
		}
		
	}
};
xmlhttp.send();

}

</script>
</head>

<body>
	<div class="headerBar">
		<div class="logoBar login_logo">
			<div class="comWidth">
				<div class="logo fl"></div>
				<h3 class="welcome_title">平时成绩管理系统 欢迎登陆</h3>
			</div>
		</div>
	</div>

	<div class="loginBox">
		<div class="login_cont">
			<form method="post" action="" id="into">
				<ul class="login">
					<li class="l_tit">用户名</li>
					<li class="mb_10"><input type="text" name="userid"
						placeholder="请输入用户名" class="login_input user_icon"></li>
					<li class="l_tit">密码</li>
					<li class="mb_10"><input type="password" name="password"
						placeholder="请输入密码" class="login_input user_icon"></li>
					<li><button type="button" value="" onClick="check()"
						class="login_btn"></button></li>
				</ul>
			</form>
			<%
				String errmsg = (String) session.getAttribute("errormsg");
				if (errmsg != null) {
					session.removeAttribute("errormsg");
					out.println(errmsg);
				}
			%>
			<div class="login_partners">
				<p class="l_tit">使用合作方账号登陆网站</p>
				<ul class="login_list clearfix">
					<li><a href="http://webmail.zucc.edu.cn/">学校邮箱</a></li>
					<li><span>|</span></li>
					<li><a href="https://www.163.com">网易</a></li>
					<li><span>|</span></li>
					<li><a href="https://weibo.com/">新浪微博</a></li>
					<li><span>|</span></li>
					<li><a href="https://t.qq.com/">腾讯微薄</a></li>
					<li><span>|</span></li>
					<li><a href="http://www.zucc.edu.cn">浙江大学城市学院</a></li>

				</ul>
			</div>
		</div>

	</div>

	<div class="hr_25"></div>
	<div class="footer">
		<p>
			<a href="#">系统简介</a><i>|</i><a href="#">慕课公告</a><i>|</i> <a href="#">招纳贤士</a><i>|</i><a
				href="#">联系我们</a><i>|</i>客服热线：158 582 67897
		</p>
		<p>Copyright &copy; 20xx - 20xx the
			commitment版权所有&nbsp;&nbsp;&nbsp;京ICP备号&nbsp;&nbsp;&nbsp;京ICP证BXXXX-XXXX号&nbsp;&nbsp;&nbsp;某市公安局XX分局备案编号：123456789123</p>
	</div>

</body>

</html>

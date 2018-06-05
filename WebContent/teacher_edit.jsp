<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
<!-- <script language="javascript">
	var thisURL = document.URL;  
	var getval = thisURL.split('?')[1];  
	var userid = getval.split("=")[1]; 
	//var userid = getval[1].split("&")[0];
	function  showvalf(){  
		alert(thisURL);
	     document.getElementById('userid').value=userid;  
	     //alert(username);
	     //document.getElementById('username').value=username;  
	}
</script> -->
<%
	String userid = new String(request.getParameter("user_id").getBytes("iso-8859-1"),"utf-8");
	String username = new String(request.getParameter("user_name").getBytes("utf-8"),"utf-8");
	String password = new String(request.getParameter("password").getBytes("utf-8"),"utf-8");
	
	request.setAttribute("user_id", userid);
	request.setAttribute("user_name", username);
	request.setAttribute("password", password);
%>
</head>
<body >
	<div class="title">修改教师信息</div>
	<div class="details">
		<div class="details_operation clearfix">
			<!--方法地址-->
			<form action="TeacherServlet?method=modifyTeacher" method="post">
				<table width="35%" border="1" cellpadding="5" cellspacing="0"
					bgcolor="#ccc">
					<tr>
						<td align="center" width="20%">教师编号</td>
						<td><input type="text" name="userid" readonly='readonly' value="${user_id}"></td>
					</tr>
					<tr>
						<td align="center">教师姓名</td>
						<td><input type="text" name="username" value='${user_name}'></td>
					</tr>
					<tr>
						<td align="center">登陆密码</td>
						<td><input type="password" name="password" value='${password }'></td>
					</tr>
					<tr>
						<td align="center">确认密码</td>
						<td><input type="password" name="password2" value=''></td>
					</tr>
				</table>
				<br />
				<div class="bui_select">
					<input type="submit" value="确&nbsp;&nbsp;定" class="add">
				</div>
			</form>
		</div>
	</div>


</body>
</html>
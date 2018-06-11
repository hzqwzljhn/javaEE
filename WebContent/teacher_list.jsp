<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
<script language="jscript">
	
		
	function dd() { 
		if(confirm("请确认是否删除！" )) 
			return true; 
		return false;  
	} 


</script>
</head>
<body>
	<div class="title">教师管理</div>
	<div class="details">
		<div class="details_operation clearfix">
			<div class="bui_select">
				<a href="teacher_add.jsp"> <input type="button" value="添加教师"
					class="btn"></a>
			</div>

		</div>
		<!--表格-->
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="10%">用户编号</th>
					<th width="20%">用户姓名</th>
					<th width="10%">类型</th>
					<th width="20%">创建日期</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr align="center">
						<td id="userid">${user.user_id }</td>
						<td id="username">${user.user_name }</td>
						<td>${user.type}</td>
						<td>${user.createtime}</td>
						<td><a
							href="teacher_edit.jsp?user_id=${user.user_id}&user_name=${user.user_name}&password=${user.password}">
								<input type="button" value="修改" class="btn">
						</a> <a
							href="TeacherServlet?method=deleteTeacher&userid=${user.user_id }"
							onclick="return dd();"><input type="button" value="删除"
								class="btn"></a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
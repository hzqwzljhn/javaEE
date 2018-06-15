<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.model.*"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.edu.zucc.ems.bean.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
</head>
<body>
	<div class="title">课程管理</div>
	<div class="details">
		<div class="details_operation clearfix">
			<div class="bui_select">
				<a href="course_add.jsp"> <input type="button" value="添加课程"
					class="btn"></a>
			</div>

		</div>
		<!--表格-->
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="15%">课程编号</th>
					<th width="20%">课程名称</th>
					<th width="20%">课程时间</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<!--                //数据库写在下面-->
				<%
					List objlist = (List) request.getAttribute("objlist");
					if (objlist != null) {
						for (int i = 0; i < objlist.size(); i++) {
							CourseBean course = (CourseBean) objlist.get(i);
				%>
				<tr>
					<td><%=course.getCourse_id()%></td>
					<td><%=course.getCourse_name()%></td>
					<td><%=course.getCourse_time()%></td>
					<td><a href="course_edit.jsp"><input type="button"
							value="修改课程" class="btn"></a> <a href="#"><input
							type="button" value="删除课程" class="btn"></a> <a
						href="CheckServlet?tab=readCheck&courseid=<%=course.getCourse_id() %>"><input type="button" value="课程详情"
							class="btn"></a></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
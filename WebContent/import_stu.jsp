<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.edu.zucc.ems.bean.*"%>
<%@page import="cn.edu.zucc.ems.model.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
</head>
<body>
	<div class="title">导入学生:<%=request.getAttribute("classname") %></div>
	<div class="details">
		<div class="details_operation clearfix">
			

		</div>
		<!--表格-->
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">学生编号</th>
					<th width="20%">学生姓名</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<%
					List objlist = (List) request.getAttribute("objlist1");
					if (objlist != null) {
						for (int i = 0; i < objlist.size(); i++) {
							StudentBean student = (StudentBean) objlist.get(i);
				%>
				<tr>
					<td><%=student.getStudent_id()%></td>
					<td><%=student.getStudent_name()%></td>
					<td><a href="ClassServlet?act=importstu&stuid=<%=student.getStudent_id()%>&classid=<%= request.getAttribute("classid")%>"><input type="button" value="导入"
							class="btn"></a></td>
				</tr>

			</tbody>
			<%
				}
				}
			%>
		</table>
		<table class="table" cellspacing="0" cellpadding="0"
			style="margin-top: 10px">
			<thead>
				<tr>
					<th width="20%">学生编号</th>
					<th width="20%">学生姓名</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
			<tbody>
				<%
					List objlist2 = (List) request.getAttribute("objlist2");
					if (objlist2 != null) {
						for (int i = 0; i < objlist2.size(); i++) {
							StudentBean student1 = (StudentBean) objlist2.get(i);
				%>
				<tr>
					<td><%=student1.getStudent_id() %></td>
					<td><%=student1.getStudent_name() %></td>
					<td><a href="ClassServlet?act=exportstu&stuid=<%=student1.getStudent_id()%>&classid=<%= request.getAttribute("classid")%>"><input type="button" value="移除"
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
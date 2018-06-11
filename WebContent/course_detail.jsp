<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <% 
  request.setAttribute("studentList", request.getParameter("studentList"));
%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
</head>
<body>
	<div class="title">班级详情</div>
	<div class="details">
		<div class="details_operation clearfix">
			<div class="bui_select" style="margin-right: 5px">
				<a href="CheckServlet?method=homework&classid=<%=session.getAttribute("class_id") %>"> <input type="button"
					value="点名" class="btn"></a>
			</div>

			<div class="bui_select" style="margin-right: 5px">
				<a href="CourseServlet?tab=exam"> <input type="button"
					value="考试情况" class="btn"></a>
			</div>

			<div class="bui_select" style="margin-right: 5px">
				<a href="CourseServlet?tab=final"> <input type="button"
					value="最终成绩" class="btn"></a>
			</div>
		</div>
		<!--表格-->
		<%
			String tab = (String) session.getAttribute("tab");

			if ("homework".equals(tab)) {
		%>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">学生学号</th>
					<th width="20%">学生姓名</th>
					<th width="20%">班级</th>
					<th>到课情况</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentList}" var="student">
					<tr align="center">
						<td>${student.student_id }</td>
						<td>${student.student_name }</td>
						<td>${student.class_id }</td>
						<td>
							<input type="button" name="arrive" value="到课" class="btn"/> 
							<input type="button" name="late" value="迟到" class="btn"/> 
							<input type="button" name="miss" value="缺课" class="btn"/>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<%
			} else if ("exam".equals(tab)) {
		%>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">作业编号</th>
					<th width="20%">作业名称</th>

					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<!--                //数据库写在下面-->
				<tr>
					<td><label> </label></td>
					<td></td>
					<td><a href="#"><input type="button" value="查看考试详情"
							class="btn"></a> <a href="#"><input type="button"
							value="修改考试信息" class="btn"></a></td>
				</tr>
			</tbody>
		</table>

		<%
			} else if ("final".equals(tab)) {
		%>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">学生编号</th>
					<th width="20%">学生姓名</th>
					<th width="20%">最终成绩</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<!--                //数据库写在下面-->
				<tr>
					<td><label> </label></td>
					<td></td>
					<td></td>
					<td><a href="#"><input type="button" value="查看学生详情"
							class="btn"></a> <a href="#"><input type="button"
							value="移除学生" class="btn"></a></td>
				</tr>
			</tbody>
		</table>
		<%
			} else {
				System.out.print("null");
			}
		%>

	</div>
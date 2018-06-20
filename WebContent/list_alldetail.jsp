<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.bean.*"%>
<%@page import="cn.edu.zucc.ems.model.*"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
<link rel="stylesheet" href="style/backstage.css">
</head>
<body>

	<div class="title">成绩详情</div>
	<div class="details">
		 <div class="details_operation clearfix">

        <form action="CheckServlet?tab=modifyfinal&stuid=<%=session.getAttribute("stuid") %>&courseid=<%=session.getAttribute("courseid") %>" method="post">
            最终成绩：
                    <input type="text" name="finalnum" style="height:30px;background-color:#dddddd">
           <br />
            <div class="bui_select" >
                <input type="submit" value="添&nbsp;&nbsp;加" class="add">
            </div>
        </form>
    </div>
		<h3>点名情况</h3>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="10%">学生学号</th>
					<th width="10%">学生姓名</th>
					<th width="10%">学生班级</th>
					<th width="10%">课程名称</th>
					<th width="15%">点名时间</th>
					<th width="10%">到课情况</th>


				</tr>
			</thead>
			<%
				List objlist = (List) request.getAttribute("listcheck");
				if (objlist != null) {
					for (int i = 0; i < objlist.size(); i++) {
						ViewCheckDetail checkbean = (ViewCheckDetail) objlist.get(i);
			%>
			<tbody>

				<tr align="center">
					<td><%=checkbean.getStudent_id()%></td>
					<td><%=checkbean.getStudent_name()%></td>
					<td><%=checkbean.getClass_name()%></td>
					<td><%=checkbean.getChecktime()%></td>
					<td><%=checkbean.getCoursetime()%></td>
					<td><%=checkbean.getState()%></td>
			</tbody>
			<%
				}
				}
			%>
		</table>
		<br />
		<h3>考试情况</h3>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">考生学号</th>
					<th width="20%">考生姓名</th>
					<th width="20%">考试名称</th>
					<th width="20%">考试成绩</th>

				</tr>
			</thead>
			<tbody>
				<!--                //数据库写在下面-->
				<%
					List objlist1 = (List) request.getAttribute("listexam");
					if (objlist1 != null) {
						for (int i = 0; i < objlist1.size(); i++) {
							ViewExamDetailBean exambean = (ViewExamDetailBean) objlist1.get(i);
				%>
				<tr>
					<td align="center"><%=exambean.getStudent_id()%></td>
					<td align="center"><%=exambean.getStudent_name()%></td>
					<td align="center"><%=exambean.getExam_name()%></td>
					<td align="center"><%=exambean.getScore()%></td>
				</tr>

			</tbody>
			<%
				}
				}
			%>
		</table>

	</div>
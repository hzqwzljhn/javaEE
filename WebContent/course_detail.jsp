<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.bean.ViewCountBean"%>
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
<<<<<<< HEAD

<div class="title">课程详情:<%= (String)session.getAttribute("coursename") %></div>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=homework"> <input type="button" value="作业情况" class="btn"></a>
        </div>
        
        <div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=exam"> <input type="button" value="考试情况" class="btn"></a>
        </div>
         
     	<div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=final"> <input type="button" value="最终成绩" class="btn"></a>
        </div>
    </div>
    <!--表格-->
    <%
    String tab=(String)session.getAttribute("tab");
    
    if("homework".equals(tab)){
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
            <td>
                <label>

                </label>
            </td>
            <td>

            </td>
            <td>
                <a href="#" ><input type="button" value="查看作业详情" class="btn"></a>
                
            </td>
        </tr>
        </tbody>
    </table>
    	
    	<%
    }else if("exam".equals(tab)){
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
            <td>
                <label>

                </label>
            </td>
            <td>

            </td>
            <td>
                <a href="#" ><input type="button" value="查看考试详情" class="btn"></a>
                <a href="#" ><input type="button" value="修改考试信息" class="btn"></a>
                
            </td>
        </tr>
        </tbody>
    </table>
    
    <%	
    }else if("final".equals(tab)){
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
       <%
					List objlist = (List) request.getAttribute("listfinal");
					if (objlist != null) {
						for (int i = 0; i < objlist.size(); i++) {
							ViewCountBean count = (ViewCountBean) objlist.get(i);
				%>
        <tr>
            <td><%=count.getStudent_id() %></td>
            <td><%=count.getStudent_name() %>
            </td>
            <td><%=count.getFinal_score() %>
            </td>
            <td>
                <a href="#" ><input type="button" value="查看学生详情" class="btn"></a>
            </td>
        </tr>
        <%}} %>
        </tbody>
    </table>
    	<%
    }else{
    	System.out.print("null");
    }
    %>
   
</div>
=======
	<div class="title">班级详情</div>
	<div class="details">
		<div class="details_operation clearfix">
			<div class="bui_select" style="margin-right: 5px">
				<a
					href="CheckServlet?tab=checklist&courseid=<%=session.getAttribute("course_id")%>">
					<input type="button" value="点名详情" class="btn">
				</a>
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

			if ("check".equals(tab)) {
		%>

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
			} else if ("checklist".equals(tab)) {
		%>
		<div class="details_operation clearfix">
			<div class="bui_select" style="margin-right: 5px">
				<a href="CheckServlet?tab=addcheck&courseid=<%=session.getAttribute("course_id")%>&classid=<%=session.getAttribute("class_id") %>"> <input type="button" value="添加点名"
					class="btn"></a>
			</div>
		</div>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">点名编号</th>
					<th width="20%">点名时间</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${checklist}" var="check">
					<tr align="center">
						<td>${check.check_id }</td>
						<td>${check.check_time }</td>
						<td>
							<a href="CheckServlet?tab=checkdetail&checkid=${check.check_id }&courseid=<%=session.getAttribute("course_id") %>"><input type="button" value="查看详细" class="btn" /></a> 
							<a href="CheckServlet?tab=deletecheck&checkid=${check.check_id }&courseid=<%=session.getAttribute("course_id") %>"><input type="button" value="删除点名" class="btn" /></a> 
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<%
			} else if ("student".equals(tab)) {
		%>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%">学生学号</th>
					<th width="20%">学生姓名</th>
					<th width="20%">学生班级</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentlist}" var="student">
					<tr align="center" id="student">
						<td>${student.student_id }</td>
						<td>${student.student_name }</td>
						<td>${student.class_id }</td>
						<td>
							<a href="CheckServlet?tab=checkin&classid=${student.class_id }&checkid=<%=session.getAttribute("check_id") %>&studentid=${student.student_id }&state=到课"><input type="button" name="arrive" value="到课" class="btn"/></a> 
							<a href="CheckServlet?tab=checkin&classid=${student.class_id }&checkid=<%=session.getAttribute("check_id") %>&studentid=${student.student_id }&state=迟到"><input type="button" name="late" value="迟到" class="btn"/></a>
							<a href="CheckServlet?tab=checkin&classid=${student.class_id }&checkid=<%=session.getAttribute("check_id") %>&studentid=${student.student_id }&state=缺课"><input type="button" name="miss" value="缺课" class="btn"/></a>  
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<%
			} else if ("checkdetail".equals(tab)) {
		%>
		<table class="table" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="10%">学生学号</th>
					<th width="10%">学生姓名</th>
					<th width="10%">学生班级</th>
					<th width="10%">课程名称</th>
					<th width="15%">点名时间</th>
					<th width="10%">到课情况</th>
					<th>修改</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${checkdetail}" var="checkdetail">
					<tr align="center" >
						<td>${checkdetail.student_id }</td>
						<td>${checkdetail.student_name }</td>
						<td>${checkdetail.class_name }</td>
						<td>${checkdetail.course_name }</td>
						<td>${checkdetail.checktime }</td>
						<td>${checkdetail.state }</td>
						<td>
							<a href="CheckServlet?tab=modifycheck&courseid=${checkdetail.course_id }&checkdetail_id=${checkdetail.check_detail_id }&checkid=${checkdetail.check_id }&state=到课"><input type="button" name="arrive" value="到课" class="btn" /></a> 
							<a href="CheckServlet?tab=modifycheck&courseid=${checkdetail.course_id }&checkdetail_id=${checkdetail.check_detail_id }&checkid=${checkdetail.check_id }&state=迟到"><input type="button" name="late" value="迟到" class="btn" /></a> 
							<a href="CheckServlet?tab=modifycheck&courseid=${checkdetail.course_id }&checkdetail_id=${checkdetail.check_detail_id }&checkid=${checkdetail.check_id }&state=缺课"><input type="button" name="miss" value="缺课" class="btn" /></a> 
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<%
			} else {
				System.out.print("null");
			}
		%>

	</div>
>>>>>>> e9b787f063174407154a24e28a30906671f47b64

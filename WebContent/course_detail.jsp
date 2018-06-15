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
<div class="title">课程详情</div>
<div class="details">
	<%	String courseid=(String)session.getAttribute("courseid");
		String classid=(String)session.getAttribute("classid");
		String examid=(String)session.getAttribute("examid");
	%>
    <div class="details_operation clearfix">
        <div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=homework"> <input type="button" value="点名详情" class="btn"></a>
        </div>
        
        <div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=exam&courseid=<%=courseid%>"> <input type="button" value="考试情况" class="btn"></a>
        </div>
         
     	<div class="bui_select" style="margin-right:10px">
            <a href="CourseServlet?tab=final"> <input type="button" value="最终成绩" class="btn"></a>
        </div>
        <%-- <div class="bui_select" style="height: 30px; border-left: 2px solid black; margin-right:10px"></div>
        <div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=addexam&courseid=<%=courseid%>"> <input type="button" value="添加考试" class="btn"></a>
        </div> --%>
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
    <div class="details_operation clearfix">
    	<div class="bui_select" style="margin-right:5px">
            <a href="CourseServlet?tab=addexam&courseid=<%=courseid%>"> <input type="button" value="添加考试" class="btn"></a>
        </div>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="20%">考试编号</th>
            <th width="20%">考试名称</th>
            <th>操作</th>

        </tr>
        </thead>
        <tbody>
        <!--                //数据库写在下面-->
        	<%
				List objlist = (List) request.getAttribute("objlist");
				if (objlist != null) {
					for (int i = 0; i < objlist.size(); i++) {
						ExamBean exam = (ExamBean) objlist.get(i);
						List objlistcourse = (List) request.getAttribute("objlistcourse");
						if (objlistcourse != null) {
							for (int j = 0; j < objlistcourse.size(); j++) {
								CourseBean course = (CourseBean) objlistcourse.get(i);
								//if(course.getCourse_id()==exam.getCourse_id()){
			%>
        <tr>
            <td align="center"><%=exam.getExam_id()%></td>
			<td align="center"><%=exam.getExam_name()%></td>
            <td>
                <a href="CourseServlet?tab=listexam_detail&examid=<%=exam.getExam_id()%>&classid=<%=course.getClass_id()%>"><input type="button" value="查看考试详情" class="btn"></a>
                <a href="CourseServlet?tab=modifyexam&examid=<%=exam.getExam_id()%>"><input type="button" value="修改考试信息" class="btn"></a>
                <a href="CourseServlet?tab=deleteexam&examid=<%=exam.getExam_id()%>&courseid=<%=exam.getCourse_id() %>" ><input type="button" value="删除考试信息" class="btn"></a>
            </td>
        </tr>
        	<%
								//break;}
        					break;}
						}
					}
				}
			%>
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
        <!--                //数据库写在下面-->
        <tr>
            <td>
                <label>

                </label>
            </td>
            <td>

            </td>
            <td>

            </td>
            <td>
                <a href="#" ><input type="button" value="查看学生详情" class="btn"></a>
                <a href="#" ><input type="button" value="移除学生" class="btn"></a>
                
            </td>
        </tr>
        </tbody>
    </table>
    <%
    }else if("listexam_detail".equals(tab)){
    %>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="20%">考生学号</th>
            <th width="20%">考生姓名</th>
            <th width="20%">考试成绩</th>
            <th>操作</th>

        </tr>
        </thead>
        <tbody>
        <!--                //数据库写在下面-->
        	<%
				List objlist = (List) request.getAttribute("objlist");
				if (objlist != null) {
					for (int i = 0; i < objlist.size(); i++) {
						StudentBean student = (StudentBean) objlist.get(i);
						List objlistexamdetail = (List) request.getAttribute("objlistexamdetail");
						if (objlistexamdetail != null) {
							for (int j = 0; j < objlist.size(); j++) {
								ExamdetailBean examdetail = (ExamdetailBean) objlistexamdetail.get(i);
								if(student.getStudent_id()==examdetail.getStudent_id()){
			%>
        <tr>
        	<td align="center"><%=student.getStudent_id()%></td>
            <td align="center"><%=student.getStudent_name()%></td>
            <%if("".equals(examdetail.getScore())) {%>
				<td align="center">0</td>
			<%} else{%>
				<td align="center"><%=examdetail.getScore() %></td>
			<%} %>
            <td>
                <a href="CourseServlet?tab=modifyscore&examdetailid=<%=examdetail.getExam_detail_id()%>&studentid=<%=student.getStudent_id()%>&classid=<%=classid%>&examid=<%=examid%>"><input type="button" value="修改考试成绩" class="btn"></a>
            </td>
        </tr>
        	<%
								break;} 
							}
						}
					}
				}
			%>
        </tbody>
    </table>
    	<%
    }else{
    	System.out.print("null");
    }
    %>
   
</div>
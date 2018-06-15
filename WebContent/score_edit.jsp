<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.bean.*"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="title">修改考试成绩</div>
<div class="details">
    <div class="details_operation clearfix">
        <!--方法地址-->
    <form action="CourseServlet?tab=modifyscoreresult" method="post">
    	<%
    		ExamdetailBean bean =(ExamdetailBean)request.getAttribute("obj");
    		if(bean==null){
    		return;
    		}
    		StudentBean studentbean = (StudentBean)request.getAttribute("objstudent");
        	if(studentbean==null){
        		return;
        	}
        	ExamBean exambean = (ExamBean)request.getAttribute("objexam");
        	if(exambean==null){
        		return;
        	}
        	ClassBean classbean = (ClassBean)request.getAttribute("objclass");
        	if(classbean==null){
        		return;
        	}
        	 
        %>
        <table width="35%" border="1" cellpadding="5" cellspacing="0" bgcolor="#ccc" >
        	<tr>
                <td align="center" width="25%">详情编号</td>
                <td><input type="text" name="examdetailid" readonly='readonly' value='<%=bean.getExam_detail_id() %>' ></td>
            </tr>
        	<tr>
                <td align="center" width="25%">考试编号</td>
                <td><input type="text" name="examid" readonly='readonly' value='<%=exambean.getExam_id() %>' ></td>
            </tr>
            <tr>
                <td align="center" width="25%">班级编号</td>
                <td><input type="text" name="classid" readonly='readonly' value='<%=classbean.getClass_id() %>' ></td>
            </tr>
        	<tr>
                <td align="center" width="25%">考生学号</td>
                <td><input type="text" name="studentid" readonly='readonly' value='<%=studentbean.getStudent_id() %>' ></td>
            </tr>
            <tr>
                <td align="center" width="25%">考生姓名</td>
                <td><input type="text" name="studentname" readonly='readonly' value='<%=studentbean.getStudent_name() %>' ></td>
            </tr>
            <tr>
                <td align="center" width="25%">考试成绩</td>
                <td><input type="text" name="score" placeholder="     请输入考试成绩" ></td>
            </tr>
        </table>
        <br />
        <div class="bui_select" >
            <input type="submit" value="确&nbsp;&nbsp;定" class="add">
        </div>
    </form>
</div>
</div>


</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,cn.edu.zucc.ems.bean.*"%>
<%@page import="cn.edu.zucc.ems.model.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
</head>
<body>


<div class="title">添加课程</div>
<div class="details">
    <div class="details_operation clearfix">

        <form action="CheckServlet?tab=addexamresult" method="post">
        	<%
        		CourseBean bean = (CourseBean)request.getAttribute("obj");
        		if(bean==null){
        			return;
        		}
       		 %>
            <table width="35%" border="1" cellpadding="5" cellspacing="0" bgcolor="#ccc" >
                <tr>
                    <td align="center">考试名称</td>
                    <td><input type="text" name="examname" placeholder="     请输入考试名称"></td>
                </tr>
                <tr> 
                	<td align="center">课程编号</td>
                	<td><input type="text" name="courseid" readonly='readonly' value='<%=bean.getCourse_id() %>' ></td>
				</tr>
				<tr> 
                	<td align="center">课程名称</td>
                	<td><input type="text" name="coursename" readonly='readonly' value='<%=bean.getCourse_name() %>' ></td>
				</tr>	
            </table>
            <br />
            <div class="bui_select" >
                <input type="submit" value="添&nbsp;&nbsp;加" class="add">
            </div>
        </form>
    </div>
</div>


</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.bean.*"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
</head>
<body>

<div class="title">修改考试信息</div>
<div class="details">
    <div class="details_operation clearfix">
        <!--方法地址-->
    <form action="CheckServlet?tab=modifyexamresult" method="post">
    	<%
        	ExamBean bean = (ExamBean)request.getAttribute("obj");
        	if(bean==null){
        		return;
        	}
        %>
        <table width="35%" border="1" cellpadding="5" cellspacing="0" bgcolor="#ccc" >
        	<tr>
                <td align="center" width="25%">考试编号</td>
                <td><input type="text" name="examid" readonly='readonly' value='<%=bean.getExam_id() %>' ></td>
            </tr>
            <tr>
                <td align="center" width="25%">考试名称</td>
                <td><input type="text" name="examname"  value='<%=bean.getExam_name() %>' ></td>
            </tr>
            <tr>
                <td align="center" width="25%">课程编号</td>
                <td><input type="text" name="courseid" readonly='readonly' value='<%=bean.getCourse_id() %>' ></td>
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
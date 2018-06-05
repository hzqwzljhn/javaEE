<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.edu.zucc.ems.bean.ClassBean"%>
<%@page import="cn.edu.zucc.ems.model.*"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
</head>
<body>
<div class="title">修改教师信息</div>
<div class="details">
    <div class="details_operation clearfix">
        <!--方法地址-->
    <form action="ClassServlet?act=modifyresult" method="post">
        <%
        ClassBean bean=(ClassBean)request.getAttribute("obj");
        if(bean==null)return;
        %>
        <table width="35%" border="1" cellpadding="5" cellspacing="0" bgcolor="#ccc" >
            <tr>
                <td align="center" width="20%">班级编号</td>
                <td><input type="text" name="classid" readonly='readonly' value='<%=bean.getClass_id() %>' ></td>
            </tr>
            <tr>
                <td align="center">班级名称</td>
                <td><input type="text" name="classname" value='<%=bean.getClass_name() %>'></td>
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
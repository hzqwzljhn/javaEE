<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cn.edu.zucc.ems.bean.*" %>
<%@ page import="cn.edu.zucc.ems.model.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
</head>
<body>
<div class="title">学生管理</div>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select">
            <a href="StudentServlet?method=addstudent"> <input type="button" value="添加学生" class="btn"></a>
        </div>

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
        	List objlist = (List)request.getAttribute("objlist");
        	if(objlist != null){
        		for(int i=0;i<objlist.size();i++){
        			StudentBean	student = (StudentBean)objlist.get(i);
        %>
        <tr>
            <td align="center"><%= student.getStudent_id() %></td> 
            <td align="center"><%= student.getStudent_name() %></td>
            <td>
                <a href="StudentServlet?method=modifystudent&studentid=<%= student.getStudent_id() %>" ><input type="button" value="修改" class="btn"></a>
                <a href="StudentServlet?method=deletestudent&studentid=<%= student.getStudent_id() %>" ><input type="button" value="删除" class="btn"></a>
            </td>
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
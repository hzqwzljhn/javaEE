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
<div class="title">班级管理</div>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select">
            <a href="ClassServlet?act=addclass"> <input type="button" value="添加班级" class="btn"></a>
        </div>
     
    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="20%">班级编号</th>
            <th width="20%">班级名称</th>
            <th>操作</th>

        </tr>
        </thead>
        <tbody>
       <%
					List objlist = (List) request.getAttribute("objlist");
					if (objlist != null) {
						for (int i = 0; i < objlist.size(); i++) {
							ClassBean classes = (ClassBean) objlist.get(i);
				%>
        <tr>
            <td align="center"><%= classes.getClass_id() %> </td>
            <td align="center"><%= classes.getClass_name() %></td>
            <td>
                <a href="ClassServlet?act=modifyclass&classid=<%= classes.getClass_id()%>" ><input type="button" value="修改班级" class="btn"></a>
                <a href="ClassServlet?act=deleteclass&classid=<%= classes.getClass_id()%>" ><input type="button" value="删除班级" class="btn"></a>
                <a href="ClassServlet?act=importlist&classid=<%= classes.getClass_id()%>&classname=<%= classes.getClass_name() %>" ><input type="button" value="导入学生" class="btn"></a>
                <a href="ClassServlet?act=classdetail&classid=<%= classes.getClass_id()%>&classname=<%= classes.getClass_name() %>" ><input type="button" value="详情" class="btn"></a>
            </td>
        </tr>
        <%	}
				}%>
        </tbody>
    </table>
</div>
</body>
</html>
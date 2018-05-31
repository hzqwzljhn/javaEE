<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
</head>
<body>
<div class="title">班级管理</div>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select">
            <a href="class_add.jsp"> <input type="button" value="添加班级" class="btn"></a>
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
        <!--                //数据库写在下面-->
        <tr>
            <td>
                <label>

                </label>
            </td>
            <td>

            </td>
            <td>
                <a href="class_edit.jsp" ><input type="button" value="修改班级" class="btn"></a>
                <a href="#" ><input type="button" value="删除班级" class="btn"></a>
                <a href="#" ><input type="button" value="导入学生" class="btn"></a>
                <a href="class_detail.jsp" ><input type="button" value="详情" class="btn"></a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
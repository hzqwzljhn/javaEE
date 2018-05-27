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
<div class="title">教师管理</div>
<div class="details">
    <div class="details_operation clearfix">
        <div class="bui_select">
            <a href="teacher_add.jsp"> <input type="button" value="添加教师" class="btn"></a>
        </div>

    </div>
    <!--表格-->
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th width="20%">教师编号</th>
            <th width="20%">教师姓名</th>
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
                <a href="teacher_edit.jsp" ><input type="button" value="修改" class="btn"></a>
                <a href="#" ><input type="button" value="删除" class="btn"></a>
            </td>
        </tr>


        </tbody>
    </table>
</div>


</body>
</html>
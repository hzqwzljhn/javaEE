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


<div class="title">添加教师</div>
<div class="details">
    <div class="details_operation clearfix">

        <form action="TeacherServlet?method=addteacher" method="post">
            <table width="35%" border="1" cellpadding="5" cellspacing="0" bgcolor="#ccc" >
                <tr>
                    <td align="center" width="20%">教师编号</td>
                    <td><input type="text" name="typeid" placeholder="     请输入教师编号"></td>
                </tr>
                <tr>
                    <td align="center">教师姓名</td>
                    <td><input type="text" name="typename" placeholder="     请输入教师姓名"></td>
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
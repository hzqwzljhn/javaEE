<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>平时成绩管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
		<% String name = (String) session.getAttribute("userid");
	if (name == null) {
		%>
		<script type="text/javascript">
		alert("您还没有登录，请登录...");
		window.location.href="login.jsp";
		</script>
		<% 
	}
%>
</head>
<body>
<div class="head">
    <div class="logo fl"><a href="#"></a></div>
    <h3 class="head_text fr">平时成绩管理系统</h3>
</div>
<div class="operation_user clearfix">

    <div class="link fr">
        <a href="teacher.jsp" target="_parent" class="icon icon_i">首页</a><span></span><a href="Userservlet?method=logout" target="_parent"  class="icon icon_e">退出</a>
    </div>
</div>
<div class="content clearfix">
    <div class="main">
        <div class="cont">
            <!--右侧内容-->
            <iframe src="main.jsp" name="manage1" style="width:100%;height:750px"></iframe>
        </div>
    </div>
    <div class="menu">
        <div class="cont">
            <div class="title">教师</div>
            <ul class="mList">
                <li>
                    <h3><a href="CourseServlet" target="manage1" style="font-size: 18px">课程管理</a></h3>
                    <!-- <dl>
                        <dd><a href="#" target="manage1">添加居民</a></dd>

                    </dl> -->
                </li>
                <li>
                    <h3><a href="class_list.jsp" target="manage1" style="font-size: 18px">班级管理</a></h3>
                    
                </li>

                <li>
                    <h3><a href="student_list.jsp" target="manage1" style="font-size: 18px">学生管理</a></h3>
                   
                </li>

            </ul>
        </div>
    </div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width; initial-scale=1; user-scalable=no">
	<title>选课管理系统</title>
	<link href="<%=basePath%>/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace-skins.min.css" />
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/course/common.js"></script>
	<script src="<%=basePath%>/assets/js/ace-elements.min.js"></script>
	<script src="<%=basePath%>/assets/js/ace.min.js"></script>
	<script src="<%=basePath%>/assets/js/ace-extra.min.js"></script>
	<script src="<%=basePath%>/assets/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/js/ace-elements.min.js"></script>
	<script src="<%=basePath%>/assets/js/typeahead-bs2.min.js"></script>
	<script src="<%=basePath%>/js/layer/layer.js"></script>
<script src="<%=basePath%>/js/laydate/laydate.js"></script>
<script src="<%=basePath%>/js/laytpl/laytpl.js"></script>
<script src="<%=basePath%>/assets/js/date-time/bootstrap-datepicker.min.js" ></script>
<script src="<%=basePath%>/assets/js/date-time/daterangepicker.min.js"></script>
<script src="<%=basePath%>/assets/js/date-time/bootstrap-timepicker.min.js" ></script>
<link rel="stylesheet" href="<%=basePath%>/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="<%=basePath%>/assets/css/bootstrap-timepicker.css" />

<style>
	@media only screen and (max-width: 460px) {
		.navbar .navbar-header.pull-left {
			float: left !important;
		}
		.navbar .navbar-header.pull-right {
			float: right !important;
		}
	}
		.visible-lg.visible-md {
	    	display: block !important;
		}
</style>

<script>
	window.onload = function() {
		
		// 绑定左侧栏active样式
		var location_url = location.pathname;
		$('#navs > li').removeClass('active');
		if (/course\/teacherCourseListByPage.do/.test(location_url)) {
			$('#nav-1').addClass('active');
		} else if (/course\/collegeListByPage.do/.test(location_url) || /course\/addCollegePage.do/.test(location_url)) {
			$('#nav-2').addClass('active');	
		} else if (/course\/majorListByPage.do/.test(location_url) || /course\/addMajorPage.do/.test(location_url)) {
			$('#nav-3').addClass('active');
		} else if (/course\/classListByPage.do/.test(location_url) || /course\/addClassPage.do/.test(location_url)) {
			$('#nav-4').addClass('active');
		} else if (/course\/userListByPage.do/.test(location_url) || /course\/addUserPage.do/.test(location_url)) {
			$('#nav-5').addClass('active');
		} else if (/course\/courseListByPage.do/.test(location_url) || /course\/addCoursePage.do/.test(location_url)) {
			$('#nav-6').addClass('active');
		} else if (/course\/myselfPage.do/.test(location_url) || /course\/updatePwdPage.do/.test(location_url) || /course\/updateHeadImagePage.do/.test(location_url)) {
			$('#nav-7').addClass('active');
		}
	}
</script>

</head>
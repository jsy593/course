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
	<title>选课管理系统</title>
	<link href="<%=basePath%>/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ace-skins.min.css" />
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/course/common.js"></script>
	<script src="<%=basePath%>/assets/js/ace-elements.min.js"></script>
	<script src="<%=basePath%>/assets/js/ace.min.js"></script>
	<script src="<%=basePath%>/assets/js/ace-extra.min.js"></script>
	<script src="<%=basePath%>/assets/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/js/typeahead-bs2.min.js"></script>
	<script src="<%=basePath%>/js/layer/layer.js"></script>
<script src="<%=basePath%>/js/laydate/laydate.js"></script>
<script src="<%=basePath%>/js/laytpl/laytpl.js"></script>
<script src="<%=basePath%>/assets/js/date-time/bootstrap-datepicker.min.js" ></script>
<script src="<%=basePath%>/assets/js/date-time/daterangepicker.min.js"></script>
<script src="<%=basePath%>/assets/js/date-time/bootstrap-timepicker.min.js" ></script>
<link rel="stylesheet" href="<%=basePath%>/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="<%=basePath%>/assets/css/bootstrap-timepicker.css" />
</head>
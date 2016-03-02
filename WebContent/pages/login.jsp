<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>选课系统</title>

<link href="css/style.css" rel="stylesheet" type="text/css"/>
<!-- <link rel="stylesheet" href="assets/css/ace.min.css" /> -->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/course/login.js"></script>
</head>

<body class="login">

<div class="login_m">
	<div class="text"><p  class="login_logo">选课系统</p></div>
	<div class="login_boder">
		<div class="login_padding">
			<h2>账号</h2>
			<label>
				<input type="text" id="userNumber" class="txt_input txt_input2" onfocus="if (value ==&#39;username&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;username&#39;}" value="username">
			</label>
			<h2>密码</h2>
			<label>
				<input type="password" name="textfield2" id="userpwd" class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******">
			</label>
			
<!-- 			<label class="inline"> -->
<!-- 				<input type="radio" checked name="identity" value="2"/> -->
<!-- 				<span>学生</span> -->
<!-- 				<input type="radio" name="identity"  value="1"/> -->
<!-- 				<span>老师</span> -->
<!-- 				<input type="radio" name="identity"  value="0"/> -->
<!-- 				<span>管理员</span> -->
<!-- 			</label> -->
				<label class="inline">
				<input type="radio" checked name="identity" class="ace" value="2"/>
				<span class="lbl">学生</span>
				<input type="radio" name="identity" class="ace" value="1"/>
				<span class="lbl">老师</span>
				<input type="radio" name="identity" class="ace" value="0"/>
				<span class="lbl">管理员</span>
			</label>
			
			<div class="rem_sub">
				<label>
					<input type="submit" class="sub_button" name="button" onclick="login()"  value="登录" style="opacity: 0.7;">
				</label>
			</div>
		</div>
	</div><!--login_boder end-->
</div><!--login_m end-->
<br />
<br />
</body>
</html>
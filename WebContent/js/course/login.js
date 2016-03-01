function login(){
	var value = $('.inline input[name="identity"]:checked').val();
	var userNumber = $("#userNumber").val();
	
	 var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字,//若判断正整数，则后边是：/^[1-9]+[0-9]*]*$/
     if (!re.test(userNumber)) {
    	 layer.alert('请输入正确的账号!', {icon: 5});
         return;
      }
	
	if($.trim(userNumber) == "" || userNumber == undefined || userNumber == null){
		layer.alert('请输入登录账号!', {icon: 5}); 
	}
	var password = $("#userpwd").val();
	if($.trim(password) == "" || password == undefined || password == null){
		layer.alert('请输入密码!', {icon: 5}); 
	}
	if(password.length < 2 || password.length >20 ){
		layer.alert('密码的长度在2-20位之间!', {icon: 5}); 
		return;
	}
	var user = {};
	user.usernumber = $.trim(userNumber);
	user.password = $.trim(password);
	user.identity = value;
	$.post("login.do",user,function(result){
		if(result == "passwordError"){
			layer.alert('密码错误!', {icon: 5});
			password = "";
			return;
		}else if(result =="roleError"){
			layer.alert('您的账号和身份不符合!', {icon: 5});
		}else if(result == "success"){
			layer.alert('恭喜你，登录成功!',{icon:6});
			window.location.href= "personalCenter/myself.do";
		}else{
			layer.alert("账号不存在!",{icon:5});
		}
	});
}
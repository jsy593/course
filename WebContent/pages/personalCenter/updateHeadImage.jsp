<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}"/> --%>

<!DOCTYPE html>
<html lang="ch">
<%@ include file="../common.jsp" %>

<script type="text/javascript">
	function test(){
		alert("进入test");
		$.post("test.do",{},function(data){
			
		});
	}
</script>
	<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="#">首页</a>
						</li>

						<li>
							<a href="collegeListByPage.do">个人中心</a>
						</li>
						<li class="active">修改头像</li>
					</ul><!-- .breadcrumb -->
					<button value="test" onclick="test()"/>
					<!-- <div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div> --><!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form" action="updateHeadImage.do" enctype="multipart/form-data" method="post" onsubmit="return checkFileType();">
									<div class="form-group">
									<!-- 修改头像开始 -->
										<div style="text-align: center;margin-bottom: 10px">
			                           		 <img src="${ctx }/${sessionScope.user.imageUrl}" class="js_imgUrl img-circle" width="140" height="140">
			                       	 	</div>
			                        <!-- 修改头像结束 -->
								</div>
								
						
				<!---------------------------------提交按钮开始 ------------------------------->
				
						<div class="clearfix form-actions" style="margin-bottom: 10px">
										<div style="text-align: center;margin-bottom: 10px">
											<button class="btn btn-info" type="button" onclick="$('input[class=js_file]').click();">
												<i class="icon-picture bigger-110"></i>
												选择要上传的文件
											</button>
											 <input class="js_file" id="js_upload_file" style="display:none" type="file" name="imgFile" multiple>
											
											&nbsp; &nbsp; &nbsp;
											<button class="btn btn-success" type="submit">
												<i class="icon-arrow-up bigger-110"></i>
												上传
											</button>
											
											 <div id="file-list"></div>
										</div>
									</div>
				<!---------------------------------提交按钮结束      ------------------------------->		
				</form>	
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		<script type="text/javascript">
		
		/**
		*修改完图片显示成功或失败
		*/
// 		if("${message}"){
// 			alert("123");
// 			layer.msg('${message}', {icon: 6,time:2000});
// 		}else if('${error}'){
// 			alert("");
// 			layer.msg('${error}', {icon: 5,time:2000});
// 		}
		
		$('input[class=js_file]').change(function() {
            var fileNames = '';
            $.each(this.files, function() {
                fileNames += '<span >' + this.name + '</span>';
            });
            $('#file-list').html(fileNames);
		});
		
		
		/*
		*检查图片格式
		*/
		
		 function checkFileType(){
		        var format = ["bmp","jpg","gif","png"];
		        var filename = $(".js_file").val();
		        var ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
		        if(jQuery.inArray(ext, format) != -1){
		            return true;
		        }else{
		            layer.msg('头像格式只能是bmp,jpg,gif,png!', {
		                offset: 0,
		                shift: 6
		            });
		            return false;
		        }
		    }
		
		
		
		 $(function() {
             $('.js_file').on('change', function() {
                 var fileNames = '';
                 $.each(this.files, function() {
                     fileNames += '<span class="am-badge">' + this.name + '</span> ';
                 });
                 $('#file-list').html(fileNames);
             });
         });
		
		/**
			添加学院
		*/
		function addCollege(){
			var name = $.trim($(".js_name").val());
			if(name == "" || name == null){
				layer.alert('请输入学院名称!', {icon: 5});
				return;
			}
			
			var createTime = $.trim($(".js_createTime").val());
			if(createTime == null  || createTime ==""){
				layer.alert('请选择日期!', {icon: 5});
				return;
			}
			
			var mapVo = {};
			mapVo.name = name;
			mapVo.createtime = createTime;
			$.post("addCollege.do",{'mapVo':mapVo},function(data){
				if(data == true){
					layer.msg('添加成功!', {icon: 6,time:2000},function(){
						window.location.reload();
					});
				}else{
					layer.msg('添加失败!', {icon: 5});
				}
			});
		}
		
			/*设置日历颜色*/
			laydate.skin('molv');
		</script>
</body>
</html>
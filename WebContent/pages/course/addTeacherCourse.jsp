<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ch">
<%@ include file="../common.jsp" %>
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
							<a href="courseListByPage.do?pageNum=1&pageSize=5">课程管理</a>
						</li>
						<li class="active">申请修改课程</li>
					</ul><!-- .breadcrumb -->

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
							<form class="js_form form-horizontal" role="form" action="addTeacherCourse.do">
							<input type="hidden" value="${course.id }" name="courseid"/>
							<!-- 所有 -->	
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">课程编号:
										</label>

										<div class="col-sm-9">
											<input type="text" value="${course.coursenumber }" id="form-field-1"  class="js_coursenumber col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">课程名称:
										</label>

										<div class="col-sm-9">
											<input type="text"  value="${course.name}" id="form-field-1"  class="js_coursename col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">修改原因:
										</label>

										<div class="col-sm-9">
											<div>
												<textarea id="form-field-11" name="content" class="js_content col-xs-10 col-sm-5"></textarea>
											</div>
									</div>
									</div>
									<div class="space-4"></div>
							
							
					
				</form>				
				<!---------------------------------提交按钮开始 ------------------------------->
				
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="addTeacherCourse()">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>
											
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset" onclick="reset()">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>
				<!---------------------------------提交按钮结束      ------------------------------->		
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		<script type="text/javascript">
		/**
			添加申请
		*/
		function addTeacherCourse(){
			
			var content = $.trim($(".js_content").val());
			if(content == "" || content == null){
				layer.alert('请输入修改!', {icon: 5});
				return;
			}
			
			
			var teacherCourse = $(".js_form").serialize();
			 $.post($(".js_form").attr("action"),teacherCourse,function(data){
				if(data == true){
					layer.alert('申请成功!', {icon: 6, time:2000},function(){
						window.location.reload();
					});
				}else{
					layer.alert('申请失败!', {icon: 5});
				}
			}); 
		}
		</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
							<a href="userListByPage.do">用户管理</a>
						</li>
						<li class="active">添加用户</li>
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
							<form class="form-horizontal" role="form">
							<!-- 所有 -->	
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户角色
										</label>

										<div class="col-sm-9">
											<select name="identity" class="js_select_role col-xs-10 col-sm-5" id="form-field-1">
												<option value="2" selected>学生</option>
												<option value="1">老师</option>
												<option value="0">管理员</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
							
							
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户名
										</label>

										<div class="col-sm-9">
											<input type="text" name="username" id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">初始密码
										</label>

										<div class="col-sm-9">
											<input type="password"  id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
							<!-- 所有 -->	
							
							
							<!-- 学生开始 -->
							<div class="js_form_student">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">学号
										</label>

										<div class="col-sm-9">
											<input type="text" name="usernumber"  id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级
										</label>

										<div class="col-sm-9">
											<select class="js_select col-xs-10 col-sm-5" id="form-field-1">
											<c:forEach items="${classes}" var="clazz">
												<option value="${clazz.id}">${clazz.name }</option>
											</c:forEach>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
							</div>	
							<!-- 学生结束 -->	
							
							
							
							<!-- 老师开始 -->
							<div class="js_form_teacher">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">学号
										</label>

										<div class="col-sm-9">
											<input type="text" name="usernumber"  id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级
										</label>

										<div class="col-sm-9">
											<select class="js_select col-xs-10 col-sm-5" id="form-field-1">
											<c:forEach items="${classes}" var="clazz">
												<option value="${clazz.id}">${clazz.name }</option>
											</c:forEach>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
							</div>	
							<!-- 老师结束 -->		
							
							
							<!-- 管理员开始 -->
							
							<div class="js_form_admin">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">学号
										</label>

										<div class="col-sm-9">
											<input type="text" name="usernumber"  id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级
										</label>

										<div class="col-sm-9">
											<select class="js_select col-xs-10 col-sm-5" id="form-field-1">
											<c:forEach items="${classes}" var="clazz">
												<option value="${clazz.id}">${clazz.name }</option>
											</c:forEach>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
							</div>	
							<!-- 管理员结束 -->	
								
						
						<!-- 所有 -->				
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 注册日期 </label>
											
											<div class="col-sm-9">
													<input   id="form-field-3" type="text"  onclick="laydate()" class="js_createTime col-xs-10 col-sm-5"/>
											</div>
									</div>

									<div class="space-4"></div>
									
						
					<div class="col-sm-9">
							<select name="sex" class="js_select_sex col-xs-10 col-sm-5" id="form-field-1">
								<option value="1" selected>男</option>
								<option value="2">女</option>
								<option value="0">未知</option>
							</select>
							</div>
					<!-- 所有 -->	
				</form>				
				<!---------------------------------提交按钮开始 ------------------------------->
				
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="addCollege()">
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
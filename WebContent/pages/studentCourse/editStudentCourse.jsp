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
							<a href="userListByPage.do">用户管理</a>
						</li>
						<li class="active">修改用户</li>
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
							<form class="js_form form-horizontal" role="form" action="editUser.do">
							<input type="hidden" value="${user.id }" name="id"/>
							<!-- 所有 -->	
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户角色
										</label>

										<div class="col-sm-9">
											<select name="identity" class="js_select_role col-xs-10 col-sm-5" id="form-field-1">
												<option value="2" <c:if test="${user.identity == 2}">selected</c:if>>学生</option>
												<option value="1" <c:if test="${user.identity == 1}">selected</c:if>>老师</option>
												<option value="0" <c:if test="${user.identity == 0}">selected</c:if>>管理员</option>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">
										<c:if test="${identity ==2 }">学</c:if>
										<c:if test="${identity ==2 }">工</c:if>
										号
										</label>

										<div class="col-sm-9">
											<input type="text" name="usernumber" value="${user.usernumber }"  id="form-field-1"  class="js_usernumber col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
							
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户名
										</label>

										<div class="col-sm-9">
											<input type="text" name="username"  value="${user.username }" id="form-field-1"  class="js_username col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class=" col-sm-3 control-label no-padding-right" for="form-field-1">初始密码
										</label>

										<div class="col-sm-9">
											<input type="password" name="password"  value="${user.password }" id="form-field-1"  class="js_password col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
							<!-- 所有 -->	
							
							
							<!-- 学生开始 -->
							<c:if test="${user.identity ==2 }">
								<div class="js_form_student">
										
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级
											</label>
											
											<div class="col-sm-9">
												<select name="classid" class="js_select col-xs-10 col-sm-5" id="form-field-1">
												<c:forEach items="${classes}" var="clazz">
													<option value="${clazz.id}" <c:if test="${user.classid == clazz.id }">selected</c:if>>${clazz.name }</option>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="space-4"></div>
										
										<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">学分
										</label>

										<div class="col-sm-9">
											<input type="text"  value="${user.credit }" id="form-field-1"  class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
								</div>	
							</c:if>
							<!-- 学生结束 -->	
							
						<!-- 所有 -->		
							<div class="form-group">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 性别:</label>
										<div class="col-sm-9">
														<label>
														 <input name="sex"  type="radio" <c:if test="${user.sex == 1 }">checked</c:if> value="1" class="ace js_radio" />
															<span class="lbl">男</span>
														</label>
															<label>
															<input name="sex" type="radio" <c:if test="${user.sex == 2 }">checked</c:if> value="2" class="ace js_radio" />
															<span class="lbl">女</span>
														</label>
														<label>
															 <input name="sex"  type="radio" <c:if test="${user.sex == 0 }">checked</c:if> value="0" class="ace js_radio" />
															<span class="lbl">未知</span>
														</label>
										</div>
									</div>
								</div>
								<div class="space-4"></div>
								
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 注册日期 </label>
											
											<div class="col-sm-9">
													<input  id="form-field-3" type="text" value="<fmt:formatDate value='${user.createtime }'  type="date" pattern='yyyy-MM-dd'/>" onclick="laydate()" class="js_createTime col-xs-10 col-sm-5"/>
											</div>
									</div>

									<div class="space-4"></div>
									
						
					
					<!-- 所有 -->	
				</form>				
				<!---------------------------------提交按钮开始 ------------------------------->
				
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="editUser()">
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
			修改用户
		*/
		function editUser(){
// 			$(".js_form").submit();
			
			var usernumber = $.trim($(".js_usernumber").val());
			if(usernumber == "" || usernumber == null){
				layer.alert('请输入编号!', {icon: 5});
				return;
			}
			
			var name = $.trim($(".js_username").val());
			if(name == "" || name == null){
				layer.alert('请输入用户名!', {icon: 5});
				return;
			}
			
			var password = $.trim($(".js_password").val());
			if(password == "" || password == null){
				layer.alert('请输入密码!', {icon: 5});
				return;
			}
			
			var createTime = $.trim($(".js_createTime").val());
			if(createTime == null  || createTime ==""){
				layer.alert('请选择日期!', {icon: 5});
				return;
			}
			
			var user = $(".js_form").serialize();
			 $.post($(".js_form").attr("action")+"?createTime="+$(".js_createTime").val(),user,function(data){
				if(data == true){
					layer.alert('修改成功!', {icon: 6, time:2000},function(){
						window.location.reload();
					});
				}else{
					layer.alert('修改失败!', {icon: 5});
				}
			}); 
		}
		
			/*设置日历颜色*/
			laydate.skin('molv');
		</script>
</body>
</html>
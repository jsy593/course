<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
							<a href="#">用户管理</a>
						</li>
						<li class="active">用户列表</li>
					</ul><!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
									<div class="form-group" style="float:right;padding:0px 20px 0px 0px">
									<input type="hidden" value="${sessionScope.user.id}" class="js_userid"/>
									<!-- 管理员显示的下拉框开始 -->
									<c:if test="${sessionScope.user.identity != 2 }">
										<label class="control-label no-padding-right" for="form-field-1">请选择</label>
										</c:if>
									<c:if test="${sessionScope.user.identity == 0 }">
										</label>
											<select name="identity" class="js_select_role" id="form-field-1">
												<option value="2" <c:if test="${identity == 2}">selected</c:if>>学生</option>
												<option value="1" <c:if test="${identity == 1}">selected</c:if>>老师</option>
												<option value="0" <c:if test="${identity == 0}">selected</c:if>>管理员</option>
											</select>
											
											<c:if test="${identity ==2 }">
											<select name="classid" value="${classid }" class="js_select_class" id="form-field-1">
												<option value="-1" selected>所有</option>
												<c:forEach items="${classes }" var="clazz">
													<option value="${clazz.id }" <c:if test="${clazz.id == classid}">selected</c:if>>${clazz.name }</option>
												</c:forEach>
											</select>
											</c:if>
									</c:if>		
									<!-- 管理员显示的下拉框结束 -->	
									
									<!-- 老师显示的下拉框开始 -->	
										<c:if test="${sessionScope.user.identity == 1}">
											<select name="courseid" value="${courseId }" class="js_select_course" id="form-field-1">
												<option value="-1" selected>所有</option>
												<c:forEach items="${courses }" var="course">
													<option value="${course.id }" <c:if test="${course.id == courseId}">selected</c:if>>${course.name }</option>
												</c:forEach>
											</select>
										
										</c:if>	
									<!-- 老师显示的下拉框开结束-->
									
									<!-- 管理员显示的搜索栏开始 -->		
										<c:if test="${sessionScope.user.identity == 0}">
											<span class="input-icon">
												<input type="text"  value="${search }" placeholder="请输入编号或者姓名 ..." class="js_search nav-search-input" id="nav-search-input" autocomplete="off" />
												<button class="btn btn-info" type="button" onclick="selectUser(1)">
														<i class="icon-search bigger-110"></i>
															搜索
												</button>
											</span>
											
										</c:if>
									<!-- 管理员显示的搜索栏结束 -->
									
									<!-- 老师显示的搜索栏开始 -->
									<c:if test="${sessionScope.user.identity == 1}">
											<span class="input-icon">
												<input type="text"  value="${search }" placeholder="课程名或编号或者姓名 ..." class="js_search nav-search-input" id="nav-search-input" autocomplete="off" />
												<button class="btn btn-info" type="button" onclick="selectStudent(1)">
														<i class="icon-search bigger-110"></i>
															搜索
												</button>
											</span>
						 					
										</c:if>
										
									<!-- 老师显示的搜索栏结束 -->
							</div>
							<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table id="sample-table-1" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>编号</th>
														<th>用户名</th>
														<c:if test="${identity ==2 }">
															<th class="js_th">专业</th>
															<th class="js_th">班级</th>
															<th>学分</th>
														</c:if>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${users}" var="user" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${user.userNumber }</th>
														<th>${user.username }</th>
														<c:if test="${identity ==2 }">
															<th class="js_th">${user.mname }</th>
															<th class="js_th">${user.cname }</th>
															<th class="js_th">${user.credit}</th>
														</c:if>
														<th>${user.time }</th>
														<th>
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
															<c:if test="${sessionScope.user.identity == 0}">
																<a class="blue" href="addUserPage.do" >
																	<i class="icon-plus-sign bigger-130"></i>
																</a>
																<!-- <a class="blue" href="#">
																	<i class="icon-zoom-in bigger-130"></i>
																</a> -->

																<a class="green" href="editUserPage.do?id=${user.id }">
																	<i class="icon-pencil bigger-130"></i>
																</a>

																<a class="red" href="javascript:void(0);" onclick="deleteUser(${user.id })">
																	<i class="icon-trash bigger-130"></i>
																</a>
																
															</c:if>
															
															
															<c:if test="${sessionScope.user.identity == 1}">
																<button class="btn  btn-sm btn-danger " onclick="deleteStudent(${user.studentCourseId})">删除学生</button>
																<button class="btn  btn-sm btn-purple " onclick="addStudent(${user.courseId})">添加学生</button>
															</c:if>
															</div>

															<div class="visible-xs visible-sm hidden-md hidden-lg">
																<div class="inline position-relative">
																	<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
																		<i class="icon-caret-down icon-only bigger-120"></i>
																	</button>

																	<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																		<li>
																			<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																				<span class="blue">
																					<i class="icon-zoom-in bigger-120"></i>
																				</span>
																			</a>
																		</li>

																		<li>
																			<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="green">
																					<i class="icon-edit bigger-120"></i>
																				</span>
																			</a>
																		</li>

																		<li>
																			<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																				<span class="red">
																					<i class="icon-trash bigger-120"></i>
																				</span>
																			</a>
																		</li>
																	</ul>
																</div>
															</div>
														</th>
													</tr>
													
													
													</c:forEach>	
												</tbody>
									</table>
									<div class="modal-footer no-margin-top">
										
										<!-- 左侧开始 -->
										<label class="pull-left" data-dismiss="modal">
													第<b>${pageNum}</b>页，总共<b>${count}</b>条数据
										</label>
										<!-- 左侧结束 -->
										
										<!-- 分页开始 -->
												<ul class="pagination pull-right no-margin">
													<li class="prev">
													<c:if test='${pageNum > 1}'>
														<a href="javascript:void(0);" onclick="selectUser(${pageNum - 1})" >
															<i class="icon-double-angle-left"></i>
														</a>
														</c:if>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="javascript:void(0);" onclick="selectUser(${page})" value="${page }">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="javascript:void(0);" onclick="selectUser(${page})">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum + 4 < totalPage}">
													<li class="next">
														<a href="javascript:void(0);" onclick="selectUser(${page + 1})">
															<i class="icon-double-angle-right"></i>
														</a>
													</li>
												</c:if>
												</ul>
										<!-- 分页结束-->
											</div>
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		<script type="text/javascript">
		
		/*------------老师模块开始---------------*/
		function deleteStudent(id){
				layer.confirm('确认要删除该学生吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteStudentCourse.do",{"id":id},function(data){
				    	if(data == true){
				    		layer.msg('删除成功!', {icon: 6,time:1000},function(){
				    			history.go(0);
							});
				    	}else{
				    		layer.msg("删除失败!",{icon:5});
				    	}
				    });
				});
			}
		
		
			layer.config({
			    extend: 'extend/layer.ext.js'
			});
		function addStudent(id){
			layer.prompt({
			    formType: 0,
			    title: '请输入学号'
			}, function(value, index, elem){
				 $.post("teacherAddStudentCourse.do",{"courseId":id,"userNumber":value},function(data){
				    	layer.close(index);
				    	if(data == "notExist"){
				    		layer.msg("学号不存在!",{icon:5});
				    	}else if(data == "exist"){
				    		layer.msg("该学生已选过!",{icon:5});
				    	}else if(data == "success"){	
				    		layer.msg('添加成功!', {icon: 6,time:1000},function(){
				    			history.go(0);
							});
				    	}else{
				    		layer.msg("添加失败!",{icon:5});
				    	}
				    });
			}); 
			
		}
		/*------------老师模块结束---------------*/
		
		/**
		* 改变显示的内容
		*/
		$(function(){
			$(".js_select_role").change(function(){
				selectUser(1);
			});
			$(".js_select_class").change(function(){
				selectUser(1);
			});
			
			$(".js_select_course").change(function(){
				selectStudent(1);
			});
			
		});
		
		function selectStudent(pageNum){
			var search = $(".js_search").val();
			var courseId = $(".js_select_course").val();
			var teacherId = $(".js_userid").val();
			window.location.href="studentListBySearch.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&courseId="+courseId+"&teacherId="+teacherId;
		}
		
		function selectUser(pageNum){
			var search = $(".js_search").val();
			var identity = $(".js_select_role").val();
			var classid = "-1";
			if(identity == 2){
				var clazzid = $(".js_select_class").val();
				if(clazzid != undefined && clazzid != null){
					classid = clazzid;
				}
			}
			window.location.href="userListBySearch.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&identity="+identity+"&classid="+classid;
		}
		
		
		
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
			function deleteUser(id){
				layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteUser.do",{"id":id},function(data){
				    	if(data == true){
				    		layer.msg('删除成功!', {icon: 6,time:2000},function(){
				    			history.go(0);
							});
				    	}else{
				    		layer.msg("删除失败!",{icon:5});
				    	}
				    });
				});
				//e
			}
		</script>
</body>
</html>
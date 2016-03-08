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
							<a href="#">课程管理</a>
						</li>
						<li class="active">课程列表</li>
					</ul><!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<!--  -->
							<!-- 搜索栏只有管理员能见 -->
							<c:if test="${sessionScope.user.identity == 0 }">
									<div class="form-group" style="float:right;padding:0px 20px 0px 0px">
										<label class="control-label no-padding-right" for="form-field-1">请选择
										</label>
											<select name="teacherid" class="js_select_teacher" id="form-field-1">
											<option value="-1">所有</option>
											<c:forEach items="${teachers }" var="teacher">
												<option value="${teacher.id}" <c:if test="${teacher.id == teacherId}">selected</c:if>>${teacher.username }</option>
											</c:forEach>
											</select>
											
											<select name="specialtyId" class="js_select_major" id="form-field-1">
												<option value="-1">所有</option>
												<c:forEach items="${majors }" var="major">
													<option value="${major.id }" <c:if test="${major.id == specialtyId}">selected</c:if>>${major.name }</option>
												</c:forEach>
											</select>
											
											
											<span class="input-icon">
												<input type="text"  value="${search }" placeholder="课程名或者编号或老师 ..." class="js_search nav-search-input" id="nav-search-input" autocomplete="off" />
												<button class="btn btn-info" type="button" onclick="selectCourse(1)">
														<i class="icon-search bigger-110"></i>
															搜索
												</button>
											</span>
								</div>
							
							</c:if>
							
							<!-- 搜索栏结束 -->
							
							
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
														<th>课程编号</th>
														<th>课程名称</th>
														<th>课程学分</th>
														<th>任课老师</th>
														<th>上课时间</th>
														<th>上课地址</th>
														<th>所属专业</th>
														<th>最大容量</th>
														<th>是否可选</th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${courses}" var="course" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${course.courseNumber }</th>
														<th>${course.name }</th>
														<th>${course.credit }</th>
														<th>${course.teachername }</th>
														<th>${course.schooltime}</th>
														<th>${course.address }</th>
														<th>${course.mname }</th>
														<th>${course.numberLimit }</th>
														<c:if test="${course.isFinish == 0}">
															<th class="js_th${course.id }">不可选</th>
														</c:if>
														<c:if test="${course.isFinish == 1}">
															<th class="js_th${course.id }">可选</th>
														</c:if>
														<th>${course.time }</th>
														<th>
														
														
											<!------------------ 管理员模块开始 ----------------------->
														<c:if test="${sessionScope.user.identity == 0 }">
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
																<a class="blue" href="addcoursePage.do" >
																	<i class="icon-plus-sign bigger-130"></i>
																</a>
																<!-- <a class="blue" href="#">
																	<i class="icon-zoom-in bigger-130"></i>
																</a> -->

																<a class="green" href="editCoursePage.do?id=${course.id }">
																	<i class="icon-pencil bigger-130"></i>
																</a>

																<a class="red" href="javascript:void(0);" onclick="deleteCourse(${course.id })">
																	<i class="icon-trash bigger-130"></i>
																</a>
																
																<label class="js_label${course.id }">
																		<input name="switch-field-1" onChange="changeStatus(${course.id},${course.isFinish})" 
																		<c:if test="${course.isFinish == 1}">checked</c:if>
																		 class="js_isFinish${course.id } ace ace-switch ace-switch-4" type="checkbox" />
																		<span class="lbl"></span>
																</label>
															</div>
														</c:if>
													<!------------------ 管理员模块开结束 ----------------------->	
														
													<!------------------ 老师模块开始 ----------------------->	
														<c:if test="${sessionScope.user.identity == 1 }">
															<c:if test="${course.isAgree == 0  && course.isChange == 0}">
																
																<button class="btn  btn-sm btn-primary ">申请中</button>
																
																<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close pull-right">
<!-- 																			<li> -->
<!-- 																				<a href="#" class="tooltip-success" data-rel="tooltip" title="Mark&nbsp;as&nbsp;done"> -->
<!-- 																					<span class="orange">待查看 -->
<!-- 																					</span> -->
<!-- 																				</a> -->
<!-- 																			</li> -->
																		</ul>
																		
																</div>
															</c:if>
															<c:if test="${course.isAgree == null && course.isChange == 0}">
																
																<div class="inline dropdown-hover">
																<button class="btn  btn-sm btn-danger " onclick="applyEditCourse(${course.id })">申请修改</button>
																
																<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close pull-right">
																			<li>
																				<a href="javascript:void(0);" onclick="applyEditCourse(${course.id })" class="tooltip-success" data-rel="tooltip" title="Mark&nbsp;as&nbsp;done">
																					<span class="orange">申请
																					</span>
																				</a>
																			</li>
																		</ul>
																		
																</div>
																
															</c:if>
															
															<!-- null代表管理员同意后，删除了teacherCourse的记录 -->
															<c:if test="${(course.isAgree == 1 || course.isAgree == null) && course.isChange == 1}"> 

															<div class="inline dropdown-hover">
																<button class="btn  btn-sm btn-success " onclick="javascript:location='editCoursePage.do?id=${course.id }'">申请成功</button>
																
																<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close pull-right">
																			<li>
																				<a href="editCoursePage.do?id=${course.id }" class="tooltip-success" data-rel="tooltip" title="Mark&nbsp;as&nbsp;done">
																					<span class="green">修改课程
																					</span>
																				</a>
																			</li>
																		</ul>
																		
																</div>
															</c:if>
															
															
															<c:if test="${(course.isAgree == 1 || course.isAgree == null) && course.isChange == 2}">

															<div class="inline dropdown-hover">
																<button class="btn  btn-sm btn-success ">已修改</button>
																
<!-- 																<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close pull-right"> -->
<!-- 																		</ul> -->
																		
																</div>
															</c:if>
															
															<c:if test="${course.isAgree == 2 }"><!-- 管理员拒绝申请 -->
																
																<div class="inline dropdown-hover">
																<button class="btn  btn-sm btn-purple ">申请失败</button>
																
																<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-caret dropdown-close pull-right">
																			<li>
																				<a href="javascript:void(0);" onclick="applyEditCourse(${course.id })" class="tooltip-success" data-rel="tooltip" title="Mark&nbsp;as&nbsp;done">
																					<span class="green">再次申请
																					</span>
																				</a>
																			</li>
																		</ul>
																		
																</div>
															</c:if>
														</c:if>
														<!------------------ 老师模块结束 ----------------------->	
														
														
														<!------------------ 学生模块开始 ----------------------->
														<c:if test="${sessionScope.user.identity == 2 }">
															<c:if test="${course.studentCourseId != null && course.studentCourseId != ''}">
																<button class="btn  btn-sm btn-danger " onclick="deleteStudentCourse(${course.studentCourseId})">退选</button>
															</c:if>
															
															<c:if test="${course.studentCourseId == null || course.studentCourseId == ''}">
																<button class="btn  btn-sm btn-purple " onclick="addStudentCourse(${course.id},${sessionScope.user.id })">选课</button>
															</c:if>
														</c:if>
														<!------------------ 学生模块结束 ----------------------->
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
														<a href="javascript:void(0);" onclick="selectCourse(${pageNum - 1})" >
															<i class="icon-double-angle-left"></i>
														</a>
														</c:if>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="javascript:void(0);" onclick="selectCourse(${page})" value="${page }">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="javascript:void(0);" onclick="selectCourse(${page})">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum + 4 < totalPage}">
													<li class="next">
														<a href="javascript:void(0);" onclick="selectCourse(${page + 1})">
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
		
		/*---------------------------老师开始-----------------------------*/
		
		
		/**
			*跳转申请修改的页面
			*/
			function applyEditCourse(id){
				window.location.href="addTeacherCoursePage.do?id="+id;
			}
		
			/*---------------------------老师结束-----------------------------*/
		
		
		/*---------------------------管理员-----------------------------*/
		
		
		$(function(){
			/**
			* 改变显示的内容
			*/
			$(".js_select_teacher").change(function(){
				selectCourse(1);
			});
			$(".js_select_major").change(function(){
				selectCourse(1);
			});
		});
		
		function selectCourse(pageNum){
			var search = $(".js_search").val();
			var teacherId = $(".js_select_teacher").val();
			var specialtyId = $(".js_select_major").val();
			window.location.href="courseListBySearch.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&specialtyId="+specialtyId+"&teacherId="+teacherId;
		}
		
		
		function changeStatus(id,isFinish){
			var finish = 0;
			var clazz = ".js_isFinish"+id;
			if(isFinish == 0){//原来为没有选中，点击按钮后，让它选中
				$(clazz).attr("checked", true);
				finish = 1;
			}else{//原来选中，点击按钮后，让它不选中
				$(clazz).attr("checked", false);
			}
			var user = {};
			user.id = id;
			user.isfinish = finish;
			
			$.post("editStatus.do",user,function(data){
				var label = ".js_label"+id;
				if(data == true){
					if(finish == 1){
						var html = "<input name='switch-field-1' onChange='changeStatus("+id+","+finish+")' checked class='js_isFinish"+id +" ace ace-switch ace-switch-4' type='checkbox' />";
						$(".js_th"+id).text("可选");
						layer.alert('该门课程可以选课了!', {icon: 6, time:2000});
					}else{
						var html = "<input name='switch-field-1' onChange='changeStatus("+id+","+finish+")' class='js_isFinish"+id +" ace ace-switch ace-switch-4' type='checkbox' />";
						$(".js_th"+id).text("不可选");
						layer.alert('该门课程选课时间结束了!', {icon: 5});
					}
					$(clazz).replaceWith(html);
				}else{
					layer.alert('系统错误!', {icon: 5});
				}
			}); 
			
		}
		
		
		
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
			/*
			*删除课程
			*/
			function deleteCourse(id){
				layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteCourse.do",{"id":id},function(data){
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
			
			/*---------------------------管理员结束-----------------------------*/
			/*---------------------------学生开始-----------------------------*/	
			function deleteStudentCourse(id){
				layer.confirm('确认要退选吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteStudentCourse.do",{"id":id},function(data){
				    	if(data == true){
				    		layer.msg('退选成功!', {icon: 6,time:1000},function(){
				    			history.go(0);
							});
				    	}else{
				    		layer.msg("退选失败!",{icon:5});
				    	}
				    });
				});
			}
			
			
			function addStudentCourse(courseId,studentId){
				var studentCourse = {};
				studentCourse.courseid = courseId;
				studentCourse.studentid = studentId;
				layer.confirm('确认要选该门课程吗?', {icon: 3, title:'提示'}, function(){
				    $.post("addStudentCourse.do",studentCourse,function(data){
				    	if(data == true){
				    		layer.msg('选课成功!', {icon: 6,time:1000},function(){
				    			history.go(0);
							});
				    	}else{
				    		layer.msg("选课失败!",{icon:5});
				    	}
				    });
				});
			}
			
				/*---------------------------学生结束-----------------------------*/	
		</script>
</body>
</html>
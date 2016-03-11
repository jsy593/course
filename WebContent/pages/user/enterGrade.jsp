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
									<!-- 老师显示的下拉框开始 -->	
										<c:if test="${sessionScope.user.identity == 1}">
										<label class="control-label no-padding-right" for="form-field-1">请选择:</label>
											<select name="courseid" value="${courseId }" class="js_select_course" id="form-field-1">
												<option value="-1" selected>所有</option>
												<c:forEach items="${courses }" var="course">
													<option value="${course.id }" <c:if test="${course.id == courseId}">selected</c:if>>${course.name }</option>
												</c:forEach>
											</select>
										
										</c:if>	
									<!-- 老师显示的下拉框开结束-->
									
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
														<th>专业</th>
														<th>班级</th>
														<th>课程名</th>
														<th>成绩</th>
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
														<th>${user.mname }</th>
														<th>${user.cname }</th>
														<th>${user.courseName}</th>
														<th >
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
																<div class="form-group">
																		<div class="col-sm-9">
																		<c:if test="${user.grade != null }">
																			<label><b>${user.grade}</b></label>
																		</c:if>
																		<c:if test="${user.grade == null }">
																			<input  id="form-field-3" type="text" userId="${user.id }" courseId="${user.courseId}" class="js_input col-xs-10 col-sm-5"/>
																		</c:if>
																		</div>
																</div>
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
								<!---------------------------------提交按钮开始 ------------------------------->
										<div style="text-align:center;">
											<button class="btn btn-info" type="button" onclick="addGrade()">
												<i class="icon-ok bigger-110"></i>
												录入成绩
											</button>
											
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset" onclick="reset()">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
								<!---------------------------------提交按钮结束      ------------------------------->	
										<!-- 分页开始 -->
												<ul class="pagination pull-right no-margin">
												
													<li class="prev">
													<c:if test='${pageNum > 1}'>
														<a href="javascript:void(0);" onclick="selectStudent(${pageNum - 1})" >
															<i class="icon-double-angle-left"></i>
														</a>
														</c:if>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudent(${page})" value="${page }">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudent(${page})">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum + 4 < totalPage}">
													<li class="next">
														<a href="javascript:void(0);" onclick="selectStudent(${page + 1})">
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
		
		function addGrade(){
			var studentCourses = [];
			$(".js_input").each(function(){
			    var grade = $.trim($(this).val());
			    var studentId = $(this).attr("userId");
			    var courseId = $(this).attr("courseId");
			    var studentCourse = {};
			    
			    if(grade != null && grade != ""){
				    studentCourse.grade = grade;
				    studentCourse.studentid = studentId;
				    studentCourse.courseid = courseId;
				    studentCourses.push(studentCourse);
			    }
			});
			$.post("addGrades.do",{"studentCourses":JSON.stringify(studentCourses)},function(data){//批量添加成绩
				if(data == true){
					layer.msg('批量录入成绩成功!', {icon: 6,time:1000},function(){
						history.go(0);
					});
				}else{
					layer.msg('批量录入成绩失败!', {icon: 5,time:1000},function(){
		    			history.go(0);
					});
				}
			});
		}
		
		
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
			$(".js_select_course").change(function(){
				selectStudent(1);
			});
		});
		
		function selectStudent(pageNum){
			var search = $(".js_search").val();
			var courseId = $(".js_select_course").val();
			var teacherId = $(".js_userid").val();
			window.location.href="studentListBySearchGradePage.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&courseId="+courseId+"&teacherId="+teacherId;
		}
		
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
		
		</script>
</body>
</html>
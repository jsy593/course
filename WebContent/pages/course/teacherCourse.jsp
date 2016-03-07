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
							<a href="#">申请管理</a>
						</li>
						<li class="active">申请信息</li>
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
														<th>课程名称</th>
														<th>申请原因</th>
														<th>申请时间</th>
														<th>申请状态</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${teacherCourses}" var="teacherCourse" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${teacherCourse.cname }</th>
														<th>${teacherCourse.content}</th>
														<c:if test="${teacherCourse.isAgree == 0}">
															<th>待反馈</th>
														</c:if>
														<c:if test="${teacherCourse.isAgree == 1}">
															<th>同意</th>
														</c:if>
														<c:if test="${teacherCourse.isAgree == 2}">
															<th>拒绝</th>
														</c:if>
														<th>${teacherCourse.time }</th>
														<th>
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
															<c:if test="${teacherCourse.isAgree  == 0}">
																<button class="btn  btn-sm btn-success " onclick="editCourse(${course.id },1)">同意</button>
																
																<button class="btn  btn-sm btn-danger " onclick="editCourse(${course.id },2)">拒绝</button>
															</c:if>
															
															<c:if test="${teacherCourse.isAgree  != 0}">
																<a class="red" href="javascript:void(0);" onclick="deleteteacherCourse(${teacherCourse.id })">
																	<i class="icon-trash bigger-130"></i>
																</a>
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
														<a href="teacherCourseListByPage.do?pageSize=5&pageNum=${pageNum-1}">
															<i class="icon-double-angle-left"></i>
														</a>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="teacherCourseListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="teacherCourseListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum < totalPage}">
													<li class="next">
														<a href="teacherCourseListByPage.do?pageSize=5&pageNum=${page+1}">
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
		
			function editCourse(id,isAgree){
				alert("!232");
				var teacherCourse = {};
				teacherCourse.id = id;
				teacherCourse.isagree =isAgree;
				
				
				$.post("updateTeacherCourse.do",teacherCourse,function(data){
					if(data == true){
						layer.alert('修改成功!', {icon: 6, time:2000},function(){
							window.location.reload();
						});
					}else{
						layer.alert('修改失败!', {icon: 5}),function(){
							window.location.reload();
						};
					}
				}); 
			}
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
			function deleteteacherCourse(id){
				layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteTeacherCourse.do",{"id":id},function(data){
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
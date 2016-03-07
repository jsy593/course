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
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															

																<a class="green" href="editCoursePage.do?id=${course.id }">
																	<i class="icon-pencil bigger-130"></i>
																</a>
																
																<button class="btn btn-minier btn-yellow" onclick="applyEditCourse(${course.id })">申请修改</button>
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
		
		
			/**
			*跳转申请修改的页面
			*/
			applyEditCourse(id){
				
			}
			
		
		
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
			/**
			*选课的开启与关闭
			*/
			$(".js_isFinish").change(function(){
				if($(".js_isFinish").is(':checked')){
					
				}
			});
		});
		
		
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
		
		function selectCourse(pageNum){
			var search = $(".js_search").val();
			var teacherId = $(".js_select_teacher").val();
			var specialtyId = $(".js_select_major").val();
			window.location.href="courseListBySearch.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&specialtyId="+specialtyId+"&teacherId="+teacherId;
		}
		
		
		
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
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
		</script>
</body>
</html>
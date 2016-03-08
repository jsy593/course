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
							<a href="#">学生管理</a>
						</li>
						<li class="active">学生列表</li>
					</ul><!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
									<div class="form-group" style="float:right;padding:0px 20px 0px 0px">
										<label class="control-label no-padding-right" for="form-field-1">请选择
										</label>
											
											<c:if test="${sessionScope.user.identity ==1 }">
											<select name="classid" value="${courseid }" class="js_select_course" id="form-field-1">
												<option value="-1" selected>所有</option>
												<c:forEach items="${courses }" var="clazz">
													<option value="${course.id }" <c:if test="${course.id == courseid}">selected</c:if>>${course.name }</option>
												</c:forEach>
											</select>
											</c:if>
											
											
											<span class="input-icon">
												<input type="text"  value="${search }" placeholder="学生编号或者姓名 ..." class="js_search nav-search-input" id="nav-search-input" autocomplete="off" />
												<button class="btn btn-info" type="button" onclick="selectUser(1)">
														<i class="icon-search bigger-110"></i>
															搜索
												</button>
											</span>
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
														<th>学号</th>
														<th>姓名</th>
														<th>班级</th>
														<th>课程名</th>
														<th>课程学分</th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${studentCourses}" var="studentCourse" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${studentCourse.usernumber }</th>
														<th>${studentCourse.username }</th>
														<th>${studentCourse.cname }</th>
														<th>${studentCourse.classname }</th>
														<th>${studentCourse.credit }</th>
														<th>${studentCourse.time }</th>
														<th>
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
																<a class="blue" href="addStudentCoursePage.do" >
																	<i class="icon-plus-sign bigger-130"></i>
																</a>

																<a class="red" href="javascript:void(0);" onclick="deleteStudentCourse(${studentCourse.id })">
																	<i class="icon-trash bigger-130"></i>
																</a>
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
		
		/**
		* 改变显示的内容
		*/
		$(function(){
			$(".js_select_course").change(function(){
				selectUser(1);
			});
		});
		
		function selectUser(pageNum){
			var search = $(".js_search").val();
			var courseid = $(".js_select_course").val();
			window.location.href="userListBySearch.do?pageNum="+pageNum+"&pageSize=5&search="+search+"&courseid="+courseid;
		}
		
		
		
		
			/*设置日历颜色*/
			laydate.skin('molv');
			
			function deleteUser(id){
				layer.confirm('确认要删除该学生吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteStudentCourse.do",{"id":id},function(data){
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
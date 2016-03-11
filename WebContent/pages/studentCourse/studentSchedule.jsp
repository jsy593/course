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
							<a href="#">成绩查询</a>
						</li>
					</ul><!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="form-group" style="float:right;padding:0px 20px 0px 0px">
							<input type="hidden" value="${date }" class="js_hidden_date">
										<label class="control-label no-padding-right" for="form-field-1">日期:
										</label>
											<select name="date" class="js_select_date" id="form-field-1">
											</select>
											<select name="flag" class="js_select_flag" id="form-field-1">
												<option value="1" <c:if test="${flag == 1}">selected</c:if>>上学期</option>
												<option value="2" <c:if test="${flag == 2}">selected</c:if>>下学期</option>
											</select>
							</div>
							
							<input type="hidden" value="${sessionScope.user.id }" class="js_studentId"/>
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
														<th>任课老师</th>
														<th>上课地址</th>
														<th>上课时间</th>
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
														<th>${studentCourse.coursenumber }</th>
														<th>${studentCourse.name }</th>
														<th>${studentCourse.teachername }</th>
														<th>${studentCourse.address }</th>
														<th>${studentCourse.schooltime }</th>
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
														<a href="javascript:void(0);" onclick="selectStudentSchedule(${pageNum - 1})" >
															<i class="icon-double-angle-left"></i>
														</a>
														</c:if>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudentSchedule(${page})" value="${page }">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudentSchedule(${page})">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum + 4 < totalPage}">
													<li class="next">
														<a href="javascript:void(0);" onclick="selectStudentSchedule(${page + 1})">
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
			
			/*---------------------------学生开始-----------------------------*/	
			
			$(function(){
				var data;
				var date = $(".js_hidden_date").val();
				for(var i = 2012;i < 2055;i++){
					data=i+"-"+(i+1);
					if(date == data) {
						$(".js_select_date").append("<option value='"+data+"' selected>"+data+"</option>");
					}else{
						$(".js_select_date").append("<option value='"+data+"'>"+data+"</option>");
					}

				}
			});		
				
			
				$(function(){
					$(".js_select_date").change(function(){
						selectStudentSchedule(1);
					});
					$(".js_select_flag").change(function(){
						selectStudentSchedule(1);
					});
				});
			
				function selectStudentSchedule(pageNum){
					var flag = $(".js_select_flag").val();
					var date = $(".js_select_date").val();
					window.location.href="studentSchedule.do?pageNum="+pageNum+"&pageSize=5&studentId="+$(".js_studentId").val()+"&flag="+flag+"&date="+date;
				}
			
			
			
			/*---------------------------学生结束-----------------------------*/	
		</script>
</body>
</html>
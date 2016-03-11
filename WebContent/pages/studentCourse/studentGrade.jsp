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
<!-- 							<div class="form-group" style="float:left;padding:0px 20px 0px 0px"> -->
<!-- 								<button class="btn btn-info" type="button" onclick="selectStudentGrade(1,1)">通过</button> -->
<!-- 								<button class="btn btn-info" type="button" onclick="selectStudentGrade(1,2)">未通过</button> -->
<!-- 							</div> -->
										<label class="control-label no-padding-right"  style="float:left">您的当前所修学分:${sessionScope.user.credit }</label>
							<div class="form-group" style="float:right;padding:0px 20px 0px 0px">
										<label class="control-label no-padding-right" for="form-field-1">请选择
										</label>
											<select name="status" class="js_select_status" id="form-field-1">
											<option value="-1">所有</option>
												<option value="1" <c:if test="${status == 1}">selected</c:if>>通过</option>
												<option value="2" <c:if test="${status == 2}">selected</c:if>>未通过</option>
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
														<th>所属班级</th>
														<th>课程学分</th>
														<th>任课老师</th>
														<th>成绩</th>
														<th>结果</th>
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
														<th>${studentCourse.username }</th>
														<th>${studentCourse.classname }</th>
														<th>${studentCourse.credit }</th>
														<th>${studentCourse.teachername }</th>
														<th>${studentCourse.grade }</th>
														<th>
															<c:choose>
																<c:when test="${studentCourse.grade == null}">暂无</c:when>
															   <c:when test="${studentCourse.grade < 60}">不合格</c:when>
															   <c:otherwise>合格 </c:otherwise>  
															</c:choose>
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
														<a href="javascript:void(0);" onclick="selectStudentGrade(${pageNum - 1})" >
															<i class="icon-double-angle-left"></i>
														</a>
														</c:if>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudentGrade(${page})" value="${page }">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="javascript:void(0);" onclick="selectStudentGrade(${page})">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum + 4 < totalPage}">
													<li class="next">
														<a href="javascript:void(0);" onclick="selectStudentGrade(${page + 1})">
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
					$(".js_select_status").change(function(){
						selectStudentGrade(1,$(".js_select_status").val());
					});
				});
			
				function selectStudentGrade(pageNum,status){
					if(status == undefined || status == null){
						status = -1;
					}
					window.location.href="studentGrade.do?pageNum=1&pageSize=5&studentId="+$(".js_studentId").val()+"&status="+status;
				}
			
			
			
			/*---------------------------学生结束-----------------------------*/	
		</script>
</body>
</html>
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
							<a href="#">学院管理</a>
						</li>
						<li class="active">学院信息</li>
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
														<th>学院名</th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${colleges}" var="college" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${college.name }</th>
														<th>${college.createTime }</th>
														<th>
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
																<a class="blue" href="addCollegePage.do" >
																	<i class="icon-plus-sign bigger-130"></i>
																</a>
																<!-- <a class="blue" href="#">
																	<i class="icon-zoom-in bigger-130"></i>
																</a> -->

																<a class="green" href="editCollegePage.do?id=${college.id }">
																	<i class="icon-pencil bigger-130"></i>
																</a>

																<a class="red" href="javascript:void(0);" onclick="deleteCollege(${college.id })">
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
														<a href="collegeListByPage.do?pageSize=5&pageNum=${pageNum-1}">
															<i class="icon-double-angle-left"></i>
														</a>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="collegeListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="collegeListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum < totalPage}">
													<li class="next">
														<a href="collegeListByPage.do?pageSize=5&pageNum=${page+1}">
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
			/*设置日历颜色*/
			laydate.skin('molv');
			
			function deleteCollege(id){
				layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteCollege.do",{"id":id},function(data){
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
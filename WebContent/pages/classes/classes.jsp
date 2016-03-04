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
							<a href="#">专业管理</a>
						</li>
						<li class="active">专业信息</li>
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
														<th>班级名</th>
														<th>所属专业</th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${classes}" var="clazz" >
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>${clazz.name }</th>
														<th>${clazz.mname }</th>
														<th>${clazz.createTime }</th>
														<th>
															<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															
																<a class="blue" href="addClassPage.do" >
																	<i class="icon-plus-sign bigger-130"></i>
																</a>
																<!-- <a class="blue" href="#">
																	<i class="icon-zoom-in bigger-130"></i>
																</a> -->

																<a class="green" href="editClassPage.do?id=${clazz.id }">
																	<i class="icon-pencil bigger-130"></i>
																</a>

																<a class="red" href="javascript:void(0);" onclick="deleteClass(${clazz.id })">
																	<i class="icon-trash bigger-130"></i>
																</a>
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
														<a href="classListByPage.do?pageSize=5&pageNum=${pageNum-1}">
															<i class="icon-double-angle-left"></i>
														</a>
													</li>

													<!-- forEach标签开始 -->
						
												<c:if test="${pageNum+4 <= totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${pageNum +4 }">
															<li >
																<a href="classListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
												
												<c:if test="${pageNum+4> totalPage}">
													<c:forEach 	var="page" begin="${pageNum}" end="${totalPage }">
															<li >
																<a href="classListByPage.do?pageSize=5&pageNum=${page}">${page }</a>
															</li>
														</c:forEach>
												</c:if>
													<!-- forEach标签结束 -->
													
												<c:if test="${pageNum < totalPage}">
													<li class="next">
														<a href="classListByPage.do?pageSize=5&pageNum=${page+1}">
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
			
			function deleteClass(id){
				layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(){
				    $.post("deleteClass.do",{"id":id},function(data){
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
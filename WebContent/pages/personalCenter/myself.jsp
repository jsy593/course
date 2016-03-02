<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ch">
<script type="text/javascript">
</script>
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
							<a href="#">Other Pages</a>
						</li>
						<li class="active">Blank Page</li>
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
							<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">工号</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="请输入..." class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">姓名</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-2" placeholder="请输入..." class="col-xs-10 col-sm-5" />
										</div>
									</div>
										
									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 性别</label>
										<div class="col-sm-9">
														<label>
															<input name="form-field-radio" type="radio" checked  class="ace" />
															<span class="lbl">男</span>
														</label>
															<label>
															<input name="form-field-radio" type="radio" class="ace" />
															<span class="lbl">女</span>
														</label>
															<label>
															<input name="form-field-radio" type="radio" class="ace" />
															<span class="lbl">未知</span>
														</label>
										</div>
									</div>
										
										
										
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 入职日期 </label>
											
											<div class="input-group col-sm-5">
													<input class="form-control date-picker" id="id-date-picker-1" type="text" data-date-format="dd-mm-yyyy" />
													<span class="input-group-addon">
														<i class="icon-calendar bigger-110 "></i>
													</span>
											</div>
											
											
											<div class="widget-body">
													<div class="widget-main">
														<label for="id-date-picker-1">Date Picker</label>

														<div class="row">
															<div class="col-xs-8 col-sm-11">
																<div class="input-group">
																	<input class="form-control date-picker" id="id-date-picker-1" type="text" data-date-format="dd-mm-yyyy" />
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
																</div>
															</div>
														</div>

														<hr />
														<label for="id-date-range-picker-1">Date Range Picker</label>

														<div class="row">
															<div class="col-xs-8 col-sm-11">
																<div class="input-group">
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>

																	<input class="form-control" type="text" name="date-range-picker" id="id-date-range-picker-1" />
																</div>
															</div>
														</div>

														<hr />
														<label for="timepicker1">Time Picker</label>

														<div class="input-group bootstrap-timepicker">
															<input id="timepicker1" type="text" class="form-control" />
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
														</div>
													</div>
												</div>
									</div>

									<div class="space-4"></div>
					</form>				
						
				<!---------------------------------提交按钮开始 ------------------------------->
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>
				<!---------------------------------提交按钮结束      ------------------------------->		
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<script type="text/javascript">
			window.jQuery || document.write("<script src='../assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
</body>
</html>
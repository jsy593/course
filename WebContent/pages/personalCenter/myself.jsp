<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ch">
<script type="text/javascript">
</script>
<script src="/course/js/jquery-1.9.1.min.js"></script>
<script src="/course/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/course/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="/course/assets/js/chosen.jquery.min.js"></script>
<script src="/course/assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="/course/assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="/course/assets/js/date-time/moment.min.js"></script>
<script src="/course/assets/js/date-time/daterangepicker.min.js"></script>
<script src="/course/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="/course/assets/js/jquery.knob.min.js"></script>
<script src="/course/assets/js/jquery.autosize.min.js"></script>
<script src="/course/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="/course/assets/js/jquery.maskedinput.min.js"></script>
<script src="/course/assets/js/bootstrap-tag.min.js"></script>
<link rel="stylesheet" href="/course/assets/css/daterangepicker.css" />
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
											
											<div class="col-sm-9">
													<input   id="form-field-3" type="text" placeholder="请选择日期..."  onclick="laydate()" class="col-xs-10 col-sm-5"/>
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
			/*设置日历颜色*/
			laydate.skin('molv');
		</script>
</body>
</html>
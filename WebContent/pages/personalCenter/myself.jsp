<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}"/> --%>
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
							<a href="#">个人中心</a>
						</li>
						<li class="active">个人信息</li>
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
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">我的头像:</label>

										<div class="col-sm-9">
											<label class="col-sm-5 col-xs-10" for="form-field-1">
											<img src="${ctx }/${sessionScope.user.imageUrl}" class="js_imgUrl img-circle" width="140" height="140">
											</label>
										</div>
									</div>
							
							
								<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><c:if test="${sessionScope.user.identity  ==2}">学</c:if>
										<c:if test="${sessionScope.user.identity  != 2}">工</c:if>
										号:</label>
										<div class="col-sm-9">
											<label class="col-sm-5 col-xs-10" for="form-field-1">
											${sessionScope.user.userNumber}</label>
										</div>
								</div>
									
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">姓名:</label>

										<div class="col-sm-9">
											<label class="col-sm-5 col-xs-10" for="form-field-1">
											${sessionScope.user.username}</label>
										</div>
									</div>
										
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 性别:</label>
										<div class="col-sm-9">
														<label>
															<c:if test="${sessionScope.user.sex == 1}"> <input type="radio" checked value="1" class="ace js_radio" />
															<span class="lbl">男</span></c:if>
														</label>
															<label>
															<c:if test="${sessionScope.user.sex == 2}"><input type="radio" checked value="2" class="ace js_radio" />
															<span class="lbl">女</span></c:if>
														</label>
														<label>
															<c:if test="${sessionScope.user.sex == 0}"> <input type="radio" checked  value="0" class="ace js_radio" />
															<span class="lbl">未知</span></c:if>
														</label>
										</div>
									</div>
										
						
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 入职日期 :</label>
											
											<div class="col-sm-9">
											<label class="col-sm-5 col-xs-10" for="form-field-1">
											<fmt:formatDate value='${sessionScope.user.createTime}'  type="date" pattern='yyyy-MM-dd'/></label>
											</div>
									</div>

									<div class="space-4"></div>
							
									<%-- <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><c:if test="${sessionScope.user.identity  ==2}">学</c:if>
										<c:if test="${sessionScope.user.identity  != 2}">工</c:if>
										号:</label>

										<div class="col-sm-9">
											<input type="text" readOnly  id="form-field-1" value="${sessionScope.user.userNumber}" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">姓名</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-2" readOnly value="${sessionScope.user.username}" class="col-xs-10 col-sm-5" />
										</div>
									</div>
										
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 性别</label>
										<div class="col-sm-9">
														<label>
															<input name="form-field-radio" <c:if test="${sessionScope.user.sex == 1}">checked</c:if> type="radio" value="1" class="ace js_radio" />
															<span class="lbl">男</span>
														</label>
															<label>
															<input name="form-field-radio" <c:if test="${sessionScope.user.sex == 2}">checked</c:if> type="radio" value="2" class="ace js_radio" />
															<span class="lbl">女</span>
														</label>
															<label>
															<input name="form-field-radio" <c:if test="${sessionScope.user.sex !=1 && sessionScope.user.sex != 2}">checked</c:if> type="radio" value="0" class="ace js_radio" />
															<span class="lbl">未知</span>
														</label>
										</div>
									</div>
										
						
						
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 入职日期 </label>
											
											<div class="col-sm-9">
													<input   id="form-field-3" readOnly="true" type="text" value="${sessionScope.user.createTime}"  class="col-xs-10 col-sm-5"/>
											</div>
									</div>

									<div class="space-4"></div> --%>
									
					</form>				
						
				<!---------------------------------提交按钮开始 ------------------------------->
<!-- 						<div class="clearfix form-actions"> -->
<!-- 										<div class="col-md-offset-3 col-md-9"> -->
<!-- 											<button class="btn btn-info" type="button"> -->
<!-- 												<i class="icon-ok bigger-110"></i> -->
<!-- 												提交 -->
<!-- 											</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
				<!---------------------------------提交按钮结束      ------------------------------->		
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		<script type="text/javascript">
		
		if("${message}"){
	        layer.msg('${message}', {
	            offset: 0,
	        });
	    }
	    if("${error}"){
	        layer.msg('${error}', {
	            offset: 0,
	            shift: 6
	        });
	    }
		
			/*设置日历颜色*/
			laydate.skin('molv');
		</script>
</body>
</html>
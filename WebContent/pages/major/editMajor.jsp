<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
							<a href="majorListByPage.do">专业管理</a>
						</li>
						<li class="active">修改专业</li>
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
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">请选择学院
										</label>
										
										<div class="col-sm-9">
											<select class="js_select col-xs-10 col-sm-5" id="form-field-1">
											<c:forEach items="${colleges}" var="college">
												<option value="${college.id}" <c:if test="${college.id == major.collegeid }">selected</c:if>>${college.name }</option>
											</c:forEach>
											</select>
										</div>
									</div>
							
							
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">专业名称
										</label>

										<div class="col-sm-9">
											<input type="text"  id="form-field-1" value="${major.name }" class="js_name col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
										
									<div class="space-4"></div>
						
						<!-- 管理员可修改 -->				
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 入职日期 </label>
											
											<div class="col-sm-9">
													<input   id="form-field-3" type="text"  value="<fmt:formatDate value='${major.createtime }'  type="date" pattern='yyyy-MM-dd'/>" onclick="laydate()" class="js_createTime col-xs-10 col-sm-5"/>
											</div>
									</div>

									<div class="space-4"></div>
									
					</form>				
						
				<!---------------------------------提交按钮开始 ------------------------------->
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="editMajor(${major.id})">
												<i class="icon-ok bigger-110"></i>
												提交
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
			
			function editMajor(id){
				var name = $.trim($(".js_name").val());
				if(name == "" || name == null){
					layer.alert('请输入专业名称!', {icon: 5});
					return;
				}
				
				var createTime = $.trim($(".js_createTime").val());
				if(createTime == null  || createTime ==""){
					layer.alert('请选择日期!', {icon: 5});
					return;
				}
				
				var mapVo = {};
				mapVo.name = name;
				mapVo.createtime = createTime;
				mapVo.id = id;
				mapVo.collegeid = $(".js_select").val(); 
				$.post("updateMajor.do",{'mapVo':mapVo},function(data){
					if(data == true){
						layer.msg('修改成功!', {icon: 6,time:2000},function(){
							window.location.reload();
						});
					}else{
						layer.msg('修改失败!', {icon: 5});
					}
				}); 
			}
		</script>
</body>
</html>
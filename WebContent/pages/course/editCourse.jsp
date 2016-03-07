<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
							<a href="courseListByPage.do?pageNum=1&pageSize=5">课程管理</a>
						</li>
						<li class="active">修改课程</li>
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
							<form class="js_form form-horizontal" role="form" action="editCourse.do">
							<input type="hidden" value="${course.id }" name="id"/>
							<!-- 所有 -->	
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">课程编号:
										</label>

										<div class="col-sm-9">
											<input type="text" name="coursenumber" value="${course.coursenumber }" id="form-field-1"  class="js_coursenumber col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">课程名称:
										</label>

										<div class="col-sm-9">
											<input type="text" name="name"  value="${course.name}" id="form-field-1"  class="js_coursename col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
							
							
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">课程学分:
										</label>

										<div class="col-sm-9">
											<input type="text" name="credit" value="${course.credit}" id="form-field-1"  class="js_credit col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">上课老师:
										</label>

										<div class="col-sm-9">
											<select name="teacherid" class="js_select_role col-xs-10 col-sm-5" id="form-field-1">
												<c:forEach items="${teachers }" var="teacher">
												<option value="${teacher.id }" <c:if test="${course.teacherid == teacher.id}">selected</c:if>>${teacher.username }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									
									
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">上课时间:
										</label>

										<div class="col-sm-9">
											<input type="text" name="schooltime" value="${course.schooltime}"  id="form-field-1"  class="js_schooltime col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">上课地址:
										</label>

										<div class="col-sm-9">
											<input type="text" name="address"  value="${course.address}" id="form-field-1"  class="js_address col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
							
							
							
							
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">所属专业:
										</label>

										<div class="col-sm-9">
											<select name="specialtyid" class="js_select_role col-xs-10 col-sm-5" id="form-field-1">
												<c:forEach items="${majors }" var="major">
												<option value="${major.id }" <c:if test="${course.specialtyid == major.id}">selected</c:if>>${major.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="space-4"></div>
									
							
									<div class="form-group">
										<label class="js_labelName col-sm-3 control-label no-padding-right" for="form-field-1">选课人数:
										</label>

										<div class="col-sm-9">
											<input type="text" name="numberlimit"  value="${course.numberlimit}" id="form-field-1"  class="js_numberlimit col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="space-4"></div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 添加日期 :</label>
											
											<div class="col-sm-9">
													<input  id="form-field-3" type="text"  value="<fmt:formatDate value='${course.createtime }'  type="date" pattern='yyyy-MM-dd'/>" onclick="laydate()" class="js_createTime col-xs-10 col-sm-5"/>
											</div>
									</div>

									<div class="space-4"></div>
						
					
				</form>				
				<!---------------------------------提交按钮开始 ------------------------------->
				
						<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="editCourse()">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>
											
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset" onclick="reset()">
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
		/**
			添加课程
		*/
		function editCourse(){
// 			$(".js_form").submit();
			
			var coursenumber = $.trim($(".js_coursenumber").val());
			if(coursenumber == "" || coursenumber == null){
				layer.alert('请输入课程编号!', {icon: 5});
				return;
			}
			
			var name = $.trim($(".js_coursename").val());
			if(name == "" || name == null){
				layer.alert('请输入课程名!', {icon: 5});
				return;
			}
			
			var credit = $.trim($(".js_credit").val());
			if(credit == "" || credit == null){
				layer.alert('请输入该课程学分!', {icon: 5});
				return;
			}
			
			var schooltime = $.trim($(".js_schooltime").val());
			if(schooltime == "" || schooltime == null){
				layer.alert('请输入该上课时间!', {icon: 5});
				return;
			}
			
			
			var address = $.trim($(".js_address").val());
			if(address == null  || address ==""){
				layer.alert('请输入上课地点!', {icon: 5});
				return;
			}
			var numberLimit = $.trim($(".js_numberlimit").val());
			if(numberLimit == null  || numberLimit ==""){
				layer.alert('请选择该课程最大容量人数!', {icon: 5});
				return;
			}
			
			var createTime = $.trim($(".js_createTime").val());
			if(createTime == null  || createTime ==""){
				layer.alert('请选择日期!', {icon: 5});
				return;
			}
			
			
			var course = $(".js_form").serialize();
			 $.post($(".js_form").attr("action")+"?createTime="+$(".js_createTime").val(),course,function(data){
				if(data == true){
					layer.alert('修改成功!', {icon: 6, time:2000},function(){
						window.location.reload();
					});
				}else{
					layer.alert('修改失败!', {icon: 5});
				}
			}); 
		}
// 		$( "#input-size-slider" ).css('width','200px').slider({
// 			value:1,
// 			range: "min",
// 			min: 1,
// 			max: 8,
// 			step: 1,
// 			slide: function( event, ui ) {
// 				var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
// 				var val = parseInt(ui.value);
// 				$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
// 			}
// 		});
			/*设置日历颜色*/
			laydate.skin('molv');
		</script>
</body>
</html>
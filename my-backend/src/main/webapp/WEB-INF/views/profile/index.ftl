<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="个人中心" openIndex="1" activeIndex="9999" base_js=["/js/cropper/cropper.min.js","/my/avatar/main.js"] base_css=["/js/cropper/cropper.min.css","/my/avatar/main.css"]>
	
	<section class="profile-env">
		<div class="row">

			<div class="col-sm-3">
				<!-- 左侧 -->
				<div class="user-info-sidebar">
					<a href="#" class="user-img" data-toggle="modal" data-target="#avatar-modal">
						<#if user.avatar!=''>
							<img src="${imgApi}${user.avatar}" alt="user-img" class="img-cirlce img-responsive img-thumbnail">
						</#if>
						<#if user.avatar==''>
							<img src="/img/user-4.png" alt="user-img" class="img-cirlce img-responsive img-thumbnail">
						</#if>
						
					</a>
					<a href="#" onclick="showBigImg('${imgApi}${user.avatar}')" class="table-a">【大图】</a>
					<a href="#" class="user-name">
						${user.username}
					</a>
					<hr>
					<ul class="list-unstyled user-info-list">
						<li>
							<i class="fa fa-phone fa-fw"></i> ${user.phone!''}
						</li>
						<li>
							<i class="fa fa-envelope-o fa-fw"></i> ${user.email!''}
						</li>
						<li>
							<i class="fa fa-eye"></i> ${roleName!''} <a href="#" onclick="changeRole()"><b>【修改权限】</b></a>
						</li>
					</ul>	
					
					<#if !isSelf && user.status==1>
						<button type="button" onclick="changeUserStatus('2', '${user.id}')" class="btn btn-danger btn-block text-left">
							禁用 <i class="fa fa-ban pull-right"></i>
						</button>
					</#if>
					<#if !isSelf && user.status==2>
						<button type="button" onclick="changeUserStatus('1', '${user.id}')" class="btn btn-success btn-block text-left">
							启用 <i class="fa fa-check pull-right"></i>
						</button>
					</#if>
				</div>
			</div>
			<!-- 左侧  结束-->
			
			<!-- 右侧 -->		
			<div class="col-sm-9">
				<!-- 登录历史 仅管理员显示 -->
				<#include "login_record_list.ftl">
				<!-- 修改基础信息 -->
				<#include "form_baseinfo.ftl">
			</div>
			<!-- 右侧结束 -->
			
		</div>
	</section>
	
	<script>
		//屏蔽或者启用用户操作
		function changeUserStatus(status, userId){
			changeUserStatusCallback(status, userId, function(data){
				if(data.errorNo==200){
					self.location="";
					swal('操作成功', '', 'success');
				} else {
					swal('操作失败', data.errorInfo, 'error');
				}
			});
		}
		
		//查看头像大图
		function showBigImg(imgUrl){
			var html = '<img src="'+imgUrl+'">';
			$.common.info(html);
		}
		
		//修改用户权限
		function changeRole(){
			var html = '<form id="roleHtml"><i class="fa fa-spinner fa-pulse"></i>数据加载中...</form>';
			$.common.dialog('修改用户权限', html, function(data){
				var formData = $.common.getFormJson(roleHtml);
				formData.id = ${user.id};
				$.common.postRequest(formData, '/backend/user/doSave', function(data){
					if(data.errorNo==200){
						$.common.tip('保存成功');
						location.reload();
					} else {
						$.common.error(data.errorInfo);
					}
				});
			});
			$('#roleHtml').load('/backend/profile/getRoleSelect?roleId='+${user.role});
		}
	</script>
	<style>
		.modal-backdrop{z-index:-1}
		.modal-content{z-index:2}
	</style>
	
	<#-- 头像上传弹框 -->
	<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<!--<form class="avatar-form" action="upload-logo.php" enctype="multipart/form-data" method="post">-->
					<form class="avatar-form" action="/backend/profile/doUploadAvatar" enctype="multipart/form-data" method="post">
						<div class="modal-header" style="padding-bottom:0px;">
							<button class="close" data-dismiss="modal" type="button">&times;</button>
							<!--<h4 class="modal-title" id="avatar-modal-label">上传图片</h4>-->
							<button class="btn btn-danger"  type="button" style="height: 35px;" onclick="$('input[id=avatarInput]').click();">请选择图片</button>
						</div>
						<div class="modal-body">
							<div class="avatar-body">
								<div class="avatar-upload">
									<input class="avatar-src" name="avatar_src" type="hidden">
									<input class="avatar-data" name="avatar_data" type="hidden">
									<input name="hideUserId" type="hidden" value="${user.id}">
									<!--<label for="avatarInput" style="line-height: 35px;">图片上传</label>-->
									<!--<button class="btn btn-danger"  type="button" style="height: 35px;" onclick="$('input[id=avatarInput]').click();">请选择图片</button>-->
									<span id="avatar-name"></span>
									<input class="avatar-input hide" id="avatarInput" name="avatar_file" type="file"></div>
								<div class="row">
									<div class="col-md-9">
										<div class="avatar-wrapper"></div>
									</div>
									<div class="col-md-3">
										<div class="avatar-preview preview-lg" id="imageHead"></div>
										<!--<div class="avatar-preview preview-md"></div>
								<div class="avatar-preview preview-sm"></div>-->
									</div>
								</div>
								<div class="row avatar-btns">
									<div class="col-md-4">
										<div class="btn-group">
											<button class="btn btn-danger fa fa-undo" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"> 向左旋转</button>
										</div>
										<div class="btn-group">
											<button class="btn  btn-danger fa fa-repeat" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"> 向右旋转</button>
										</div>
									</div>
									<div class="col-md-5" style="text-align: right;">								
										<button class="btn btn-danger fa fa-arrows" data-method="setDragMode" data-option="move" type="button" title="移动">
							            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
							            </span>
							          </button>
							          <button type="button" class="btn btn-danger fa fa-search-plus" data-method="zoom" data-option="0.1" title="放大图片">
							            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, 0.1)">
							              <!--<span class="fa fa-search-plus"></span>-->
							            </span>
							          </button>
							          <button type="button" class="btn btn-danger fa fa-search-minus" data-method="zoom" data-option="-0.1" title="缩小图片">
							            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, -0.1)">
							              <!--<span class="fa fa-search-minus"></span>-->
							            </span>
							          </button>
							          <button type="button" class="btn btn-danger fa fa-refresh" data-method="reset" title="重置图片">
								            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;reset&quot;)" aria-describedby="tooltip866214">
								       </button>
							        </div>
									<div class="col-md-3">
										<input type="submit" value="保存修改" class="btn btn-danger btn-block avatar-save fa fa-save" >
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="loading" aria-label="Loading" role="img" tabindex="-100"></div>
	
</@base> 
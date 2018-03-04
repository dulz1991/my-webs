<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="个人中心" openIndex="9999" activeIndex="9999">
	
	<section class="profile-env">
		<div class="row">

			<div class="col-sm-3">
				<!-- 左侧 -->
				<div class="user-info-sidebar">
					<a href="#" class="user-img">
						<img src="/img/user-4.png" alt="user-img" class="img-cirlce img-responsive img-thumbnail">
					</a>
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
	</script>
</@base> 
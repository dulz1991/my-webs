<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <div id="datatable">
			<table>
				<thead class="navbar-mystyle" >
					<tr>
						<th field="index_no">序号</th>
						<th field="username">用户名</th>
						<!-- <th field="username" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>用户名</th> -->
						<th field="phone">手机号</th>
						<th field="email">邮箱</th>
						<th field="roleName">用户权限</th>
						<th field="userStatusStr">用户状态</th>
						<th field="createTimeStr">创建时间</th>
						<th field="updateTimeStr">更新时间</th>
						<th field="button" 
							btn_list='[
			                {fnName:"$.biz.toDisable",args:"id,/backend/user/disable",name:"屏蔽",icon:"fa fa-ban",cls:"btn-danger",hidden:"hidepb"},
			                {fnName:"$.biz.toEnable",args:"id,/backend/user/enable",name:"恢复",icon:"fa fa-shield",cls:"btn-success",hidden:"hidehf"},
			                {fnName:"resetPwd",args:"id",name:"重置密码",cls:"btn-danger"},
			                {fnName:"editUserRole",args:"id",name:"编辑权限"}
			                ]'>操作</th>
					</tr>
				</thead>
			</table>
		</div>
    
<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/user/getList',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function viewDetail(id){
	gotoUrl("/backend/profile/index?userId="+id);
}

/* 重置密码 */
function resetPwd(id){
	$.pop.confirm("确定重置密码？重置后密码为123456", '/backend/user/resetPwd?id='+id, function(){
		$.pop.success('密码重置成功');
		$.fn.reload();
	});
}

/* 修改权限 */
function editUserRole(id){
	var parm = {};
	parm.loadUrl = '/backend/user/resetUserRolePage?userId='+id; 
   	parm.width = '400px';
   	parm.height = '200px';
   	$.pop.dialog(parm, editUserRoleVerify, function(data){
   		$.pop.success("保存成功");
   		$.fn.reload();
   	});
}
function editUserRoleVerify(){
	var formData = $.common.getFormJson('#editRoleForm');
	if($.common.isNotBlank(formData.roleCode)){
		if(formData.roleCode instanceof Array){
			formData.roleCode = formData.roleCode.join(',');
		}
	} else {
		$.pop.error("请选择权限");
	}
	var rst=''; 
	$.common.postSync(formData, '/backend/user/resetUserRole', function(data){
		if(data.errorNo!=0){
			rst = data.errorInfo;
		}
	});
	return rst;
}
</script>

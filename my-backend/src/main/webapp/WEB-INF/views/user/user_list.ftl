<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="用户管理" openIndex=1 activeIndex=activeIndex>
	<!-- 搜索区 -->
	<@searchForm class="form" title="搜索区">
		<@searchInput title="用户名" name="username" />
		<@searchInput title="手机号" name="phone" />
		<@searchInput type="hidden" name="role1" value="${role}" />
		<@searchSelect id="status" datas={"0":"禁用","1":"正常"} defaultValue="--全部--" title="用户状态" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
		
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">序号</th>
			<th field="username" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>用户名</th>
			<th field="phone">手机号</th>
			<th field="email">邮箱</th>
			<th field="roleName">用户权限</th>
			<th field="userStatusStr">用户状态</th>
			<th field="createTimeStr">创建时间</th>
			<th field="updateTimeStr">更新时间</th>
			<th field="button" 
				btn_list='[
                {fnName:"changeUserStatus",args:"status,id",name:"屏蔽",icon:"fa fa-ban",cls:"btn btn-danger btn-xs",hidden:"hidepb"},
                {fnName:"changeUserStatus",args:"status,id",name:"恢复",icon:"fa fa-shield",cls:"btn btn-success btn-xs",hidden:"hidehf"}
                ]'>操作</th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->

	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/user/getList?role=${role}',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function viewDetail(id){
		window.open("/backend/profile/index?userId="+id);
	}
	
	//状态操作
	function changeUserStatus(status, userId){
		if(status==1){
			status=2;
		} else if(status==2){
			status=1;
		}
		changeUserStatusCallback(status, userId, function(data){
			if(data.errorNo==200){
				$.fn.reload();
				swal('操作成功', '', 'success');
			} else {
				swal('操作失败', data.errorInfo, 'error');
			}
		});
	}
	</script>

</@base> 
<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="权限列表" openIndex=1 activeIndex=2>
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="权限列表">
			<a href="javascript:;" onclick="edit()" target="_blank"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" id="reloadBtn" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="id">权限id</th>
			<th field="roleName">权限名称</th>
			<th field="roleDescription">权限描述</th>
			<th field="button" field-role="2" width="110" 
			btn_list='[
            {fnName:"edit",args:"id,roleName",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
            ]'></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->

	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/userRole/getList'
		}); 
	});
	
	function edit(id, roleName){
		var title = "新增权限";
		var loadUrl = "/backend/userRole/edit";
		var saveUrl = "/backend/userRole/doSave";
		var formId = "roleForm";
		var html = '<form id="'+formId+'" class="form-horizontal form" action="javascript:void(0);"><i class="fa fa-spinner fa-pulse"></i>数据加载中...</div>';
		if($.common.isNotBlank(id)){
			title = "编辑权限-"+roleName;
			loadUrl = "/backend/userRole/edit?id="+id;
		}
		
		$.common.dialog(title, html, function(data){
			var formData = $.common.getFormJson('#'+formId+'');
			$.common.postRequest(formData, saveUrl, function(data){
				if(data.errorNo==200){
					$.common.tip('保存成功');
					$.fn.reload();
					$('#reloadBtn').trigger('click');
				} else {
					$.common.error(data.errorInfo);
				}
			});
		});
		$('#roleForm').load(loadUrl);
	}
	</script>

</@base> 
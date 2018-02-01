<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="博客分类" openIndex=3 activeIndex=1>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="分类名称" name="name" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="javascript:;" onclick="edit('','')"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.common.reloadTable()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="name" my-attrs='{textFun:"viewDetail",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>分类名称</th>
			<th field="button" field-role="2"
				btn_list='[
                {fnName:"edit", args:"id,name",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
                {fnName:"$.common.deleteById", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn btn-danger btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
	
	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/blogMenu/getList',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function edit(id,name){
		swal({
  			title: '编辑菜单',
  			html:'<input id="swal-input1" class="swal2-input" name="menuName" autofocus value="'+name+'">',
  			showCancelButton: true,   //显示取消按钮
			confirmButtonColor: '#3085d6', //俩个按钮的颜色
			confirmButtonText: '提交', //俩个按钮的文本
			cancelButtonText: '取消',
  			allowOutsideClick: false  
		}).then(function(result) {
			name=$('input[name="menuName"]').val();
			if(!$.common.isNotBlank(name)){
				$.common.tip("请输入菜单名称");
				return;
			}
			var parm = {};
			if($.common.isNotBlank(id)){
				parm.id=id;
			}
			parm.name=name;
			$.common.postRequest(parm, '/backend/blogMenu/doSave', function(data){
				if(data.errorNo==200){
					$.common.reloadTable();
				} else {
					$.common.tip(data.errorInfo);
				}
			});
		})
	}
	
	function viewDetail(id){
		window.open('/backend/blog/list?blogMenuId='+id);
	}
	</script>

</@base> 
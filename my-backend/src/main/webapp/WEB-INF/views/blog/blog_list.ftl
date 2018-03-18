<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="博客列表" openIndex=3 activeIndex=0>
	
    <!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="标题" name="title" />
		<@searchSelect id="menuId" datas=menuList key="id" text="name" value="${blogMenuId!''}" defaultValue="--选择分类--" title="分类" />
		<@searchInput title="用户" name="username" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="title" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>标题</th>
			<th field="username" my-attrs='{textFun:"viewUser",args:"userId",clazz:"table-a"}'>用户</th>
			<th field="menuName" my-attrs='{textFun:"getByMenu",args:"menuId",clazz:"table-a"}'>分类</th>
			<th field="click">点击</th>
			<th field="createTime">创建时间</th>
			<th field="updateTime">更新时间</th>
			<!-- <th field="op" field-role="0" width="110"></th> -->
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
			
	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/blog/getList',
			url_edit : '/backend/blog/edit',
			url_remove : '/backend/blog/doDelete',
			parm:{
				pageNo : 1,
				pageSize : 10,
				menuId:'${blogMenuId!''}'
			},
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function viewDetail(id){
		window.open('/backend/blog/getDetail?id='+id);
	}
	
	function viewUser(id){
		window.open("/backend/profile/index?userId="+id);
	}
	
	function getByMenu(menuId){
		self.location='/backend/blog/list?blogMenuId='+menuId;
	}
1	</script>

</@base> 
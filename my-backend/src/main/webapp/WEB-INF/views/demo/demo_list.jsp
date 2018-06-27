<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div id="formArea"></div>
    			
   			<!-- table区 -->
   			<div  id="datatable">
				<table>
					<thead>
						<tr>
							<th width="60" field="index_no">编号</th>
							<th field="title" my-attrs='{textFun:"viewDetail",args:"url",clazz:"table-a"}'>标题</th>
							<th field="name">所属分类</th>
							<th field="downloadTimes">下载次数</th>
							<th field="createTimeStr">创建时间</th>
							<th field="updateTimeStr">更新时间</th>
							<th field="button" width="110" btn_list='[
				                {fnName:"edit",args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn-warning"}
				               ]'>
						</tr>
					</thead>
				</table>
   			</div>
   			<!-- table区 结束 -->
	
	<script type="text/javascript">
		$(function(){
			$('#formArea').formRender({
			    formItemList : [
			                    {type:'text',name:'title', label:'Demo标题' },
			                    {type:'select',name:'menuId', label:'Demo菜单',list:${demoMenuList}, sKey:'id', sValue:'name', value:'${demoMenuId}' }
			                    ]
			});
			
			$('#datatable').datatable({
				url_load : '/backend/demo/getList',
				url_delete : '/backend/demo/doDelete',
				parm : $.common.getFormJson('.form'),
				buttons: [{type:'add',onclick:'edit'}],
				backFn : function(p) {
					
				}
			}); 
			
		});
		
		function viewDetail(url){
			window.open('/api_img'+url)
		}

		function edit(id){
			gotoUrl('/backend/demo/edit?id='+(id||''));
		}
	</script>


<%-- <#include "/base-lib/baseMacro.ftl"> 
<@base base_title="Demo列表" openIndex=4 activeIndex=0>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="Demo标题" name="title" />
		<@searchSelect title="Demo菜单" id="menuId" datas=demoMenuList key="id" text="name" value="${demoMenuId!''}" defaultValue="--请选择--"/>
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="/backend/demo/edit" target="_blank"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="title" my-attrs='{textFun:"viewDetail",args:"url",clazz:"table-a"}'>标题</th>
			<th field="name">所属分类</th>
			<th field="downloadTimes">下载次数</th>
			<th field="createTimeStr">创建时间</th>
			<th field="updateTimeStr">更新时间</th>
			<th field="button" width="110" 
				btn_list='[
                {fnName:"edit",args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/demo/getList',
		parm:{
			pageNo : 1,
			pageSize : 10,
			menuId:'${demoMenuId!''}'
		},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});


</script>

</@base>  --%>
<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="编辑代码笔记" openIndex=2 activeIndex=9999>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">编辑</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id!}">
					<div class="form-group">
						<div class="col-sm-4">
							标题
							<input type="text" class="form-control input" name="item" value="${entity.item!''}">
						</div>
						<div class="col-sm-2">
							排序
							<input type="number" class="form-control input" name="itemOrder" value="${entity.itemOrder!''}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3">
							一级分类
							<@select id="codeMenuId" class="form-control select" datas=codeMenuList key="id" text="name" onchange="refreshFaterId()" value="${codeMenuId!''}" defaultValue="--请选择--" />
						</div>
						<div class="col-sm-3">
							二级分类
							<@select id="fatherId" class="form-control select" datas=codeSubMenuList key="id" text="name" onchange="refreshCodeId()" value="${entity.fatherId!''}" defaultValue="--请选择--" />
						</div>
						<div class="col-sm-3" >
							父级菜单
							<@select id="codeId" class="form-control select" datas=codeIdList key="id" text="item" value="${entity.codeId!''}" defaultValue="--请选择--" /> 
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<textarea id="myEditor" name="content">${entity.content!''}</textarea>
							<script type="text/javascript">
								var ue = UE.getEditor('myEditor',{
							       initialFrameHeight:500,
							       initialFrameWidth:'100%',
							       serverUrl:'/ue/jsp/controller.jsp?imgType=code'
							       /* autoHeightEnabled: true,
							       autoFloatEnabled: true */
								});
							</script>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button class="btn btn-info btn-icon" onclick="doSave()">
								<span>提交</span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 编辑区结束 -->

<script type="text/javascript">
$(function(){
	
});
function refreshFaterId(){
	var parm = {};
	parm.id=$('#codeMenuId').val();
	$.common.refreshSelect('#fatherId','/backend/codeSubMenu/getCodeSubMenuListByFatherId',parm);
	$.common.cleanSelect('#codeId');
}

function refreshCodeId(){
	var parm = {id:$('#fatherId').val()}
	$.common.refreshSelect('#codeId','/backend/code/getCodeListByFatherId', parm);
}

function doSave(){
	var parm = $.common.getFormJson('.form');
	$.common.postRequest(parm, '/backend/code/doSave', function(data){
		if(data.errorNo==200){
			self.location='/backend/code/viewDetail?id='+data.id;
		} else {
			$.common.alert(data.errorInfo);
		}
	});
}
</script>

</@base> 
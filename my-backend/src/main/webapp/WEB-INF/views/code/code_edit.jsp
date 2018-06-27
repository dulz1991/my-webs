<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id}">
					<div class="form-group">
						<div class="col-sm-12">
							标题
							<input type="text" class="form-control input" name="item" value="${entity.item}">
						</div>
						<%-- <div class="col-sm-2">
							排序
							<input type="number" class="form-control input" name="itemOrder" value="${entity.itemOrder}">
						</div> --%>
					</div>
					<div class="form-group">
						<div class="col-sm-4">
							一级分类
							<select name="codeMenuId" id="codeMenuId" class="form-control" onchange="refreshFaterId()">
								<option value="">--</option>
								<c:forEach var="item" items="${codeMenuList }">
									<option value="${item.id }" <c:if test="${codeMenuId==item.id }">selected</c:if> >${item.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-4">
							二级分类
							<select name="fatherId" id="fatherId" class="form-control" onchange="refreshCodeId()">
								<option value="">--</option>
								<c:forEach var="item" items="${codeSubMenuList }">
									<option value="${item.id }" <c:if test="${entity.fatherId==item.id }">selected</c:if> >${item.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-4" >
							父级菜单
							<select name="codeId" id="codeId" class="form-control">
								<option value="">--</option>
								<c:forEach var="item" items="${codeIdList }">
									<option value="${item.id }" <c:if test="${entity.codeId==item.id }">selected</c:if> >${item.item }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<textarea id="myEditor" name="content">${entity.content}</textarea>
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
	if('${entity.item}'!=''){
		$.common.pageTitle('编辑code -- ${entity.item}');	
	} else {
		$.common.pageTitle('新增code');
	}
	
});
function refreshFaterId(){
	var parm = {};
	parm.id=$('#codeMenuId').val();
	$.common.refreshSelect('#fatherId','/backend/codeSubMenu/getCodeSubMenuListByFatherId',parm);
	$.common.cleanSelect('#codeId');
}

function refreshCodeId(){
	var parm = {id:$('#fatherId').val()}
	$.common.refreshSelect('#codeId','/backend/code/getCodeListByFatherId', parm, "id" ,"item" );
}

function doSave(){
	var parm = $.common.getFormJson('.form');
	$.common.post(parm, '/backend/code/doSave', function(data){
		gotoUrl('/backend/code/viewDetail?id='+data.id, 'self');
	});
}
</script>


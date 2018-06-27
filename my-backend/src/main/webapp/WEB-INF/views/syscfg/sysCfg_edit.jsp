<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<form id="editForm" class="form-horizontal col-sm-12">
	<input type="hidden" name="id" value="${entity.id}">

	<div class="form-group">
		<label class="col-sm-12 control-label">参数关键字</label>
		<div class="col-sm-12">
			<input class="form-control" name="key" <c:if test="${isUpdate }">readOnly="readOnly"</c:if>  value="${entity.key}" type="text">
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-12 control-label">参数值</label>
		<div class="col-sm-12">
			<textarea rows="6" cols="64" class="form-control" name="value">${entity.value}</textarea>
		</div>
	</div>
	
</form>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
		
<form id="editForm" class="form-horizontal col-sm-12">
	<input type="hidden" name="id" value="${entity.id}">

	<div class="form-group">
		<div class="col-sm-12">
			分类名称<span class="text-red">*</span>
			<input class="input" name="name" value="${entity.name}" type="text">
		</div>
	</div>
</form>

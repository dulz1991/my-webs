<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
		
<form id="editForm" class="form-horizontal col-sm-12">
	<input type="hidden" name="id" value="${entity.id}">

	<div class="form-group">
		<div class="col-sm-12">
			菜单名称<span class="text-red">*</span>
			<input class="input" name="menuName" value="${entity.menuName}" type="text">
		</div>
	</div>
		
	<div class="form-group">
		<div class="col-sm-12">
			图标
			<input class="input" name="icon" value="${entity.icon}" type="text">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-12">
			访问地址
			<input class="input" name="url" value="${entity.url}" type="text">
		</div>
	</div>
		
	<div class="form-group">
		<div class="col-sm-12">
			父级菜单
			<select name="parentId" class="input">
				<option value="-1">--</option>
				<c:forEach var="item" items="${list }">
					<option value="${item.id }" <c:if test="${item.id==entity.parentId }">selected</c:if> >${item.menuName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
		
</form>

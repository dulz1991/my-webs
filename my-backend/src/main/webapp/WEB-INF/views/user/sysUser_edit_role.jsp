<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
		
<form id="editRoleForm" class="form-horizontal col-sm-12">
	<input type="hidden" name="userId" value="${userId}">
	
	<div class="form-group">
		<label class="col-sm-12 control-label"><span class="text-red">*</span>选择权限</label>
		<div class="col-sm-12">
			<c:forEach var="item" items="${roleList }">
				<label class="checkbox" style="display:inline">
                   <input type="checkbox" class="colored-success"   name="roleCode" value="${item.roleCode }"
                   <c:if test="${item.hasRoleCode }">checked</c:if>    
                   >
                   <span class="text">${item.roleName }</span>
               </label>
			</c:forEach>
		</div>
	</div>
		
</form>

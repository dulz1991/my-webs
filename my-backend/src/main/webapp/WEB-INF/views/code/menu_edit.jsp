<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<form class="form-horizontal form" id="editMenuForm" action="javascript:void(0);" style="padding:10px">
			<input type="hidden" name="id" value="${entity.id}">
			<div class="form-group">
				<div class="col-sm-11">
					code一级菜单名称
					<input type="text" class="form-control input" name="name" value="${entity.name}">
				</div>
				
				<div class="col-sm-11">
					排序号
					<input type="text" class="form-control input" name="orderBy" value="${entity.orderBy}">
				</div>
			</div>
		</form>

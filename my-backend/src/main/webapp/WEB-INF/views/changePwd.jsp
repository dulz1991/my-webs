<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		
		
<form id="changePwdForm" class="form-horizontal col-sm-12">
	<div class="form-group">
		<label class="col-sm-12 control-label"><span class="text-red">*</span>新密码</label>
		<div class="col-sm-12">
			<input class="input" name="newPwd" value="" type="password">
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-12 control-label"><span class="text-red">*</span>确认密码</label>
		<div class="col-sm-12">
			<input class="input" name="rePwd" value="" type="password">
		</div>
	</div>
	
</form>

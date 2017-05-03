/**
 * From 2016.04.12
 *
 * e.g. $.commonUtil.isNotBlank();
 */
jQuery.commonUtil = {
	
	/* 内容为空 返回"" */
	convertBlank: function(value) {
		if (value == undefined) {
			return "";
		} else if (value == null) {
			return "";
		}
		return value;
	},

	/* 判断是否为空 */ 
	isNotBlank: function(value) {
		if (value == undefined) {
			return false;
		} else if (value == null) {
			return false;
		} else if (value == '') {
			return false;
		} 
		return true;
	},
	
	//显示提示
	showTip: function(tipText) {
		$("#modal-tip .modal-body").html(tipText);
		jQuery("#modal-tip").modal("show", {backdrop: "fade"});
	}
	
}
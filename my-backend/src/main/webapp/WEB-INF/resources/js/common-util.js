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
	showTip: function(text) {
		setTimeout(function() {			
			var opts = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-right toast-default",
				"toastClass": "black",
				"onclick": null,
				"showDuration": "100",
				"hideDuration": "1000",
				"timeOut": "2000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
			toastr.info(text, "提示/Tip", opts);
		}, 400);
	},
	
	/**
	 * 删除
	 */
	deleteById: function(_id, _deleteUrl, _isRefrush, _jumpUrl) {
		$.ajax({
			type: "GET",
			url: _deleteUrl,
			dataType : "json",
			data: {
				idList : _id
			},
			success: function(data) {
				if (data.errorNo != 0) {
					alert(data.errorInfo);
				} else {
					if(_isRefrush){
						self.location.href=_jumpUrl;
					} else {
						alert(data.errorInfo);
					}
				}
			}
		});
	},
	
	updateObj: function(_obj, _updateUrl, _isRefrush, _jumpUrl) {
		$.ajax({
			type: "POST",
			url: _updateUrl,
			dataType : "json",
			data: _obj,
			success: function(data) {
				if (data.errorNo != 0) {
					alert(data.errorInfo);
				} else {
					if(_isRefrush){
						self.location.href=_jumpUrl;
					} else {
						alert(data.errorInfo);
					}
				}
			}
		});
	},
	
	/* 含文件的表单提交 */
	ajaxFileSubmit : function(elem ,_submitUrl,_isRefrush, _jumpUrl) {
		$(elem).ajaxSubmit({  
            type:'post',  
            cache: false,  
            url: _submitUrl, 
            dataType : 'json', //返回值类型 一般设置为json  
            success : function(data, status) {  
	        	if(data.errorNo==0){
	        		if(_isRefrush){
	        			self.location= _jumpUrl;
	        		}
				} else {
					alert(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	alert(data.errorInfo); 
	        }   
        });
	},
	
	/* 联动刷新select */
	refreshSelect : function(_currentElem, _targetElem, _getUrl){
		var parentId = $(_currentElem).val();
		$.ajax({
			type: "GET",
			url: _getUrl,
			dataType : "json",
			data: {
				id : parentId 
			},
			success: function(data) {
				if (data.errorNo == 200) {
					if(data.list==undefined){
						return;
					}
					var html = '<option value="-1">--请选择--</option>';
					var len = data.list.length;
					for (var i = 0; i < len; i++) {
						var item = data.list[i];
						html += '<option value="'+item.key+'">'+item.value+'</option>';
					}
					$(_targetElem).html(html);
				} 
			}
		});
	}
	
}
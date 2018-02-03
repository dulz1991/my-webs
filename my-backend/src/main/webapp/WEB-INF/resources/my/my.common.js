jQuery.common = {
		
	/**
	 * 封装jq的ajax get请求
	 * @param _data 参数
	 * @param _url 请求地址
	 * @param _callback 回调
	 */
	getRequest: function(_data, _url, _callback) {
		$.common.ajaxRequest("GET", _url, "json", _data, false, true, function(data){
			_callback(data);
		});
	},
	
	/**
	 * 封装jq的ajax post请求
	 * @param _data 参数
	 * @param _url 请求地址
	 * @param _callback 回调
	 */
	postRequest: function(_data, _url, _callback) {
		$.common.ajaxRequest("POST", _url, "json", _data, false, true, function(data){
			_callback(data);
		});
	},
	
	/**
	 * 公用ajax方法
	 * @param _type post或get
	 * @param _url 请求地址
	 * @param _dataType 数据类型 一般json
	 * @param _data 请求的参数
	 * @param _cache 是否缓存
	 * @param _async true：异步，false：同步
	 * @param _callback 回调
	 */
	ajaxRequest: function(_type, _url, _dataType, _data, _cache, _async, _callback){
		$.ajax({
			type: _type,
			url: _url,
			dataType : _dataType,
			data: _data,
			cache : _cache,
			async: _async,
			success: function(data) {
				if(data.errorNo==3){
					self.location="/login/index";
					return;
				} else if(data.errorNo==4){
					self.location="/login/noAuth";
					return;
				} 
				_callback(data);
			},
			error: function(data){
				var res = {};
				res.errorNo=-1;
				res.errorInfo="系统异常或升级中";
				_callback(res);
			}
		});
	},
	
	doLogout: function(){
		$.common.postRequest({}, '/login/logout', function(data){
			if(data.errorNo==0){
				self.location="/login/index";
			} else {
				$.common.tip("退出失败");
			}
		})
	},
		
	/**
	 * 删除
	 * @param _parm
	 * @param _url
	 */
	confirmDelete: function(_parm, _url) {
		swal({
			title: '确定要删除么?',
			text: "",
			type: 'warning',   //感叹号图标
			showCancelButton: true,   //显示取消按钮
			confirmButtonColor: '#3085d6', //俩个按钮的颜色
			cancelButtonColor: '#d33',
			confirmButtonText: '确定删除', //俩个按钮的文本
			cancelButtonText: '取消',
			confirmButtonClass: 'btn btn-success',  //俩个按钮的类样式
			cancelButtonClass: 'btn btn-danger',
		}).then(function() {
			$.common.postRequest(_parm, _url, function(data){
				if(data.errorNo==0){
					$.fn.reload();
					swal('删除成功', '记录已被删除', 'success');
				} else {
					swal('删除失败', data.errorInfo, 'error');
				}
			})
		}, function(dismiss) {
			/*if (dismiss === 'cancel') {
				swal('Cancelled', 'Your imaginary file is safe :)', 'error')
			}*/
		})
	},
	
	/**
	 * 含文件的表单提交
	 * @param _elem 表单标签 如:#form .form
	 * @param _submitUrl 提交的接口地址
	 * @param _callback 回调
	 */
	ajaxFileSubmit : function(_elem, _submitUrl, _callback) {
		$(_elem).ajaxSubmit({  
            type:'post',  
            cache: false,  
            url: _submitUrl, 
            dataType : 'json', //返回值类型 一般设置为json  
            success: function(data) {
            	if(data.errorNo==3){
					self.location="/login/index";
					return;
				}
				_callback(data);
			},
			error: function(){
				var res = {};
				res.errorNo=-1;
				res.errorInfo="系统异常或升级中";
				_callback(res);
			}  
        });
	},
	
	/**
	 * 联动刷新select
	 * @param _currentElem
	 * @param _targetElem
	 * @param _getUrl
	 * @param _parm
	 */
	refreshSelect : function( _targetElem, _getUrl, _parm){
		$.common.getRequest(_parm, _getUrl, function(data){
			if (data.errorNo == 200) {
				if(data.list==undefined){
					return;
				}
				var html = '<option value="">--请选择--</option>';
				var len = data.list.length;
				for (var i = 0; i < len; i++) {
					var item = data.list[i];
					html += '<option value="'+item.key+'">'+item.value+'</option>';
				}
				$(_targetElem).html(html);
			}
		});
	},
	
	/**
	 * 根据select的text默认选中
	 * @param _elem
	 * @param _value
	 */
	optionTextSelect : function(_elem, _value){
		$(_elem).each(function (){
		    if($(this).text()==_value){
		        $(this).attr('selected',true);
		    }
		});
	},
	
	/**
	 * 清除select
	 */
	cleanSelect : function(_targetElem){
		var html = '<option value="">--请选择--</option>';
		$(_targetElem).html(html);
	},
	
	/**
	 * 根据select的value默认选中
	 * @param _elem
	 * @param _value
	 */
	optionValueSelect : function(_elem, _value) {
		$(_elem).each(function (){
		    if($(this).val()==_value){
		        $(this).attr('selected',true);
		    }
		});
	},
	
	/**
	 * 获取表单数据 未编码
	 * @param elem
	 * @returns {___anonymous3972_3973}
	 */
	getFormJson : function(elem) {
        var o = {};
        var a = $(elem).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    },
    /**
     * 获取表单数据 编码
     * @param elem
     * @returns {___anonymous4537_4538}
     */
    getFormJsonEncode: function(elem) {
        var o = {};
        var a = $(elem).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = encodeURIComponent(this.value) || '';
            }
        });
        /*o = encodeURI(encodeURI(o));*/
        return o;
    },
    
    /**
     * 获取地址栏参数
     * @param name url中的key
     * @returns
     */
	getQueryString: function(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)return  unescape(r[2]); return null;
	},
	
	/**
	 * json集合间拼接
	 * @param source 源json
	 * @param destination 目标json
	 * @returns
	 */
	appendJson: function(source, destination){
		for(var property in source){
			destination.push(source[property]);
		}
		return destination;
	},
	
	/**
	 * 分页栏
	 * @param elem 标签元素 比如：#divpage
	 * @param page 分页的json内容
	 * @param turnPageFunc 点击分页触发的js方法
	 */
	refreshPage: function(elem, page, turnPageFunc){
		$(elem).createPage({
			totalNum: page.totalRecords,//总数据量
			pageNum: page.pageCount,//总页码
			current: page.currentPage,//当前页
			shownum: page.pageSize,//每页显示个数
			turnPageFunc: turnPageFunc,
//			activepage: "",//当前页选中样式
//			activepaf: "",//下一页选中样式
			backfun: function(e) {
//				console.log(e);//回调
			}
		});	
	},
	/**
	 * 清空分页
	 * @param elem
	 * @param turnPageFunc
	 */
	cleanPage: function(elem,turnPageFunc){
		$(elem).createPage({
			totalNum: 0,//总数据量
			pageNum: 0,//总页码
			current: 0,//当前页
			shownum: 10,//每页显示个数
			turnPageFunc: turnPageFunc,
			backfun: function(e) {
				//console.log(e);//回调
			}
		});	
	},

	/**
	 * 默认2s中的toast
	 * @param text
	 */
	tip: function(text){
		$.common.tip(text, 2000);
		/*showMessage(text,2000,true,'bounceInUp-hastrans','bounceOutDown-hastrans');*/
	},
	/**
	 * 带时间的toast
	 * text 文案
	 * time 时间
	 */
	tip: function(text, time){
		showMessage(text,time,false,'bounceInUp-hastrans','bounceOutDown-hastrans');
	},
	
	alert: function(text){
		swal('text', '', 'error');
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
	 * 将空字符串转换为""
	 * @param value
	 * @returns ""
	 */
	convertBlank: function(value) {
		if (value == undefined) {
			return "";
		} else if (value == null) {
			return "";
		}
		return value;
	},

	/**
	 * 判断是否为空
	 * @param value
	 * @returns {Boolean}
	 */ 
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
	
	dateconvert: function(value) {
		var date = new Date(value);
		var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours();  
	    var minute = date.getMinutes();  
	    minute = minute < 10 ? ('0' + minute) : minute;  
	    return y + '-' + m + '-' + d+' '+h+':'+minute;    
	},
	
	openQQ: function(){
		window.open("http://wpa.qq.com/msgrd?v=3&uin=3566877072&site=qq&menu=yes");
	},
	
	reloadTable: function(){
		$.fn.reload();
	},
	
	//删除
	deleteById:function(id, url){
		swal({
			title: '删除',
		  	text: "确定删除么?",
		  	type: 'warning',   //感叹号图标
		  	showCancelButton: true,   //显示取消按钮
		  	confirmButtonColor: '#d33', //俩个按钮的颜色
		  	confirmButtonText: '确定删除', //俩个按钮的文本
		  	cancelButtonText: '取消',
		}).then(function() {    //大部分，then是通用的回调函数
			var parm = {};
			parm.id=id;
			$.common.postRequest(parm, url, function(data){
				if(data.errorNo==200){
					$.common.tip("删除成功");
					$.common.reloadTable();
				} else {
					$.common.tip(data.errorInfo);
				}
			});
		})
	}
	
}

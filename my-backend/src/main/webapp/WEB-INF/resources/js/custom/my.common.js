jQuery.common = {
		
		/**
		 * 封装jq的ajax get请求
		 * @param _data 参数
		 * @param _url 请求地址
		 * @param _callback 回调
		 */
		get: function(_data, _url, _callback) {
			$.common.ajaxRequest("GET", _url, "json", _data, false, true, function(data){
				_callback(data);
			});
		},
		getSync: function(_data, _url, _callback) {
			$.common.ajaxRequest("GET", _url, "json", _data, false, false, function(data){
				_callback(data);
			});
		},
		
		/**
		 * 封装jq的ajax post请求
		 * @param _data 参数
		 * @param _url 请求地址
		 * @param _callback 回调
		 */
		post: function(_data, _url, _callback) {
			$.common.ajaxRequest("POST", _url, "json", _data, false, true, function(data){
				_callback(data);
			});
		},
		postSync: function(_data, _url, _callback) {
			$.common.ajaxRequest("POST", _url, "json", _data, false, false, function(data){
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
				if(data.errorNo==3){ //未登录跳转
					self.location="/login/index";
					return;
				} if(data.errorNo==8){ //异步接口自动登录跳转
					window.top.location.href= "/purview/toIndex";
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
            	if(data.errorNo==0){
            		_callback(data);	
            	} else {
            		$.pop.error(data.errorInfo);	
            	}
			},
			error: function(res){
				var res = {};
				res.errorNo=-1;
				res.errorInfo="系统异常";
				_callback(res);
			}  
        });
	},
	ajaxFileSubmitSync : function(_elem, _submitUrl, _callback) {
		$(_elem).ajaxSubmit({  
            type:'post',  
            cache: false,  
            async: false,
            url: _submitUrl, 
            dataType : 'json', //返回值类型 一般设置为json  
            success: function(data) {
            	if(data.errorNo==3){
					self.location="/login/index";
					return;
				}
            	if(data.errorNo==0){
            		_callback(data);	
            	} else {
            		$.pop.error(data.errorInfo);	
            	}
			},
			error: function(res){
				var res = {};
				res.errorNo=-1;
				res.errorInfo="系统异常";
				_callback(res);
			}  
        });
	},
	
	/* 联动刷新select */
	refreshSelect : function(_targetElem, _getUrl, _parm, key, value){
		key = key || 'key';
		value = value || 'value';
		$.common.getSync(_parm, _getUrl, function(data){
			var html = '<option value="">--</option>';
			if(data.errorNo==0){
				if(data.selectList==undefined){
					$(_targetElem).html(html);
					$.pop.error('下拉框数据加载失败');
					return;
				}
				
				var len = data.selectList.length;
				for (var i = 0; i < len; i++) {
					html += '<option value="'+data.selectList[i][key]+'">'+data.selectList[i][value]+'</option>';
				}
				$(_targetElem).html(html);	
			} else {
				$(_targetElem).html(html);
			}
		});
	},
	cleanSelect : function(_targetElem){
		$(_targetElem).html('<option value="">--</option>');
	},
	
	optionTextSelect : function(_elem, _value){
		$(_elem).each(function (){
		    if($(this).text()==_value){
		        $(this).attr('selected',true);
		    }
		});
	},

	optionValueSelect : function(_elem, _value) {
		$(_elem).find('option').each(function (){
		    if($(this).val()==_value){
		        $(this).attr('selected',true);
		    }
		});
	},
	
	/**
	 * 表单序列化 不带编码
	 * @param elem 表单元素，如 '.form'
	 * @returns {___anonymous3126_3127}
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
     * 表单序列化 带编码
     * @param elem 表单元素，如 '.form'
     * @returns {___anonymous3695_3696}
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
			totalNum: page.totalResult,//总数量
			pageNum: page.totalPage,//总页码
			current: page.currentPage,//当前页
			shownum: page.showCount,//每页显示个数
			subtractionTime: page.subtractionTime,//查询消耗时间
			turnPageFunc: turnPageFunc,
//			activepage: "",//当前页选中样式
//			activepaf: "",//下一页选中样式
			backfun: function(e) {
				
			}
		});	
	},
	
	/**
	 * 清空分页
	 * @param elem
	 * @param turnPageFunc
	 */
	cleanPage: function(args){
		$(args.pageDiv).createPage({
			totalNum: 0,//总数据量
			pageNum: 0,//总页码
			current: args.parm.pageNo,//当前页
			shownum: 10,//每页显示个数
			turnPageFunc: args.nextPage,
			inputPageNo: args.inputPageNo,
			inputPageSize: args.inputPageSize,
			toTop: args.toTop,
			backfun: function(e) {
				//console.log(e);//回调
			}
		});	
	},

	/**
	 * 判断是否为空
	 * @param value
	 * @returns
	 */
	isBlank: function(value) {
		return !$.common.isNotBlank(value);
	},
	/**
	 * 判断是否为非空
	 * @param value
	 * @returns {Boolean}
	 */
	isNotBlank: function(value) {
		if (value === undefined) {
			return false;
		} else if (value === null) {
			return false;
		} else if (value === '') {
			return false;
		} 
		return true;
	},
	
	/**
	 * 空转换
	 * @param value 
	 * @param defaultValue 默认值 如果为空显示该默认值 否则显示value值
	 * @returns
	 */
	convertBlank: function(value, defaultValue) {
		if($.common.isBlank(defaultValue)){
			defaultValue = "";
		}
		if($.common.isBlank(value)){
			return defaultValue;
		} else {
			return value;	
		}
		
	},
	
	/**
	 * 日期转换
	 * @param value
	 * @returns {String}
	 */
	dateConvert: function(value) {
		if($.common.isBlank(value)){
			return "";
		}
		var date = new Date(value);
		var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours();  
	    var minute = date.getMinutes();  
	    minute = minute < 10 ? ('0' + minute) : minute;
	    var sec = date.getSeconds();  
	    sec = sec < 10 ? ('0' + sec) : sec;
	    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+sec;    
	},
	getNowFormatDate: function () {
        var date = new Date();
        var seperator1 = ".";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    },
	
	/**
	 * 设置cookie
	 * @param key
	 * @param value
	 */
	setCookie: function(key, value){
		document.cookie=""+key+"="+value+";path=/";
	},
	/**
	 * 读取cookie
	 * @param key
	 * @returns
	 */
	getCookie: function(key){
		var arr, reg=new RegExp("(^| )"+key+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	},
	/**
	 * 删除cookie
	 * @param key
	 */
	delCookie: function(key){
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=$.common.getCookie(key);
		if(cval!=null)
			document.cookie= key + "="+cval+";expires="+exp.toGMTString()+";path=/";
	},
	
	/**
	 * table列表 判断是否有选择复选框
	 * @returns {Boolean}
	 */
	isSelected: function(){
		if ($("input[type='checkbox'][name$='Id']:checked").length == 0) {
	        return false;
	    } else {
	    	return true;
	    }	
	}, 
	selectedIds: function(){
		if(!$.common.isSelected()){
			return ''; 
		}
		var ids = '';
        var checkItems = $("input[type='checkbox'][name$='Id']:checked");
        for(var i=0; i<checkItems.length; i++){
               ids += checkItems[i].value+',';
       	}
        return ids;
	},
	selectedItems: function(name){
		var ids = '';
        var checkItems = $("input[type='checkbox'][name='"+name+"']:checked");
        for(var i=0; i<checkItems.length; i++){
               ids += checkItems[i].value+',';
       	}
        return ids;
	},
	
	/**
	 * 遮罩层 
	 * @param elem 如：'#table'
	 */
	tableCover: function(elem){
		$("<div id='table-mask'></div>").css({ 
			position:'absolute', 
			top:0, 
			left:0, 
			backgroundColor:"#808080",
			color:'#fff',
			opacity:0.4,
			zIndex:300 
		}).css('vertical-align','middle').height($(document).height()).width($(document).width()).appendTo(elem); 
	},
	
	
	/**
	 * 判断是否IE
	 * @returns {Boolean}
	 */
	isIE: function(){
		if(window.navigator.userAgent.indexOf("MSIE")>=1){
			return true;
		} else {
			return false;
		}
	},
	
	trim: function(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	
	/*收缩左侧栏*/
	sidebarSmall: function(){
		$('#sidebar-collapse').trigger('click');
	},
	
	/*设置tab标签的title和页面的title*/
	pageTitle: function(title){
		document.title=title;
		$('.header-title h1').text(title);
	},
	
	openImg : function(imgUrl){
		layer.photos({
            shade: [0.3, '#000'],
            success:function(){
                $('.layui-layer-imgsee').css('display','none');
            },
            photos: {
                "title": "", //相册标题
                "data": [ //相册包含的图片，数组格式
                    {"src": imgUrl}
                ]
            },
            anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
	}
	
}

jQuery.pop = {
		
	/**
	 * 各种弹框提示
	 */
	error : function(text){
		layer.alert(text, {
			skin: 'layui-layer-red', //样式类名
			closeBtn: 0,
			title: '错误'
		});
	},
	warning : function(text){
		$.pop.notify(text, 'warning');
	},
	success : function(text){
		$.pop.notify(text, 'success');
	},
	info : function(text){
		$.pop.notify(text, 'info');
	},
	notify: function(text, type){
		$.pop.notify(text, type, '3000');
	},
	notify: function(text, type, time){
		Notify(text, 'top-right', time, type, 'fa-bolt', true);
	},
	
	/**
	 * parm里的数据
	 * title 弹出层标题
	 * html 弹框内容
	 * w 宽度
	 * h 高度
	 * 查看详情弹出框
	 */
	view : function(parm){
		var title = $.common.convertBlank(parm.title, ' ');
		var html = $.common.convertBlank(parm.html, '<div id="dialogView">数据加载中...</div>');
		var width =  $.common.convertBlank(parm.width, '400px');
		var height =  $.common.convertBlank(parm.height, '400px');
		layer.open({
			title: title,
			type: 1,
			skin: 'layui-layer-rim', //加上边框
			maxmin: true,
			area: [width, height],
			content: html
		});
		if($.common.isNotBlank(parm.loadUrl)){
			$('#dialogView').load(parm.loadUrl);	
		} else {
			$('#dialogView').html(html);
		}
	},
	
	/**
	 * @param text 提示文字
	 * @param url 接口地址
	 * @param callback 回调
	 * @returns {Boolean}
	 */
	confirm: function(text, url, callback){
		if($.common.isBlank(url)){
			$.pop.error('请求地址不能为空');
			return false;
		}
		layer.confirm(text, {
			btn: ['确定','取消'],
			title: '确认提示',
		}, function(){
			//点击确定 
			$('.layui-layer-btn0').hide();
			$.common.post({}, url, function(res){
				if(res.errorNo!=0){
					$.pop.error(res.errorInfo);
					$('.layui-layer-btn0').show();
					return false;
				} else {
					callback(res);
					layer.closeAll();
				}
			})
		}, function(){
			
		});
	},
	
	/**
	 * 表单校验对话框 需要带上校验的方法
	 * @param parm 弹框参数json对象
	 * @param verifyFunction 回调方法
	 * @param callback 回调
	 */
	dialog : function(parm, verifyFunction, callback){
		var title = $.common.convertBlank(parm.title, ' ');
		var html = $.common.convertBlank(parm.html, '<div id="dialogId">数据加载中...</div>');
		var width =  $.common.convertBlank(parm.width, '400px');
		var height =  $.common.convertBlank(parm.height, '400px');
		
		layer.open({
			type: 1,
			title: title,
			shade: 0,
			area: [width, height],
			maxmin: true,
			content: html,
			btn: ['确定','处理中...', '取消'],
			yes: function(index,layero){
				$('.layui-layer-btn0').hide();
				$('.layui-layer-btn1').show();
				if($.common.isBlank(verifyFunction)){
					callback();
					layer.closeAll();	
				} else {
					var result = verifyFunction();
                    if(result!='') {
                    	$('.layui-layer-btn0').show();
        				$('.layui-layer-btn1').hide();
                    	$.pop.error(result);
                    	return false;
                    } else {
                    	callback();
                    	layer.closeAll();
                    	return true;
                    }
				}
			},
			btn2: function(){
				return false;
			},
			btn3: function(){
				layer.closeAll();
			}
		});
		$('.layui-layer-btn1').hide();
		if($.common.isNotBlank(parm.loadUrl)){
			$('#dialogId').load(parm.loadUrl);	
		}
    },


    /**
     * 查看图片
     * @param obj ing标签 this
     */
    viewImg: function(imgArr){
    	for(var i=0;i<imgArr.length;i++){
            if(document.getElementById(imgArr[i])!=null){
                new Viewer(document.getElementById(imgArr[i]), {
                    url: 'data-original',
                    navbar:false
                });
                document.getElementById(imgArr[i]).onclick=function(){
                    $(".viewer-container").draggable();
                };
            }
        }
    }
	
}

/**
 * From 2016.04.12
 */
/* plugin definition */    
(function($) {
	var _obj; //页面选择的对象
	var _args; //参数
	
	$.fn.datatable = function(options) {
		var args = $.extend({
			url_load : '', //加载地址
			parm : {
				pageNo : 1,
				pageSize : 10
			},
			cache : false,
			type : 'POST',
			dataType : 'json',
			pageDiv : '#pageDiv', //分页栏id
			changePageSize : true, //是否允许修改每页数量
			backFn : function() {}
		},options);
		_obj = this;
		_args = args;
		action.load(this, _args);
	}
	
	/* 重新加载 */
	$.fn.reload = function() {
		action.load(_obj, _args);
	}
	
	/* 翻页 */
	$.fn.nextPage = function(_pageNo) {
		if(_pageNo==-1){
			_pageNo = $('.zxfinputPageNo').val();
		} 
		_args.parm.pageNo = _pageNo;
		_args.parm.pageSize = $('.zxfinputPageSize').val();
		if(_args.parm.pageSize>100){
			$('.zxfinputPageSize').val('10');
			_args.parm.pageSize = 10;
		}
		action.load(_obj, _args);
	}
	
	/* 手动搜索 -- 手动拼接参数，再调用此方法 */
	$.fn.manualSearch = function(_parm) {
		_args.parm = _parm;
		action.load(_obj, _args);
	}
	
	/* 自动搜索  
	 * onclick里直接传入表单的class或者id选择器即可
	 *  */
	$.fn.autoSearch = function(_elem) {
		var _parm = $.common.getFormJson(_elem);
		_args.parm = _parm;
		if($.common.isNotBlank($('.zxfinputPageSize').val())){
			_args.parm.pageSize = $('.zxfinputPageSize').val();	
		}
		action.load(_obj, _args);
	}
	
	/**
	 * 渲染按钮
	 */
	$.fn.renderButtons = function(fields, index, item) {
		//add:添加; del:删除; edit:编辑; download:下载; setCustomer：弹框设置供应商关联id
		var opHtml = '';
		var btn_list =  fields.eq(index).attr("btn_list");//获取有哪些按钮，如：btn_list='{"download":"accountId","setCustomer":"accountId"}'
        //是否需要渲染按钮
		if(!$.common.isNotBlank(btn_list)){
			return opHtml;
		}
		
		/*遍历按钮*/
		btn_list = eval('(' + btn_list + ')');
		var btnFnName=''; //方法名 
		var btnName=''; //按钮文字
		var btnArgs=''; //方法参数
		var btnCls = 'am-btn-default'; //样式
		var btnIcon = ''; //图标
		var disable = ''; //是否disable
		var hidden = ''; //是否隐藏
		
		for(var i=0; i<btn_list.length; i++) { 
			btnName = btn_list[i].name; //方法名
			btnFnName = btn_list[i].fnName; //按钮文字
			btnArgs = btn_list[i].args; //方法参数
			btnIcon = $.common.convertBlank(btn_list[i].icon); //图标
			disable = btn_list[i].disable; //是否disable
			hidden = btn_list[i].hidden; //是否隐藏

			//判断是否隐藏按钮
			if($.common.isNotBlank(hidden)){
				if(item[hidden]==true){
					continue;	
				}
			}
			
			//判断是否disable按钮
			var disableHtml = '';
			if($.common.isNotBlank(disable)){
				if(item[disabled]==true){
					disableHtml = 'disabled="disabled"';	
				}
			}
			
			var btnArgsArr = btnArgs.split(','); 
			if($.common.isNotBlank(btn_list[i].cls)){
				btnCls = btn_list[i].cls; //样式
			}
			
			//js方法参数
			var btnArgValue = '';
			var arrLen = btnArgsArr.length; 
			for(var r=0;r<arrLen;r++){
				if(btnArgsArr[r]=='this'){
					btnArgValue += 'this';
				} else {
					if($.common.isNotBlank(item[btnArgsArr[r]])){
						btnArgValue += '\''+item[btnArgsArr[r]]+'\'';	
					} else {
						btnArgValue += '\''+btnArgsArr[r]+'\'';
					}
				}
				if(r<(arrLen-1)){
					btnArgValue += ',';	
				}
			}
			
			opHtml += '<a href="javascript:;" index="'+i+'" '+disableHtml+' onclick="'+btnFnName+'('+btnArgValue+');"  class="am-btn '+btnCls+' am-btn-xs"><i class="'+btnIcon+'"></i> '+btnName+'</a>';
		}  
		
		return opHtml;
	}
	
	//渲染列
	$.fn.renderColumn = function(fields, field, index, item) {
		var text = item[field]; //列数据
		text = $.common.convertBlank(text);//空检查
		
		var attrs =  fields.eq(index).attr("my-attrs");
		if(!$.common.isNotBlank(attrs)){
			return text;
		}
		
		attrs = eval('(' + attrs + ')');
		var span = "<span";
		
		//点击事件
		var textFun = attrs.textFun; //方法名
		var args = attrs.args; //方法参数
		if($.common.isNotBlank(textFun)){
			var argsArr = args.split(',');
			var argValue = '';
			var arrLen = argsArr.length; 
			for(var r=0;r<arrLen;r++){
				if(argsArr[r]=='this'){
					argValue += 'this';
				} else {
					if($.common.isNotBlank(item[argsArr[r]])){
						argValue += '\''+item[argsArr[r]]+'\'';	
					} else {
						argValue += '\''+argsArr[r]+'\'';
					}
				}
				if(r<(arrLen-1)){
					argValue += ',';	
				}
			}
			span += ' onclick="'+textFun+'('+argValue+')" ';
		}
		
		//样式
		var style = attrs.style; //样式
		if($.common.isNotBlank(style)){
			span += " style='" + style +"' ";	
		}
		
		//class
		var clazz = attrs.clazz; //样式
		if($.common.isNotBlank(clazz)){
			span += " class='" + clazz +"' ";	
		}
		
		span += ">"+text+"</span>";
		
		return span;
	}
	
	var action = {
		init: function(obj,args){
			return (function() {
				action.load(obj, args);
			})();
		},
		load: function(obj, args) {
			var fields = $(obj).find('thead tr th');
			var fieldsLen = fields.length;
			/*$(obj).find('tbody').html('<tr><td colspan="'+fieldsLen+'">数据加载中...</td></tr>');
			$.common.tip("数据加载中...",1000);*/
			$.common.postRequest(args.parm, args.url_load, function(data){
				var html = '';
				if(data.list==undefined){
					$(obj).find('tbody').html('<tr><td colspan="'+fieldsLen+'">查无数据</td></tr>');
					$.common.cleanPage(args.pageDiv,'$.fn.nextPage');
					$.common.tip("查无数据",2000);
					return;
				}
				var len = data.list.length;
				if(len==0){
					$(obj).find('tbody').html('<tr><td colspan="'+fieldsLen+'">查无数据</td></tr>');
					$.common.cleanPage(args.pageDiv,'$.fn.nextPage');
					$.common.tip("查无数据",2000);
					return;
				}
				
				for (var i = 0; i < len; i++) {
					var item = data.list[i];
					html += '<tr>';
					$.each(fields, function(index) {
						var field = fields.eq(index).attr('field');//获取当前列的字段
						if (field == 'index_no') { //是否是序列号
							html += '<td>' + (i + 1) + '</td>';
						} else if(field == 'check_box'){ //渲染复选框
							var checkBoxName = "";
							var checkBoxValue = "";
							
							var checkBoxNameKey = fields.eq(index).attr('name');
							if($.common.isNotBlank(checkBoxNameKey)){
								checkBoxName = fields.eq(index).attr('name');
							}
							
							var chackBoxValueKey = fields.eq(index).attr('value');
							if($.common.isNotBlank(chackBoxValueKey)){
								checkBoxValue = item[chackBoxValueKey];
							}
							
							html += '<td><input type="checkbox" value="'+checkBoxValue+'" name="'+checkBoxName+'" /></td>';
						}else if (field == 'button') { //是否是按钮列
							//渲染按钮
							text = $.fn.renderButtons(fields, index, item);
							html += '<td>' + text + '</td>';
						} else { 
							//普通列
							text = $.fn.renderColumn(fields, field, index, item);
							html += '<td class="'+ $.common.convertBlank(fields.eq(index).attr("class")) +'">' + text + '</td>';
						}
					});
					html += '</tr>';
				}
				
				//渲染数据
				$(obj).find('tbody').html(html);
				
				//是否有分页数据
				if(data.page!=null){
					$(args.pageDiv).createPage({
						totalNum: data.page.totalRecords,//总数据量
						pageNum: data.page.pageCount,//总页码
						current: data.page.currentPage,//当前页
						shownum: data.page.pageSize,//每页显示个数
						changePageSize: args.changePageSize,
						turnPageFunc: '$.fn.nextPage',
						backfun: function(e) {
							//console.log(e);//回调
						}
					});
				} else {
					//清空分页栏
					$.common.cleanPage(args.pageDiv,'$.fn.nextPage');
				}
			
			})
		}
	}
	
})(jQuery);
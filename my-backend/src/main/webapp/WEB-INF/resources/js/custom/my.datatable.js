/**
 * From 2016.04.12
 */
/* plugin definition */    
(function($) {
	var _obj; //页面选择的对象
	var _args; //参数
	
	$.fn.datatable = function(options) {
		var args = $.extend({
			url_load : '', //加载数据接口
			url_delete : '', //删除数据接口
			parm : {
				pageNo : 1,
				pageSize : 10
			},
			title: '', //table标题
			textAlign : 'center',  //是否居中 默认居中
			cache : false,
			type : 'POST',
			dataType : 'json',
			pageId : 'pageDiv', //分页栏id
			inputPageNo : 'zxfinputPageNo', //页码输入框
			inputPageSize : 'zxfinputPageSize', //每页数量输入框
			nextPage : '$.fn.nextPage', //翻页的方法  一般在多个table时使用 如：dt1.nextPage, dt2.nextPage 
			toTop: true, //是否回到顶部
			changePageSize : false, //是否允许修改每页数量
			tableClass:'table table-bordered table-hover table-striped',
			tableStyle:'overflow: auto; width: 100%;',
			onExport:'$.fn.onExport', //导出的方法
			buttons : [], //按钮
			showPage: true, //是否显示分页 默认显示
			backFn : function() {} //回调
		},options);
		args.pageId=$(this).attr("id")+args.pageId;
		args.inputPageNo=$(this).attr("id")+args.inputPageNo;
		args.inputPageSize=$(this).attr("id")+args.inputPageSize;
		_obj = this;
		_args = args;
		action.load(this, args);
		this.args = args;
		this.obj = this;
		return this;
	}
	
	/* 重新加载 */
	$.fn.reload = function() {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		action.load(this.obj, this.args);
	}
	
	/* 删除单个记录 */
	$.fn.deleteById = function(id) {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		action.doDelete(this.args.url_delete, id);
	}
	/* 批量删除 */
	$.fn.deleteByIds = function() {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		var ids = $.common.selectedIds();
		action.doDelete(this.args.url_delete, ids);
	}
	
	/* 翻页  */
	$.fn.nextPage = function(_pageNo) {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		var _pageSize = '.'+this.args.inputPageSize;
		if(_pageNo==-1){
			//页码
			if($(_pageSize).val()!=this.args.parm.pageSize){
				//每页查询的数量有改动 回到第一页
				_pageNo = 1;
			} else {
				//每页查询的数量没有改动 说明是页码改动了 调整页码
				_pageNo = $('.'+this.args.inputPageNo).val();
			}
			
			//每页数量 超过100 重置为100
			/*if($(_pageSize).val()>100){
				$(_pageSize).val('100');
			} else if($(_pageSize).val()<=0){
				$(_pageSize).val('10');
			}*/
			
			//分页数量小于等于0 重置为10
			if($(_pageSize).val()<=0){
				$(_pageSize).val('10');
			}
		} 
		this.args.parm.pageNo = _pageNo;
		this.args.parm.pageSize = $(_pageSize).val();
		action.load(this.obj, this.args);
	}
	
	/* 手动搜索 -- 手动拼接参数，再调用此方法 */
	$.fn.manualSearch = function(_parm) {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		this.args.parm = _parm;
		action.load(this.obj, this.args);
	}
	
	/* 自动搜索  
	 * onclick里直接传入表单的class或者id选择器即可
	 *  */
	$.fn.autoSearch = function(_elem) {
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		var _parm = $.common.getFormJson(_elem);
		var _pN=this.args.parm.pageNo;
		var _pS=this.args.parm.pageSize;
		this.args.parm = _parm;
		var _pageSize = '.'+this.args.inputPageSize;
		if($.common.isNotBlank($(_pageSize).val())){
			this.args.parm.pageSize = $(_pageSize).val();	
		} else {
			this.args.parm.pageNo = _pN;
			this.args.parm.pageSize = _pS;
		}
		action.load(this.obj, this.args);
	}
	
	$.fn.onExport = function(){
		if(this.args==undefined){
			this.args = _args;
			this.obj = _obj;
		}
		var tableId = $(this.obj).attr('id');
		$('#'+tableId).table2excel({
			/*exclude: ".noExl",*/
			name: "Excel Document Name",
			filename: new Date().getTime(),
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});
		
		/*$.pop.error('功能开发中...')*/
	}
	
	var action = {
		init: function(obj,args){
			return (function() {
				action.load(obj, args);
			})();
		},
		//加载数据
		load: function(obj, args) {
			$.common.tableCover(obj); //加遮罩
			var fields = $(obj).find('thead tr th');
			var fieldsLen = fields.length;
			$.common.post(args.parm, args.url_load, function(data){
				if(data.errorNo!=0){
					$.pop.info(data.errorInfo);
					args.backFn(data);
					$('#table-mask').remove(); //去掉遮罩
					return;
				}
				var html = '<tbody>';
				var len = data.list.length;
				if(data.list==undefined || len==0){
					html = action.appendTableHtml(html, args, obj);
					$(obj).html(html);
					$(obj).find('tbody').html('<tr><td colspan="'+fieldsLen+'">查无数据</td></tr>');
					args.backFn(data);
					$('#table-mask').remove(); //去掉遮罩
					$.common.cleanPage(args,data.page.pageSize);
					return;
				}
				
				/*tr 是否有obj-attrs属性*/
				var hasObjAttr = false;
				var objAttrs = '';
				if($(obj).find('thead tr').attr('obj-attrs')!=undefined){
					hasObjAttr = true;
					objAttrs = $(obj).find('thead tr').attr('obj-attrs');
				}
				
				for (var i = 0; i < len; i++) {
					var item = data.list[i];
					
					if(hasObjAttr){
						var objAttrResult = action.renderObjAttr(item, objAttrs);
						html += '<tr '+objAttrResult+' >';
					}else {
						html += '<tr>';
					}
					
					$.each(fields, function(index) {
						var field = fields.eq(index).attr('field');//获取当前列的字段
						if (field == 'extend_column') { //是否是展开列
							html += '<td><i class="fa row-details fa-minus-square-o"></i></td>';
						} else if (field == 'index_no') { //是否是序列号
							html += '<td width="45">' + (i + 1) + '</td>';
						} else if(field == 'check_box'){ //复选框
							var checkBoxValue = ""; 
							var chackBoxValueKey = fields.eq(index).attr('value'); //value对于的属性
							if($.common.isNotBlank(chackBoxValueKey)){
								checkBoxValue = item[chackBoxValueKey] || "";
							}
							html += '<td width="30"><label class="checkbox"><input type="checkbox" id="chkAll" value="'+checkBoxValue+'" name="Id" class="colored-success"><span class="text"></span></label></td>';
						} else if(field == 'radio'){ //单选框
							var radioValue = "";
							var radioValueKey = fields.eq(index).attr('value'); 
							if($.common.isNotBlank(radioValueKey)){
								radioValue = item[radioValueKey] || "";
							}
							html += '<td width="30"><label class="radio"><input type="radio" value="'+radioValue+'" name="Id" class="colored-success"><span class="text"></span></label></td>';
						}else if (field == 'button') { //按钮列
							text = action.renderButtons(fields, index, item);
							html += '<td style="text-align:'+args.textAlign+'">' + text + '</td>';
						} else { //普通列 
							text = action.renderColumn(fields, field, index, item);	
							html += '<td  style="text-align:'+args.textAlign+'" class="'+ $.common.convertBlank(fields.eq(index).attr("class")) +'">' + text + '</td>';
						}
					});
					html += '</tr>';
				}
				html += '</tbody>'
				html = action.appendTableHtml(html, args, obj);
				$(obj).html(html);	//渲染数据
				$(obj).attr('style','overflow: auto; width: 100%;');
				
				args.backFn(data); //数据回调
				$('#table-mask').remove(); //去掉遮罩
				InitiateWidgets(); //初始化表格上的收缩功能按钮

				/*全选与取消全选*/
				$('#chkAll').click(function () {
		            chkAll($('#chkAll').is(":checked"));
		        });
				
				if(data.page!=null && args.showPage){
					//刷新分页数据
					$('#'+args.pageId).createPage({
						totalNum: data.page.totalRecords,//总数据量
						pageNum: data.page.pageCount,//总页码
						current: data.page.pageNo,//当前页
						shownum: data.page.pageSize,//每页显示个数
						changePageSize: args.changePageSize,
						inputPageNo: args.inputPageNo,
						inputPageSize: args.inputPageSize,
						toTop: args.toTop,
						turnPageFunc: args.nextPage,
						backfun: function(e) {
							//console.log(e);//回调
						}
					});
				} else {
					//清空分页栏
					$.common.cleanPage(args,data.page.pageSize);
				}
			})
		},
		appendTableHtml: function(html, args, obj){
			$(obj).find('tbody').remove();
			var div = '';
			
			var widgetStyle='';
			/*是否有分页 没有分页 显示滚动条*/
			if(!args.showPage){
				widgetStyle= "style=\'overflow:auto;height:400px;\'";
			}
			
			div += '<div class="widget" '+widgetStyle+'>';
			div += '	<div class="widget-header bordered-bottom bordered-themeprimary">';
			div += '		<span class="widget-caption">'+args.title+'</span>';
			div += '		<div class="widget-buttons">';
			div += '			<a href="#" data-toggle="maximize"><i class="fa fa-expand"></i></a>';
			div += '			<a href="#" data-toggle="collapse"><i class="fa fa-minus"></i></a>';
			div += '			<a href="#" onclick="'+args.onExport+'()"><i class="glyphicon glyphicon-export"></i>导出</a>';
			
			/*按钮格式*/
			/*buttons: [{type:'add'},{type:'edit'},{type:'del',onclick:'xx',cls:'btn-xxx',label:'ss'}]*/
			if(args.buttons.length>0){
				var buttonLen = args.buttons.length;
				var btnItem; //按钮对象
				var btnFun; //按钮方法名
				var btnLabel; //按钮名称
				var btnCls; //按钮样式
				var btnIcon; //按钮前面的图标
				for(var i = 0; i< buttonLen; i++){
					btnItem = args.buttons[i];
					if(btnItem.type=='add'){
						btnLabel = $.common.convertBlank(btnItem.label, '新增');
						btnFun = $.common.convertBlank(btnItem.onclick, 'add');
						btnCls = $.common.convertBlank(btnItem.cls, 'bg-themeprimary');
						btnIcon = $.common.convertBlank(btnItem.icon, 'fa fa-plus');
					} else if(btnItem.type=='edit'){
						btnLabel = $.common.convertBlank(btnItem.label, '修改');
						btnFun = $.common.convertBlank(btnItem.onclick, 'edit');
						btnCls = $.common.convertBlank(btnItem.cls, 'bg-themeprimary');
						btnIcon = $.common.convertBlank(btnItem.icon, 'fa fa-pencil-square-o');
					} else if(btnItem.type=='del'){
						btnLabel = $.common.convertBlank(btnItem.label, '删除');
						btnFun = $.common.convertBlank(btnItem.onclick, '$.fn.deleteByIds');
						btnCls = $.common.convertBlank(btnItem.cls, 'btn-danger');
						btnIcon = $.common.convertBlank(btnItem.icon, 'fa fa-times');
					} else if(btnItem.type=='custom'){ //自定义
						btnLabel = $.common.convertBlank(btnItem.label, '自定义');
						btnFun = $.common.convertBlank(btnItem.onclick, 'custom');
						btnCls = $.common.convertBlank(btnItem.cls, 'bg-themeprimary');
						btnIcon = $.common.convertBlank(btnItem.icon, '');
					} 
					div += '	<a href="#" class="btn '+btnCls+'" id="'+btnItem.id+'" style="color:#fff;padding:0 5px;" onclick="'+btnFun+'()"><i class=" '+btnIcon+'" style="font-size:16px;"></i> '+btnLabel+'</a>';
				}
			}
			
			div += '		</div>';
			div += '	</div>';
			div += '	<div class="widget-body no-padding">';
			div += '		<table style="'+args.tableStyle+'" class="'+args.tableClass+'"  data-tableName="Test Table 1">';
			div += 				$(obj).find('table').html();
			div += 				html;
			div += '		</table>';
			div += '		<div class="row DTTTFooter" id="'+args.pageId+'"></div>';
			div += '	</div>';
			div += '</div>';
			return div;
		},
		/*
		 * <th field="businessName" xym-attrs='{textFun:"viewBusinessDetailById",args:"bussinessId"}'>业务名称</th>
		 * */
		renderColumn: function(fields, field, index, item){
			var text = "";
			/*列类型转换*/
			if($.common.isNotBlank(field)){
				if(field.indexOf('|')>0){
					var textArr = field.split('|');
					text = item[textArr[0]]; 
					if(textArr[1]=='datetime'){
						text = $.common.dateConvert(text);	
					} else if(textArr[1]=='img'){
						text = '<img width="120" src="/api_img'+text+'" />';
					}
				} else {
					text = item[field]; //列数据	
				}
			}
			
			text = $.common.convertBlank(text);//空检查
			var attrs =  fields.eq(index).attr("my-attrs");
			if($.common.isBlank(attrs)){
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
			
			//鼠标放到文字上显示的文案
			var title = attrs.title; //样式
			if($.common.isNotBlank(title)){
				span += " title='" + title +"' ";	
			}
			
			//class
			var clazz = attrs.clazz; //样式
			clazz = $.common.convertBlank(clazz, 'table-a');
			span += " class='" + clazz +"' ";
			
			span += ">"+text+"</span>";
			return span;
		},
		/*
		 * <th style="width: 330px;" field="button" btn_list='[
					    		{fnName:"nodeSet",args:"flowId",name:"节点设置"},
					            {fnName:"$.common.viewFlowImg",args:"flowId,flowName,id",name:"流程节点查看"},
					            {fnName:"toUpdate",args:"flowId,flowName",name:"修改"},
					            {fnName:"$.fn.deleteById",args:"flowId",name:"删除",cls:"btn btn-danger btn-xs"}
					            ]'>操作</th>
		 * */
		renderButtons: function(fields, index, item){
			var btnHtml = '';
			var btn_list =  fields.eq(index).attr("btn_list");//获取有哪些按钮，如：btn_list='{"download":"accountId","setCustomer":"accountId"}'
	        //是否需要渲染按钮
			if($.common.isBlank(btn_list)){
				return btnHtml;
			}
			
			/*遍历按钮*/
			btn_list = eval('(' + btn_list + ')');
			var btnFnName=''; //方法名 
			var btnName=''; //按钮文字
			var btnArgs=''; //方法参数
			var btnCls = 'btn bg-themeprimary btn-xs margin-right-5'; //样式
			var btnIcon = ''; //图标
			var disable = ''; //是否disable
			var hidden = ''; //是否隐藏
			
			for(var i=0; i<btn_list.length; i++) {
				btnName = btn_list[i].name; //方法名
				if($.common.isNotBlank(item[btnName])){
					btnName = item[btnName];
				}
				
				btnFnName = btn_list[i].fnName; //按钮文字
				btnArgs = btn_list[i].args; //方法参数
				btnIcon = $.common.convertBlank(btn_list[i].icon, ''); //图标
				disable = btn_list[i].disable; //是否disable
				hidden = btn_list[i].hidden; //是否隐藏

				//判断是否隐藏按钮
				if($.common.isNotBlank(hidden) && item[hidden]==true){
					continue;	
				}
				
				//样式
				btnCls = $.common.convertBlank(btn_list[i].cls, 'bg-themeprimary');
				
				//js方法参数
				var btnArgsArr = btnArgs.split(',');
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
				
				//判断是否disable按钮
				var disableHtml = '';
				if($.common.isNotBlank(disable) && item[disable]==true){
					disableHtml = ' disabled="disabled" ';	
				} else {
					disableHtml = ' onclick="'+btnFnName+'('+btnArgValue+');" ';	
				}
				
				btnHtml += '<button type="button" index="'+i+'" '+disableHtml+' class="btn btn-xs margin-right-5 '+btnCls+'" style="color:#fff"><i class="'+btnIcon+'"></i> '+btnName+'</button>';	
			}
			return btnHtml;
		},
		/* 获取并展示tr标签上的字段属性 例：
		 * <tr obj-attrs="id,name"></tr> 
		 * */
		renderObjAttr: function(item, objAttrs){
			var objAttrResult = '';
			var arr = objAttrs.split(',');
			for(j = 0; j < arr.length; j++) {
				objAttrResult += arr[j] + '="'+item[arr[j]]+'"';
			} 
			return objAttrResult;
		},
		//删除
		doDelete: function(delete_url, delete_ids) {
			if($.common.isBlank(delete_url)){
				$.pop.info("参数异常：未找到删除地址");
				return false;
			}
			if($.common.isBlank(delete_ids)){
				$.pop.info("参数异常：未选择要删除的数据");
				return false;
			}
			var url = delete_url+"?id="+delete_ids;
			$.pop.confirm('确认删除么？', url, function(data){
				$.pop.success("删除成功!");
				$.fn.reload();
			})
		}
	}
	
})(jQuery);
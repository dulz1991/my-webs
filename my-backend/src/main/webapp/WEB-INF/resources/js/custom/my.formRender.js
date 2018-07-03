/**
 * formRender 2016.04.12
 */
/* plugin definition */    

/*formItemList说明
type : 'text', //输入框类型		hidden:隐藏；text：文本；select：下拉框单选；date：单个日期选择；checkbox：复选框；textScope/dateScope：某个范围内；date:日期,checkbox:复选框
name : '', //输入框name属性
id : '' , //输入框id属性
value : '', //默认值
list : '', //下拉列表
label : '', //输入框前面的搜索条件名称
fromName : '', //scope使用 前一个输入框的name
toName : '', //scope使用  后一个输入框的name
fromId : '', //scope使用 前一个输入框的id
toId : '', //scope使用 后一个输入框的id
fromValue : '',
toValue : '',
sKey : '', //下拉框的可以
sValue ：'', //下拉框的value
emptyOption : false; 是否不显示<option value="">--</option>
*/
/*demo
 * 
 * $('#formArea').formRender({
		    title : '',
		    formItemList : [
							{type:'dateScope',fromName:'a', fromId:'fromDate', toName:'b',toId:'toDate', label:'≤日期选择≤' },
		                    {type:'textScope',fromName:'a', fromId:'aa', toName:'b',toId:'bb', label:'≤会员价格≤' },
		                    {type:'select',name:'select', value:'2', sKey:'id',sValue:'text', label:'下拉框', list:[{id:'1',text:'121'},{id:'2', text:'3213'}] },
		                    {type:'select',name:'select', multiple:true, value:[2], id:'mulityId', sKey:'id',sValue:'text', label:'下拉框', list:[{id:'1',text:'121'},{id:'2', text:'3213'}] },
		                    {type:'hidden',name:'hidden', value:'hide' },
		                    {type:'checkbox',name:'area', label:'区域',list:${cityPdList},sKey:'cityId', sValue:'cityName',value:[]}
		                    {type:'text',name:'a', label:'asuef', value:123 }
		                    ]
		});
		
		 $('#mulityId').multiselect();
 * */
(function($) {
	$.fn.formRender = function(options) {
		var args = $.extend({
			title : '', //表单标题
			formItemList : [], //表单搜索条件列表
			onclick : '$.fn.autoSearch', //点击搜索事件
			formId : 'form', //表单id
			formClass : 'form', //表单class
			
			formGroupStyle : 'margin-right:0px;margin-bottom:5px;width:24%', 
			
			formLabelStyle : 'width:28%;text-align:right',
			
			formGroupStyle : 'margin-right:0px;margin-bottom:5px;width:25%',
			formInputStyle : 'padding-top:0;width:70%',
			
			formGroupScopeStyle : 'margin-right:0px;margin-bottom:5px;width:50%',
			formScopeStyle : 'padding-top:0;width:34%',
			formScopeLabel : 'width:14%;text-align:center',
			
			formCheckboxLabel : 'width:90px;text-align:right',
			formCheckboxStyle : 'width:90px;text-align:left',
			
			formGroupSelectStyle : 'margin-right:0px;margin-bottom:5px;width:25%',
			formSelectStyle : 'padding-top:0;width:70%;display:inline-block;',
			showMoreBtn : true,
			buttons: [],
			showSelectIndex:3,// 默认搜索框展示个数
			backFn : function() {} //回调
		},options);
		this.args = args;
		action.render(this, args);
		return this;
	}
	
	var action = {
		/*渲染表单*/
		render: function(obj, args) {
			var defaultData = $(obj).html();
			var html = '';
			html += '<div class="widget">';
			if(args.title!=''){ //是否有标题
				html += '	<div class="widget-header bordered-left bordered-themeprimary">';
				html += '		<span class="widget-caption">'+args.title+'</span>';
                html += '	</div>';
			}
			
			html += '	<div class="widget-body bordered-left bordered-themeprimary">';
            html += '		<form class="form-inline '+args.formClass+'" id="'+args.formId+'" role="form">';
            
            if($.common.isNotBlank(defaultData)){
            	html += defaultData;
            }
            
            /*表单搜索框搜索条件列表*/
            var formItemList = args.formItemList;
            if(formItemList != undefined && formItemList.length != 0){
            	var len = formItemList.length;
            	var item; 
            	for(var i=0; i<len; i++) { 
            		item = formItemList[i];
            		if($.common.isBlank(item.type)){
            			continue;
            		}
            		switch(item.type) {
                	case 'text': /*文本输入框*/
                		html += action.renderText(item,args);
                		break;
                    case 'hidden': /*隐藏输入框*/
                    	html += action.renderHidden(item,args);
                    	break;
                    case 'select': /*下拉框*/
                    	html += action.renderSelect(item,args);
                        break;
                    case 'textScope': /*文本区间*/
                    	html += action.renderTextScope(item,args);
                        break;
                    case 'dateScope': /*日期区间*/
                    	html += action.renderDateScope(item,args);
                        break;
                    case 'date': /*单个日期选择*/
                    	html += action.renderDate(item,args);
                        break;
                    case 'checkbox': /*复选框*/
                    	html += action.renderCheckbox(item,args);
                        break;
                    case '': 
                        break;
                    default:
                      // TODO
            		}
            	}
            }
            
            html += '			<div class="form-group" style="'+args.formGroupButtonStyle+'">';
            html += '				<button type="button" class="btn bg-themeprimary" style="color:#fff;" onclick="'+args.onclick+'(\'.'+args.formClass+'\')"><i class="fa fa-search"></i>搜索</button>';
            //展开或收缩表单
            if(args.showMoreBtn){
            	html += '				<button type="button" style="display:none" id="formAreaShowMoreText" onclick="showMoreSearchForm(\''+$(obj).attr('id')+'\')" class="btn btn-link">更多>></button>';	
            }
            if(args.buttons.length>0){
            	for(var i=0; i<args.buttons.length; i++){
            		html += '			<button type="button" class="btn btn-default '+args.buttons[i].cls+'" onclick="'+args.buttons[i].onclick+'()">'+args.buttons[i].label+'</button>';		
            	}
            }
            html += '			</div>';
            
            html += '		</form>';
            html += '	</div>';
			
			html += '</div>';
			$(obj).html(html);
			
			//渲染有多选下拉框
			$(".form-group select[multiple]").each(function(index){
				$(this).multiselect({ allSelectedText:'全部选中',nSelectedText:'项选中'});
			});
			
			//搜索框显示的高度调整
			$(obj).find('.form-group').each(function(index){
				if(index<args.showSelectIndex) {
					return;
				} 
				if(index==$(obj).find('.form-group').length-1){
					return;
				}
				$(this).hide();
				$(obj).find('#formAreaShowMoreText').show();
				$(obj).find('#formAreaShowMoreText').attr('status','0');
			});
		},
		/*单个日期选择*/
		renderDate: function(item, args) {
			var div = '';
			div += '<div class="form-group" style="'+args.formGroupStyle+'">';
	        div += '	<label style="'+args.formLabelStyle+'">'+item.label+'</label>';
	        div += '	<input type="text" onfocus="WdatePicker({ dateFmt: \'yyyy.MM.dd\' });" style="'+args.formInputStyle+'" class="form-control" name="'+item.name+'" id="'+$.common.convertBlank(item.id)+'" value="'+$.common.convertBlank(item.value)+'">';
	        div += '</div>';
	        return div;
		},
		/*文本输入框*/		
		renderText: function(item, args) {
			var div = '';
			div += '<div class="form-group" style="'+args.formGroupStyle+'">';
			if(item.ztree){
				div += '	<label class="" style="'+args.formLabelStyle+'"><a href="javascript:;" onclick="showZtreeMenu(this)">'+item.label+' </a></label>';
			} else {
				div += '	<label class="" style="'+args.formLabelStyle+'">'+item.label+' </label>';
			}
			div += '	<input type="text" style="'+args.formInputStyle+'" class="form-control" name="'+item.name+'" id="'+$.common.convertBlank(item.id)+'" value="'+$.common.convertBlank(item.value)+'">';
	        div += '</div>';
	        return div;
		},
		/*隐藏输入框*/
		renderHidden: function(item, args) {
			return '<input type="hidden" class="form-control" name="'+item.name+'" id="'+item.id+'" value="'+$.common.convertBlank(item.value)+'">';
		},
		/*下拉框*/
		renderSelect: function(item, args) {
			var div = '';
			div += '<div class="form-group" style="'+args.formGroupSelectStyle+'" id="'+item.formGroupSelectId+'">';
			div += '	<label class="" style="'+args.formLabelStyle+'">'+item.label+' </label>';
			div += '	<div style="'+args.formSelectStyle+'">';
			
			var list = item.list;
			var sKey = $.common.convertBlank(item.sKey, 'key');
			var sValue = $.common.convertBlank(item.sValue, 'value');
			if($.common.isBlank(item.onchange)){
				item.onchange = '';
			} else {
				item.onchange = "onchange="+item.onchange+"()";
			}
			if(item.multiple){
				item.multiple="multiple";
			} else {
				item.multiple="";
			}
			var id = $.common.convertBlank(item.id, '');
			if(list!=undefined){
				//渲染select
				div += '	<select name="'+item.name+'" '+item.multiple+' class="form-control input" style="width:100%;border-radius:0" '+item.onchange+' id="'+id+'">';
				
				/*是否显示空选项*/
				if(item.emptyOption==undefined){
					item.emptyOption = true;
				}
				if(item.emptyOption){
					div += '		<option value="">--</option>';
				}
				
				//遍历选项
				var selected = '';
				var valueArr = [];
				if(item.multiple){ //多选
					if(item.value instanceof Array && item.value.length>0){
						valueArr = item.value;	
					}
				} else { //单选
					if($.common.isNotBlank(item.value)){
						valueArr.push(item.value); 
					}
				} 
				for(var j=0; j<list.length;j++){
					selected = '';
					for(var k=0;k<valueArr.length; k++){
						if(valueArr[k]==list[j][sKey]){
							selected = 'selected';
							break;
						}
					}
					div += '		<option value="'+list[j][sKey]+'" '+selected+'>'+list[j][sValue]+'</option>';
				}
				div += '	</select>';
			}
	        
			div += '	</div>';
	        div += '</div>';
	        return div;
		},
		/*文本区间*/
		renderTextScope: function(item, args) {
			var div = '';
			div += '<div class="form-group" style="'+args.formGroupScopeStyle+'">';
			div += '	<label style="'+args.formScopeLabel+'"> </label>';
			div += '	<input type="text" style="'+args.formScopeStyle+'" class="form-control" name="'+item.fromName+'" id="'+$.common.convertBlank(item.fromId)+'" value="'+$.common.convertBlank(item.fromValue)+'">';
	        div += '	<label style="'+args.formScopeLabel+'">≤'+item.label+'≤</label>';
	        div += '	<input type="text" style="'+args.formScopeStyle+'" class="form-control" name="'+item.toName+'" id="'+$.common.convertBlank(item.toId)+'" value="'+$.common.convertBlank(item.toValue)+'">';
	        div += '</div>';
	        return div;
		},
		/*日期区间*/
		renderDateScope: function(item, args) {
			var div = '';
			div += '<div class="form-group" style="'+args.formGroupScopeStyle+'">';
			div += '	<label style="'+args.formScopeLabel+'"> </label>';
			div += '	<input type="text" onfocus="WdatePicker({ dateFmt: \'yyyy.MM.dd\' });" style="'+args.formScopeStyle+'" class="form-control" name="'+item.fromName+'" id="'+$.common.convertBlank(item.fromId)+'" value="'+$.common.convertBlank(item.fromValue)+'">';
	        div += '	<label style="'+args.formScopeLabel+'">≤'+item.label+'≤</label>';
	        div += '	<input type="text" onfocus="WdatePicker({ dateFmt: \'yyyy.MM.dd\' });" style="'+args.formScopeStyle+'" class="form-control" name="'+item.toName+'" id="'+$.common.convertBlank(item.toId)+'" value="'+$.common.convertBlank(item.toValue)+'">';
	        div += '</div>';
	        return div;
		},
		/*复选框*/
		renderCheckbox: function(item, args) {
			var div = '';
			div += '<div class="" style="'+args.formGroupStyle+'">';
			div += '	<label class="checkbox" style="'+args.formCheckboxLabel+'">'+item.label+'</label>';
			
			var sKey = $.common.convertBlank(item.sKey, 'key');
			var sValue = $.common.convertBlank(item.sValue, 'value');
			var list = item.list;
			var valueArr = [];
			if($.common.isNotBlank(item.value)){
				valueArr = item.value;
			}
			var checked = '';
			if(list!=undefined){
				for(var i=0; i<list.length;i++){
					div += '	<label class="checkbox" style="'+args.formCheckboxStyle+'">',
					checked = '';
					for(var k=0;k<valueArr.length; k++){
						if(valueArr[k]==list[i][sKey]){
							checked = 'checked=\'checked\'';
							break;
						}
					}
					div += '	<input type="checkbox" name="'+item.name+'" value="'+list[i][sKey]+'" '+checked+'></span>',
					div += '	<span class="text">'+list[i][sValue]+'</span>',
					div += '	</label>';				
				}
				div += '</div>';
				return div;
			}
		}
		
		/*渲染表单 结束*/
	}
	
})(jQuery);
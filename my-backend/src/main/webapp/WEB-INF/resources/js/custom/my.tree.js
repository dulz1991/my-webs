/**
 * 二次封装ztree 2018.06.30
 */

(function($) {
	$.fn.mytree = function(options) {
		var args = $.extend({
			data : [], //数据
			selectedMulti : false, //是否允许多选
			treeList : false, //是否自定义dom
			showIcon: true, //是否显示图标
			
			head : [], //表头
			fields : [], //数据字段
			
			onClick : '', //点击事件
			removeHoverDom : '', //移除元素
			addHoverDom : '', //添加元素
			
			backFn : function() {} //回调
		},options);
		this.args = args;
		action.render(this, args);
		return this;
	}
	
	var action = {
		/*渲染*/
		render: function(_this, args) {
			var setting = action.myTreeSetting(args);
			
			var id = $(_this).attr("id");
			var ztree = $.fn.zTree.init($(_this), setting, args.data || []);
			
			//添加表头
			var li_head = '';
			if( Array.isArray(args.head) && args.head.length>0 ){
				var width = 100/args.head.length;
				li_head += '<li class="head">';
				for(var item in args.head){
					li_head += '<div class="diy" style="text-align:center;width:'+width+'%">'+args.head[item]+'</div>';
				}
				li_head += '</li>';
				
				/*li_head += '<table class="table table-bordered"><thead><tr>';
				for(var item in args.head){
					li_head += '<th>'+args.head[item]+'</th>';
				}
				li_head += '</tr></thead><tbody></tbody></table>';*/
			}
			/*var li_head = ' <li class="head"><a><div class="diy" style="width:55%">名称</div><div class="diy" style="width:10%">状态</div><div class="diy" style="width:10%">排序</div><div class="diy" style="width:10%">级别</div><div class="diy" style="width:15%">操作</div></a></li>';*/
			var rows =_this.find('li');
			if (rows.length > 0) {
			    rows.eq(0).before(li_head)
			} else {
				_this.append(li_head);
				_this.append('<li><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
			}

			_this.treeObj = ztree;
			args.backFn({errorNo:0});
		},
		myTreeSetting :function(args){
			var zSetting = {
				view: {
					selectedMulti: args.selectedMulti,
					addDiyDom: addTreeList,
					showIcon: args.showIcon,
					showLine : false,
					removeHoverDom: args.removeHoverDom, //移除元素
					addHoverDom: args.addHoverDom, //添加元素
					
				},
				data: {
					/*title:"name",*/
					simpleData: {
						enable: true
					}
				},
				callback: {
				 	onClick : args.onClick
				}
			};
			//无自定义dom 移除
			if(!args.treeList){
				delete zSetting.view.treeList;
			}
			//无onclick事件  移除
			if($.common.isBlank(args.onClick)){
				delete zSetting.callback.onClick;
			}
			if($.common.isBlank(args.removeHoverDom)){
				delete zSetting.view.removeHoverDom;
			}
			if($.common.isBlank(args.addHoverDom)){
				delete zSetting.view.addHoverDom;
			}
			
			/*树列表*/
			function addTreeList(treeId, treeNode){
				action.addTreeList(treeId, treeNode,args)
			}
			
			return zSetting;
		},
		addTreeList :function(treeId, treeNode,args) {
		    var liObj = $("#" + treeNode.tId);
		    var aObj = $("#" + treeNode.tId + "_a");
		    var switchObj = $("#" + treeNode.tId + "_switch");
		    
		    var icoObj = $("#" + treeNode.tId + "_ico");
		    var spanObj = $("#" + treeNode.tId + "_span");
		    spanObj.prepend(icoObj);
		    
		    aObj.attr('title', treeNode.name);
		    aObj.append('<div class="diy swich"></div>');
		    
		    var div = $(liObj).find('div').eq(0);
		    
		    div.append(switchObj);
		    div.append(spanObj);
		    var spaceStr = "<span style='height:1px;display: inline-block;'></span>";
		    switchObj.before(spaceStr);
		    
			var tree_li_fields = args.fields;
		    var div = '';
		    if($.common.isNotBlank(tree_li_fields) && Array.isArray(tree_li_fields) && tree_li_fields.length>0){
		    	var tree_li_width = 100/(parseInt(tree_li_fields.length)+1);
		    	for(var item in tree_li_fields){
		    		var field = tree_li_fields[item];
		    		if(field=='buttons'){
		    			div += '<div class="diy" style="width:'+tree_li_width+'%">'+formatHandle(treeNode)+'</div>';
		    		} else {
		    			div += '<div class="diy" style="width:'+tree_li_width+'%">'+treeNode[field]+'</div>';	
		    		}
		    	}
		    }
		    if($.common.isBlank(tree_li_width)){
		    	tree_li_width = "100";
		    }
		    aObj.find('.swich').attr("style",'width:'+tree_li_width+'%')
		    
		    aObj.append(div);
		}
		/*渲染 结束*/
	}
	
})(jQuery);
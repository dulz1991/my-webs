$(function(){
	
	loadMenuList();
	
	$('#search').click(function(){
		var elem = $('.form');
		var menuId = elem.find('select[name="menuId"]').val();
		var fatherId = elem.find('select[name="fatherId"]').val();
		if(menuId==0||fatherId==0){
			return false;
		}
		$.ajax({
			url: "/auth/code/list", 
			data:{
				fatherId: fatherId
			},  
			type: 'get',  
			cache: false,  
			dataType: 'json',
			success: function(data) {
				var html = '';
				for (var i=0; i<data.length; i++) {
					var create = new Date(data.list[i].createTime).format("yyyy-MM-dd hh:mm:ss");
					var update = new Date(data.list[i].updateTime).format("yyyy-MM-dd hh:mm:ss");
					var item = data.list[i].item.replace('<','&lt;').replace('>','&gt;');
					html += '<tr>'
						+ ' <td>'+(i+1)+'</td>'
						+ ' <td><a href="http://my.code/code?fatherId='+data.list[i].fatherId+'" target="_blank">'+item+'</a></td>'
						+ ' <td>'+create+'</td>'
						+ ' <td>'+update+'</td>'
						+ ' <td>'
						+ '  <a href="/auth/code/edit?id='+data.list[i].id+'" target="_blank" class="btn btn-secondary btn-sm btn-icon icon-left"><i class="fa-edit"></i></a>'
						+ '  <a href="#" class="btn btn-info btn-sm btn-icon icon-left"><i class="fa-chevron-up"></i></a>'
						+ '  <a href="#" class="btn btn-info btn-sm btn-icon icon-left"><i class="fa-chevron-down"></i></a>'
						+ '  <a href="#" class="btn btn-danger btn-sm btn-icon icon-left btn-delete"><i class="fa-minus"></i></a>'
						+' </td>'
						+ '</tr>';
				}
				$('#datatable tbody').html(html);
			}
		});
	});
	
	$('#codeSubmit').click(function(){
		var elem = $('.form');
		$.ajax({ 
			url: "/auth/code/save", 
			data:{
				id: elem.find('input[name="id"]').val(),
				fatherId: elem.find('select[name="fatherId"]').val(),
				item: elem.find('input[name="item"]').val(),
				mod:mod,
				codeId:$('select[name="codeId"]').val(),
				content: editor_a.getContent()
			},  
			type: 'post',  
			cache: false,  
			dataType: 'json',
			success: function(data) {
				debugger;
				if(data.errorNo==200){
					window.location.href='http://my.blog/code/guide?codeId='+data.id;
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
			}
		});
	});
	
});

function loadMenuList() {
	$.ajax({ 
		url: "/auth/code/getMenuList", 
		data:{},  
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var len = data.length;
			var html = '<option value="0">-- 选择菜单 --</option>';
			for (var i = 0; i<len; i++) {
				html += '<option value="'+data.list[i].id+'">'+data.list[i].name+'</option>';
			}
			$('.menuSelect').html(html);
			if(menuId!=null && menuId!=''){
				$('select[name="menuId"]').find('option[value="'+menuId+'"]').attr("selected",true);
				loadSubMenuList();
			}
			if(fatherId!=null && fatherId!=''){
				loadNodeMenuList();
			}
		}
	});
}

function loadMenus() {
	loadSubMenuList();
	loadNodeMenuList();
}

function loadSubMenuList() {
	$.ajax({ 
		url: "/auth/code/getSubMenuList", 
		data:{
			fatherId: $('.menuSelect').val()
		},  
		type: 'get',  
		async: false,
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var len = data.length;
			var html = '<option value="0">-- 选择子菜单 --</option>';
			for (var i = 0; i<len; i++) {
				html += '<option value="'+data.list[i].id+'">'+data.list[i].name+'</option>';
			}
			$('.subMenuSelect').html(html);
			if(fatherId!=null && fatherId!=''){
				$('select[name="fatherId"]').find('option[value="'+fatherId+'"]').attr("selected",true);				
			}
		}
	});
}

function loadNodeMenuList() {
	$.ajax({ 
		url: "/auth/code/getNodeMenuList", 
		data:{
			fatherId: $('.subMenuSelect').val()
		},  
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var len = data.length;
			var html = '<option value="0">-- 选择节点菜单 --</option>';
			for (var i = 0; i<len; i++) {
				html += '<option value="'+data.list[i].id+'">'+data.list[i].item+'</option>';
			}
			$('.nodeMenuSelect').html(html);
			if(fatherId!=null && fatherId!=''){
				$('select[name="codeId"]').find('option[value="'+codeId+'"]').attr("selected",true);				
			}
		}
	});
}
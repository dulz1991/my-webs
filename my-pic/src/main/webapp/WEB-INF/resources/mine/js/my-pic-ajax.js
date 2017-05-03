$(document).ready(function(){
	
	/** 提交文章 **/
	$("#picBlogSubmit").click(function(){
		var title = $("input[name=title]").val();
		var content = editor_a.getContent();
		if (title == "") {
			Materialize.toast('请输入标题', 3000, 'rounded');
			return false;	
		} else if (content == "") {
			Materialize.toast('请输入内容', 3000, 'rounded');
			return false;
		}
		$.ajax({
			type: "POST",
			url: "/pic/doSubmit",
			dataType : "json",
			data: {
				title : title,
				content : content
			},
			success: function(data) {
				if (data.errorNo != 200) {
					Materialize.toast(data.errorInfo, 3000, 'rounded');
				} else {
					 window.location.href="/list/detail?id="+data.id;
				}
			}
		});
	});
	
	/** user pic 删除 **/
	var _elem;
	var _id
	$(".delete-btn").click(function(){
		_elem = $(this).parents("tr");
		_id = $(this).attr("id");
		$("a.modal-trigger").trigger("click");
	});
	$(".waves-green").click(function(){
		$.ajax({
			type: "GET",
			url: "/user/doDelete",
			dataType : "json",
			data: {
				id : _id
			},
			success: function(data) {
				if (data.errorNo != 200) {
					Materialize.toast(data.errorInfo, 3000, 'rounded');
				} else {
					Materialize.toast(data.errorInfo, 3000, 'rounded');
					_elem.remove();
				}
			}
		});
	});
	
	/** user pic 查询 **/
	$('.btn-search').click(function(){
		var title = $('input[name="title"]').val();
		window.location.href = "/user/pic?title=" + encodeURIComponent(encodeURIComponent(title));
	});
	
});

/** pic list 分页 **/
function picListPage(_pageNum){
	var html = "";
	var _title = $('input[name="title"]').val();
	$.ajax({
		type: "GET",
		url: "/user/queryPicList",
		dataType : "json",
		data: {
			title : _title,
			pageNum : _pageNum
		},
		success: function(data) {
			var len = data.list.length;
			for (var i=0; i<len; i++) {
				html += '<tr><td>'+(i+1)+'</td>'
					+ '<td><a target="_blank" href="/list/detail?id='+data.list[i].id+'">'+data.list[i].title+'</a></td>'
					+ '<td>'+dateFormater(data.list[i].createTime)+'</td>'
					+ '<td>'+dateFormater(data.list[i].updateTime)+'</td>'
					+ '<td>'+data.list[i].click+'</td>'
					+ '<td><a href="javascript:void(0)" id="'+data.list[i].id+'" class="edit-btn"><i class="fa fa-edit"></i>Edit</a>&nbsp;&nbsp;'
					+ '<a href="javascript:void(0)" id="'+data.list[i].id+'" class="delete-btn"><i class="fa fa-remove"></i>Delete</a></td>'
					+ '</tr>';
			}
			$('table.striped tBody').html(html);
		}
	});
}

function dateFormater(value){
	value = new Date(value);
    var year = value.getFullYear();
    var month = value.getMonth() + 1;
    var day = value.getDate();
    var hour = value.getHours();       
    var minute = value.getMinutes();     
    var second = value.getSeconds();    
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
    	day = "0" + day;
    }
    if (hour >= 0 && hour <= 9) {
    	hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
    	minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
    	daysecond = "0" + second;
    }
    var dateStr =  year + "-" + month + "-" + day  + " " + hour + ":" + minute + ":" + second;
    return dateStr;
}
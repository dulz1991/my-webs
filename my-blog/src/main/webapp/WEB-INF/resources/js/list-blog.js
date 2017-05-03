var _menuId = -1;
var _pageNum = 1;
var _pageSize = 10;
var _title = "";

//加载右侧博客列表
loadBlogList();
//点击查看更多
function viewMore(){
	_pageNum = _pageNum +1;
	loadBlogList();
}

function loadBlogList() {
	$.ajax({ 
		url: "/auth/blog/queryBlogList", 
		data:{
			menuId: _menuId,
			pageNum: _pageNum,
			pageSize: _pageSize,
			title: encodeURI(_title)
		},  
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var html = '';
			for (var i=0; i<data.list.length; i++) {
				var item = data.list[i];
				
				var create = new Date(item.createTime).format("yyyy-MM-dd hh:mm:ss");
				var update = new Date(item.updateTime).format("yyyy-MM-dd hh:mm:ss");
				var img="";
				if (item.img != "" && item.img != null) {
					img = item.img;
				}
				var preContent=item.preContent;
				if(preContent==null){
					preContent="暂无简介";
				}
				
				html += '<div class="row portfolio-items-main pd-5" style="width:92%">';
				html += '	<div class="cbp-so-from-right">';
				html += '		<div class="row">';
				html += '			<div class="col-lg-12 port-desc-wrap">';
				html += '     			<div class="item item-0"><h3><a href="/blog/detail?id='+item.id+'" target="_blank">'+item.title+'</a></h3></div>';
				html += '     			<div class="item item-1"><a href="#">'+item.username+'</a> · '+create+'</div>';
				html += '     			<div class="item item-2">'+preContent+'</div>';
				html += '     			<div class="item item-3">';
				html += '     				<span class="bg-light-green white pd-5"><i class="fa fa-eye"></i> 阅读('+item.click+')</span>';
				html += '     				<span class="bg-yellow white pd-5"><i class="fa fa-heart-o"> 收藏 '+0+'</i></span>';
				html += '     			</div>';
				html += '     		</div>';
				if (img != "") {
					html += '     	<div class="col-lg-0 img-wrap-main mg-left-10" style="width:80%;">';
					html += '     		<a href="/blog/detail?id='+item.id+'" target="_blank">';
					html += '     			<img class="img-responsive" style="height:100%" src="'+item.img+'">';
					html += '     	    </a>';
					html += '     	</div>';
				}
				html += '     	</div>';
				html += '     </div>'
				html += '</div>';
			}
			$('#view-more').remove();
			if (data.page.hasNextPage) {
				html += '<div class="col-xs-12" id="view-more"><a href="javascript:;" onclick="viewMore()" class="btn btn-block btn-lg btn-info">查看更多</a></div>';
				/*$('#view-more').show();*/
			} else {
				/*$('#view-more').hide();*/
			}
			if (_pageNum == 1) {
				$('.blog-list').html(html);	
			} else {
				$('.blog-list').append(html);
			}
		}
	});
}


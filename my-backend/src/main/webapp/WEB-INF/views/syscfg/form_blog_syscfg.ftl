<div class="panel panel-default" style="width:760px;">
	<div class="panel-heading">
		<div class="panel-title">博客网站数据</div>
	</div>
	
	<div class="panel-body">
		<div class="form-group" >
			<label class="control-label">首页slogan</label>
			<div class="input-group">
				<input type="text" class="form-control no-right-border form-focus-purple" name="blogSlogan" value="${blogSlogan!''}">
				<span class="input-group-btn">
					<button class="btn btn-purple" onclick="saveSlogan()" type="button">确定</button>
				</span>
			</div>
		</div>
	</div>
	
</div>

<script>
function saveSlogan(){
	if($('input[name="blogSlogan"]').val()==''){
		$.common.tip("请输入slogan");
		return false;
	}
	var parm = {};
	parm.key = "blogSlogan";
	parm.value = $('input[name="blogSlogan"]').val();
	doSave(parm);
}
function doSave(parm){
	parm.flag = "0";
	$.common.postRequest(parm, '/backend/sys/doSave', function(data){
		if(data.errorNo==200){
			$.common.tip("修改成功");
		} else {
			$.common.tip(data.errorInfo);
		}
	});
}
</script>
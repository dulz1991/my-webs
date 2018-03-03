<div class="panel panel-default" style="width:760px;">
	<div class="panel-heading">
		<div class="panel-title">任务执行</div>
	</div>
	
	<div class="panel-body">
		<label class="control-label" style="font-size:20px;">备份数据库</label>
		<button class="btn btn-info excute1" style="margin-left:40px;" onclick="excute(this)" type="button">执行</button>
		<button class="btn excute11" style="margin-left:40px;display:none;" type="button">执行中...</button>
	</div>
	
</div>

<script>
function excute(obj){
	$(obj).next('button').show();
	$(obj).hide();
}
</script>
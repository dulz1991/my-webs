<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="modal-delete">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><i class="fa fa-trash-o fa-lg"></i> 删除提示</h4>
				</div>
				<div class="modal-body">
					Hello I am a Modal!
				</div>
				<div class="modal-footer">
					<input type="hidden" value="" name="hidden-url" /> 
					<input type="hidden" value="" name="hidden-id" /> 
					<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-info" onclick="$.fn.doDelete();" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="modal fade" id="modal-menu">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">编辑菜单</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="hidden" name="id" value="" />
								<label for="menuName" class="control-label">菜单名称</label>
								<input type="text" name="name" value="" class="form-control" id="menuName" placeholder="输入菜单名称" />
							</div>	
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="saveMenu()">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modal-submenu">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">编辑子菜单</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="hidden" name="id" value="" />
								<label for="menuName" class="control-label">子菜单名称</label>
								<input type="text" name="name" value="" class="form-control" id="menuName" placeholder="输入子菜单名称" />
							</div>	
							<div class="form-group">
								<label for="status" class="control-label">状态</label> (0:未开始; 1:编辑中; 2:已完成)
								<!-- <input type="text" name="status" value="" class="form-control" id="status" placeholder="Input menu status here" /> -->
								<select name="status" class="form-control" id="subMenuStatus">
									<option value="-1">-- 选择状态 -- </option>
									<option value="0">未开始</option>
									<option value="1">编辑中</option>
									<option value="2">已完成</option>
								</select>
							</div>	
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="saveSubMenu()">保存</button>
				</div>
			</div>
		</div>
	</div>
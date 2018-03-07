$(function(){
	//全选
	$('#chkAll').click(function () {
        chkAll($('#chkAll').is(":checked"));
    });
})
//全选或反选
function chkAll(isChecked) {
    $(":checkbox[name$='ID_CHECK']").each(function () {
        if (!$(this).is(":disabled")) {
            $(this).prop("checked", isChecked);
        }
    });
}

//修改用户状态
function changeUserStatusCallback(status, userId, callback){
	var title = "确定此次操作么";
	if(status==1){
		title = "确定恢复此账号么？"
	} else if(status==2){ 
		title = "确定屏蔽此账号么？"
	}
	var _parm={};
	_parm.status=status;
	_parm.id=userId;
	swal({
		title: title,
		text: "",
		type: 'warning',   //感叹号图标
		showCancelButton: true,   //显示取消按钮
		confirmButtonColor: '#d33', //俩个按钮的颜色
		cancelButtonColor: '#3085d6',
		confirmButtonText: '确定', //俩个按钮的文本
		cancelButtonText: '取消',
		confirmButtonClass: 'btn btn-info',  //俩个按钮的类样式
	}).then(function() {
		$.common.postRequest(_parm, '/backend/profile/changeUserStatus', function(data){
			callback(data);
		})
	}, function(dismiss) {
	})
}

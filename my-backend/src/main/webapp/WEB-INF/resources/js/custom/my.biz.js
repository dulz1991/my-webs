jQuery.biz = {
		/*禁用*/
		toDisable: function(id, url){
			var parm = {};
			parm.id=id;
			parm.isEnable = 0;
			$.common.postSync(parm, url, function(data){
				if(data.errorNo!=0){
					$.pop.error(data.errorInfo);
				} else {
					$.pop.success("禁用成功");
					$.fn.reload();
				}
			});
		},
		
		/*启用*/
		toEnable: function(id, url){
			var parm = {};
			parm.id=id;
			parm.isEnable = 1;
			$.common.postSync(parm, url, function(data){
				if(data.errorNo!=0){
					$.pop.error(data.errorInfo);
				} else {
					$.pop.success("启用成功");
					$.fn.reload();
				}
			});
		},
		
		/*根据开始时间和课时，计算对应的结束时间 默认15周*/
		calcEndDate : function(){
			var dateFrom = $('._startDate').val();
			if($.common.isBlank(dateFrom)){
				$('._endDate').val('');
				return;
			}
			var classTimes = $('._classTimes').val();
			if($.common.isBlank(classTimes)){
				classTimes = 15;
			}
			classTimes = parseInt(classTimes)-1;
			
			classTimes = classTimes*7;
			$.common.get({dateFrom:dateFrom,days:classTimes},'/getNextDay', function(data){
				if(data.errorNo==0){
					 $('._endDate').val(data.dateTo);
				}
			})
		},
		
		/*续费*/
		editNextFinanceDetail : function(financeId, financeDetailId){
			var loadUrl = '/finance/editDetail?financeId='+financeId;
			if($.common.isNotBlank(financeDetailId)){
				loadUrl += '&financeDetailId='+financeDetailId;
			}
			$.pop.dialog({loadUrl:loadUrl,width:"640px", height:"550px"}, $.biz.editNextFinanceDetailVerigy, function(data){
				$.pop.success('保存成功');
				$.fn.reload();
			});
		},
		editNextFinanceDetailVerigy : function(){
			var formData = $.common.getFormJson('#editFinanceDetailForm');
			var rst = '';
			$.common.postSync(formData, '/finance/doSaveDetail', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		},
		
		/* 退学 */
		endClass : function(financeId){
			var loadUrl = '/student/endClass?financeId='+financeId;
			$.pop.dialog({loadUrl:loadUrl,width:"640px", height:"350px",title:'退学'}, $.biz.endClassVerigy, function(data){
				$.pop.success('保存成功');
				$.fn.reload();
			});
		},
		endClassVerigy : function(){
			var formData = $.common.getFormJson('#editEndClassForm');
			var rst = '';
			$.common.postSync(formData, '/student/doEndClass', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		},
		
		/*查看学生详情*/
		viewStudentDetail : function(studentId){
			gotoUrl('/student/viewDetail?id='+studentId);
		},
		
		/*查看教师详情*/
		viewTeacherDetail : function(teacherId){
			gotoUrl('/teacher/viewDetail?userId='+teacherId);
		},
}
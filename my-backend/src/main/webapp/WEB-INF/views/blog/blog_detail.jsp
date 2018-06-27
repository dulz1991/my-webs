<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<div class="widget">
           	<div class="widget-header">
               	<i class="widget-icon fa fa-bold"></i>
               	<span class="widget-caption">
               		${entity.title} 
               		<a href="javascript:;">${username} |  <fmt:formatDate type="both" value="${entity.createTime}" /></a> 
               </span>
               
               <div class="widget-buttons">
                   <%-- <a href="javascript:gotoUrl('/backend/code/edit?id=${entity.id}')" title="编辑">
						<i class="fa fa-edit"></i>
					</a>
					<a href="javascript:reload('${entity.id}');" title="刷新">
						<i class="fa fa-rotate-right"></i>
					</a>
					<c:if test="${entity.status!=0 }">
						<a href="javascript:toDelete('${entity.id}');"  title="删除">
							<i class="fa fa-trash-o"></i>
						</a>
					</c:if> --%>
                   	<a href="#" data-toggle="maximize">
                       <i class="fa fa-expand"></i>
                   	</a>
                   	<a href="#" data-toggle="collapse">
                       <i class="fa fa-minus"></i>
                   	</a>
               	</div>
           	</div>
           
           	<div class="widget-body">
               	${entity.content}
           	</div>
       	</div>
       	
	<%-- <div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel panel-default"><!-- Add class "collapsed" to minimize the panel -->
				
				<div class="panel-heading">
					<h3 class="panel-title">${entity.title}</h3>
					<div class="panel-options">
						<a href="#">${username} | ${entity.createTime?datetime}</a>
						<a href="#">
							<i class="linecons-cog"></i>
						</a>
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">–</span>
							<span class="expand-icon">+</span>
						</a>
						<a href="#" data-toggle="reload">
							<i class="fa-rotate-right"></i>
						</a>
						<a href="#" data-toggle="remove">
							×
						</a>
					</div>
				</div>
				
				<div class="panel-body">
					${entity.content}
				</div>
				
			</div>
		</div>
	</div> --%>
	
	<script>
	$(function(data){
		$.common.pageTitle('${entity.title}');
	})
	</script>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="demo-detail" style="width:800px;">
	<div class="title">
		<p style="border-bottom:1px solid #ccc;padding-bottom:5px;">【${demo.title }】</p>
	</div>
	<div class="img"><img alt="" src="/api_img${demo.picPath }" width="100%"></div>
	<div class="desc">${demo.description }</div>
	<div class="btns">
		<a href="javascript:;" onclick="downloadResource('${demo.id }')" style="color:#5aba1f" class="downloadBtn"><i class="fa fa-download"></i> 下载</a>
	</div>
</div>


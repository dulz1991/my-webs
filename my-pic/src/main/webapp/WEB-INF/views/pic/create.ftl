<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="/frameworkmine/style/images/favicon.png" />
<title>Create</title>
<#include "../share/meta.ftl">
</head>
<body>

<!-- header -->
<#include "../share/nav.ftl">
<script src="/ue/editor_config.js"></script>
<script src="/ue/editor_api.js"></script>
<link href="/ue/themes/default/ueditor.css" rel="stylesheet">
<script src="/mine/js/my-pic-ajax.js"></script>

<div class="row container">
	<br />
	<form class="col s12">
		<input name="id" type="hidden" value="${id}" class="validate">
        <div class="row">
            <div class="input-field col s12">
            	<i class="fa fa-text-width prefix"></i>
               	<input id="icon_prefix" name="title" type="text" value="${picBlog.title}" class="validate">
               	<label for="icon_prefix" class="">输入标题</label>
           	</div>
		</div>
		<div class="row">
			<textarea id="myEditor" name="content" style="width:100%; margin:0;">${picBlog.content}</textarea>
			<script type="text/javascript">
				var editor_a = new baidu.editor.ui.Editor();
				//editor_a.render('myEditor');
				window.onload = render;
				function render() {
 		           	editor_a.render('myEditor');
        		}
			</script>
		</div>
		<div class="row">
        	<div class="input-field col s12" style="text-align:right;">
            	<a id="picBlogSubmit" class="waves-effect waves-light btn-large blue" href="javascript:void(0);">提交</a>
            </div>
       	</div>
  	</form>
</div>

<!-- footer -->
<#include "../share/footer.ftl">

</body>
</html>
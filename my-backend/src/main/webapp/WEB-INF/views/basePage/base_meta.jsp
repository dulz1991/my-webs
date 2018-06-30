<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/img/logo.png" title="" rel="shortcut icon" type="image/x-icon">

<!--Basic Styles-->
<link href="/assets/css/bootstrap.min.css"  title="" rel="stylesheet" />
<link href="/assets/css/bootstrap-multiselect.css"  title="" rel="stylesheet" />
<link href="/assets/css/font-awesome.min.css" title="" rel="stylesheet" />
<!--Beyond styles-->
<link href="/assets/css/beyond.min.css" title="" id="beyond-link"  rel="stylesheet" />
<!-- <link href="/assets/css/typicons.min.css" title="" rel="stylesheet" /> -->
<link href="/assets/css/animate.min.css" title="" rel="stylesheet" />
<link href="/assets/css/dataTables.bootstrap.css" title="" rel="stylesheet" />
<link href="/css/style.css" title="" rel="stylesheet" />
<!-- skins -->
<link href="" id="skin-link"  title="" rel="stylesheet" type="text/css" />
<script src="/assets/js/skins.min.js"></script>

<script src="/assets/js/jquery-2.0.3.min.js"></script>
<script src="/assets/js/jquery.form.js"></script>
<script src="/assets/js/jquery-ui-1.10.4.custom.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/bootstrap-multiselect.js"></script>
<script src="/assets/js/datatable/jquery.dataTables.min.js"></script>
<script src="/assets/js/toastr/toastr.js"></script>
<!-- <script src="/assets/js/bootbox/bootbox.js"></script> -->
<!-- <script src="/assets/js/datatable/ZeroClipboard.js"></script>
<script src="/assets/js/datatable/dataTables.tableTools.min.js"></script>
<script src="/assets/js/datatable/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/datatable/datatables-init.js"></script> -->
<!-- layer -->
<script type="text/javascript" src="/js/plugins/layer/layer.js"></script>
<!-- 自定义 js -->
<script type="text/javascript" src="/js/custom/my.common.js"></script>
<script type="text/javascript" src="/js/custom/my.pop.js"></script>
<script type="text/javascript" src="/js/custom/my.biz.js"></script>
<script type="text/javascript" src="/js/custom/my.formRender.js"></script>
<script type="text/javascript" src="/js/custom/my.datatable.js"></script>
<script type="text/javascript" src="/js/custom/global.js"></script>
<!-- 拖动排序 -->
<!-- <script type="text/javascript" src="/js/custom/tablednd.js"></script> -->
<!-- 分页 -->
<link rel="stylesheet" type="text/css" href="/js/plugins/page/my.page.css">
<script type="text/javascript" src="/js/plugins/page/my.page.js"></script>
<!-- 日期选择插件1 -->
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<!-- 图表插件 -->
<!-- <script type="text/javascript" src="/js/plugins/statisticalGraph/echarts.js"></script> -->
<!-- ztree -->
<!-- <link rel="stylesheet" title='' href="/js/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css"> -->
<link rel="stylesheet" title='' href="/js/plugins/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="/js/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/plugins/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="/js/plugins/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<!-- 自动完成 -->
<link title='' href="/js/plugins/autoComplete/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/plugins/autoComplete/jquery.autocompleter.js"></script>
<!-- excel导出 -->
<script src="/assets/js/jquery.table2excel.js"></script>

<!-- ueditor -->
<script src="/js/plugins/ue/ueditor.config.js"></script>
<script src="/js/plugins/ue/ueditor.all.min.js"></script>
<link href="/js/plugins/ue/themes/default/css/ueditor.css" title='' rel="stylesheet">

<!--Beyond Scripts-->
<script src="/assets/js/beyond.min.js" defer async="true"></script>


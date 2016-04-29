<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="staticResourceTag" value="${sessionScope.static_resource_tag}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="${ctx}/static/css/bootstrapValidator.min.css"
	rel="stylesheet">
<link href="${ctx}/static/css/sco.message.css" rel="stylesheet"> 
<link href="${ctx}/static/css/style.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/main.css?tag=${staticResourceTag}" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="${ctx}/static/js/html5shiv.js"></script>
<![endif]-->

<!-- Fav and touch icons -->
<link rel="shortcut icon" href="${ctx}/static/img/favicon.png?tag=${staticResourceTag}">

  <script type="text/javascript"src="${ctx}/static/js/jquery-1.11.3.min.js"></script>

 <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/moment.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/sco.message.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/locales/moment-zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="${ctx}/static/js/locales/bootstrap-table-zh-CN.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${ctx}/static/js/locales/bootstrapValidator.zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/scripts.js?v=1.0"></script> 


<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.metadata.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/messages_zh.js"></script>
<style type="text/css">
.glyphicon-user {
	font-size: 14px;
}

.user-name {
	padding-left: 3px;
}

.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #ffffff;
}

.dropdown-submenu .pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>
</head>
<body>

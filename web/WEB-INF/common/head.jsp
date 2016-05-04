<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="firstMenuList" value="${sessionScope.user_menu.firstMenuList }" />
<c:set var="currentMenu" value="${sessionScope.current_url}" />
<c:set var="staticResourceTag" value="${sessionScope.static_resource_tag}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会议系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">


<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
<!--script src="js/less-1.3.3.min.js"></script-->
<!--append ‘#!watch’ to the browser URL, then refresh the page. -->

<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-datetimepicker.min.css"	rel="stylesheet">
<link href="${ctx}/static/css/bootstrapValidator.min.css"	rel="stylesheet">
<link href="${ctx}/static/css/sco.message.css" rel="stylesheet"> 
<link href="${ctx}/static/css/style.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/main.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/report.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/jquery.validate.css" rel="stylesheet">
<link href="${ctx}/static/css/mymodal.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/jquery-ui.min.css" rel="stylesheet">

<link rel="shortcut icon" href="${ctx}/static/img/favicon.ico?tag=${staticResourceTag}">
  <script type="text/javascript"src="${ctx}/static/js/jquery-1.11.3.min.js"></script>

 <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/moment.min.js"></script>
<script type="text/javascript"	src="${ctx}/static/js/bootstrap-table.min.js"></script>
<script type="text/javascript"	src="${ctx}/static/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"	src="${ctx}/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/sco.message.js"></script>
<script type="text/javascript"	src="${ctx}/static/js/locales/moment-zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript"	src="${ctx}/static/js/locales/bootstrap-table-zh-CN.min.js"	charset="UTF-8"></script>
<script type="text/javascript"	src="${ctx}/static/js/locales/bootstrapValidator.zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/scripts.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-ui.min.js"></script>

<script type="text/javascript" src="${ctx}/static/js/jquery.metadata.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/static/js/mymodal.js?tag=${staticResourceTag}"></script>
<script type="text/javascript" src="${ctx}/static/js/ajax-error.js?tag=${staticResourceTag}"></script>




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
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				
				<ul id="yw3" class="nav">
				  <c:if test="${not empty firstMenuList }" >
				  <c:forEach items="${firstMenuList}" var="menu">
				  <c:choose>
				  <c:when test="${currentMenu==menu.url }">
				  <li class="active"><a href="<c:url value="${menu.url}" />">${menu.name}</a></li>
				  </c:when>
				  <c:otherwise>
				  <li><a href="<c:url value="${menu.url}" />">${menu.name}</a></li>
				  </c:otherwise>
				  
				  </c:choose>
				  
				  </c:forEach>
				  </c:if>
				</ul>
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$.validator.addMethod("maxByteLength",
				function (value, element, params) {
					return this.optional(element) || value.replace(/[^\x00-\xff]/gi, "--").length <= params;
				},
				$.validator.format("至多只能输入{0}个字符!"));

		$.validator.addMethod("integer", function (value, element) {
			//去掉字符串前后的空格
			value =trim(value);
			var aint = parseInt(value);
			return this.optional(element) || (aint > 0 && (aint + '' == value));
		}, "请输入正整数");

		$.validator.addMethod("number", function(value, element) {
			var aint=parseInt(value);
			return this.optional(element) || (aint>=0 && (aint+''==value));
		}, "请输入自然数");
	</script>

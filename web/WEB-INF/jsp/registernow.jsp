<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="${ctx}/static/css/sco.message.css" rel="stylesheet">
<link href="${ctx}/static/css/style.css?tag=${staticResourceTag}" rel="stylesheet">
<link href="${ctx}/static/css/main.css?tag=${staticResourceTag}" rel="stylesheet">


<link rel="shortcut icon" href="${ctx}/static/img/favicon.ico?tag=${staticResourceTag}">
<script type="text/javascript" src="${ctx}/static/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/sco.message.js"></script>
<script type="text/javascript" src="${ctx}/static/js/locales/moment-zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/static/js/locales/bootstrap-table-zh-CN.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/static/js/locales/bootstrapValidator.zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/scripts.js?tag=${staticResourceTag}"></script>

<html>
<head>
    <title>注册</title>
</head>
<body>
<header id="navigator" class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span><span
                    class="icon-bar"></span><span class="icon-bar"></span><span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx }/">登录</a>
        </div>

    </div>
</header>
<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
        <div class="tit">
            <h2>注册</h2>
        </div>
        <div class="mymodal-form clearfix">
            <form method="post" action="${ctx}/registersave">
                <ul>
                    <li>
                        <label style=" width:150px;">用户名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="username_id" name="username"
                               autocomplete="off" required>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">密码：</label>
                        <input type="password" class="form-input" style="width:500px;" id="password_id" name="password"
                               autocomplete="off" required>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">真实姓名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="trueName_id" name="trueName"
                               required>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">邮箱（仅当验证成功才可登录）：</label>
                        <input type="text" class="form-input" style="width:500px;" id="email_id" name="email" required>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">手机号码：</label>
                        <input type="text" class="form-input" style="width:500px;" id="telephone_id" name="telephone" required>
                    </li>
                </ul>
                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                        <input type="submit"
                               value="确认"
                               class="btn btn-primary">

                    </li>
                </ul>
            </form>
        </div>
        <div class="panel-footer">
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    $(function () {
        $('#username_id').val("");

        $('#password_id').val("");
        $('#trueName_id').val("");
        $('#email_id').val("");


        if (${status==true}) {
            $.scojs_message('${message}', $.scojs_message.TYPE_OK);
        } else if (${status==false}) {
            $.scojs_message('${message}', $.scojs_message.TYPE_ERROR);
        }

    })

</script>
</html>

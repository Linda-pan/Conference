<%@ page import="com.elin4it.ssm.model.SessionUser" %>
<%@ page import="com.elin4it.ssm.utils.WebUtil" %>
<%@ taglib prefix="width" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/4/26
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>


<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
        <div class="tit">
            <h2>用户详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <form id="frm_id" method="post" action="">

                <input type="hidden" id="user_id" name="userId" value="${userId}"/>

                <ul>
                    <li>
                        <label style=" width:150px;">用户名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="username_id" name="username"
                               readonly>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">头衔：</label>
                        <input type="text" class="form-input" style="width:500px;" id="title_id" name="title" readonly>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">真实姓名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="trueName_id" name="trueName"
                               readonly>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">是否实名：</label>
                        <select class="form-control-horizontal" style="width:300px;" id="is_show_name_id"
                                name="is_show_id" readonly disabled="disabled">
                            <c:forEach items="${StatusMap}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">电话：</label>
                        <input type="text" class="form-input" style="width:500px;" id="telephone_id" name="telephone"
                               readonly>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">邮箱：</label>
                        <input type="text" class="form-input" style="width:500px;" id="email_id" name="email"
                               readonly>
                    </li>
                </ul>
                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                        <c:if test="${statu==1}">
                            <input type="button"
                                   value="修改用户信息"
                                   class="btn btn-primary" onclick="changeDetail()">
                            <input type="button"
                                   value="修改密码"
                                   class="btn btn-primary" onclick="changePassword()">
                        </c:if>
                    </li>
                </ul>
            </form>
        </div>
        <div class="panel-footer">
        </div>
    </div>
</div>

<div class="mymodal" style="display: none;" id="change_detail_id">
    <div class="tit">
        <h2>修改用户信息</h2>
        <a href="javascript:;" class="closes" id="change_detail_close">取消</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="detail_id" method="post" action="${ctx }/user/save">
            <input type="hidden" id="user1_id" name="userId"/>
            <input type="hidden" id="role1_id" name="roleId"/>
            <ul>
                <li>
                    <label style=" width:150px;">用户名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="username1_id" name="username"
                           validate="{required:true,maxByteLength:60}">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">头衔：</label>
                    <input type="text" class="form-input" style="width:500px;" id="title1_id" name="title"
                           validate="{required:true,maxByteLength:60}" onfocus="removeError('detail_error_id');">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">真实姓名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="trueName1_id" name="trueName"
                           validate="{required:true,maxByteLength:60}" onfocus="removeError('detail_error_id');">
                </li>
            </ul>
            <c:if test="${roleId==0}">
                <ul>
                    <li>
                        <label style="width:150px;">是否实名：</label>
                        <select class="form-control-horizontal" style="width:300px;" id="is_show_name1_id"
                                name="is_show_id" readonly>
                            <c:forEach items="${StatusMap}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
            </c:if>
            <ul>
                <li>
                    <label style="width:150px;">电话：</label>
                    <input type="text" class="form-input" style="width:500px;" id="telephone1_id" name="telephone"
                           validate="{required:true}" onfocus="removeError('detail_error_id');">
                </li>
            </ul>
            <ul>
                <li><label style="width:150px;">邮箱(已验证不可修改)：</label>
                    <input type="text" class="form-input" style="width:500px;" id="email1_id" name="email"
                           validate="{required:true}" readonly>
                </li>
            </ul>
            <label style="width:150px;" id="detail_error_id" class="error"></label>
            <ul>
                <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                    <input type="submit"
                           value="修改"
                           class="btn btn-primary" onclick="return saveDetail()">
                </li>
            </ul>
        </form>
    </div>
</div>

<div class="mymodal" style="display: none;" id="change_password_id">
    <div class="tit">
        <h2>修改密码</h2>
        <a href="javascript:;" class="closes" id="change_password_close">取消</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="detail2_id" method="post">
            <form id="frm2_id" method="post" action="">
                <input type="hidden" id="user2_id" name="userId"/>
                <ul>
                    <li>
                        <label style=" width:150px;">用户名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="username2_id" name="username"
                               readonly>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">密码(大于6位)：</label>
                        <input type="password" class="form-input" style="width:500px;" id="password1_id"
                               name="password1"
                               onfocus="removeError('hot_error_id');">
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">确认密码：</label>
                        <input type="password" class="form-input" style="width:500px;" id="password_id" name="password"
                               onfocus="removeError('hot_error_id');">
                    </li>
                </ul>
                <label style="width:150px;" id="hot_error_id" class="error"></label>
                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                        <input type="submit"
                               value="修改密码"
                               class="btn btn-primary" onclick="return checkPassword()">

                    </li>
                </ul>
            </form>
        </form>
    </div>
</div>

<div id="change_detail_mask" class="mask" style="display:none;">
    <div class="mask-tips"></div>
</div>

<div id="change_password_mask" class="mask" style="display:none;">
    <div class="mask-tips"></div>
</div>

<script>
    $(function () {
        var userId = $('#user_id').val();
        $.ajax({
            url: '${ctx}/user/userdetail',
            type: 'post',
            datatype: 'json',
            data: {userId: userId},
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        var user = result.data;
                        if (user != null) {
                            $('#username_id').val(user.username);
                            $('#telephone_id').val(user.telephone);
                            $('#title_id').val(user.title);
                            $('#email_id').val(user.email);

                            if (user.isShowName == true) {
                                $('#is_show_name_id').val(1);
                            } else {
                                $('#is_show_name_id').val(0);
                            }
                            $('#trueName_id').val(user.trueName);

                            $('#user1_id').val(userId);
                            $('#username1_id').val(user.username);
                            $('#telephone1_id').val(user.telephone);
                            $('#title1_id').val(user.title);
                            $('#email1_id').val(user.email);
                            $('#role1_id').val(user.roleId);

                            if (user.isShowName == 'true') {
                                $('#is_show_name1_id').val(1);
                            } else {
                                $('#is_show_name1_id').val(0);
                            }
                            $('#trueName1_id').val(user.trueName);


                            $('#user2_id').val(user.userId);
                            $('#username2_id').val(user.username);
                        }
                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
            },
            error: function (result) {
                requestError(result);
            }
        });

        $('#change_detail_close').on('click', function () {
            closeModal("change_detail_id", "change_detail_mask");
        });

        $('#change_password_close').on('click', function () {
            closeModal("change_password_id", "change_password_mask");
        });
    });

    function changeDetail() {
        showModal("change_detail_id", "change_detail_mask");
    }

    function saveDetail() {
        var userId = $('#user1_id').val();
        var telephone = $('#telephone1_id').val();
        var title = $('#title1_id').val();
        var trueName = $('#trueName1_id').val();
        var isShowName = $('#is_show_name1_id').val();
        var username = $('#username1_id').val();
        if (telephone.length == 0 || title.length == 0 || trueName.length == 0) {
            addError('detail_error_id', "必填");
            return false;
        }
        if (telephone.length != 11) {
            addError('detail_error_id', "电话为11位");
            return false;
        }
        var n = Number(telephone);
        if (isNaN(n)) {
            addError('detail_error_id', "电话必须全为数字");
            return false;
        }

        $.ajax({
            url: '${ctx}/user/save',
            type: 'post',
            datatype: 'json',
            data: {
                userId: userId,
                telephone: telephone,
                title: title,
                trueName: trueName,
                isShowName: isShowName,
                username: username
            },
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        closeModal("change_detail_id", "change_detail_mask");
                        return true;
                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
            },
            error: function (result) {
                requestError(result);
            }
        });

        return false;
    }

    function removeError(id) {
        $('#' + id).css('display', 'none');
    }

    function addError(id, message) {
        $('#' + id).text(message);
        $('#' + id).css('display', 'block');
    }

    function changePassword() {
        showModal("change_password_id", "change_password_mask");
    }

    function checkPassword() {
        var userId = $('#user2_id').val();
        var password1 = $('#password1_id').val();
        var password = $('#password_id').val();

        if (password.length == 0 || password1.length == 0) {
            addError('hot_error_id', "必填");
            return false;
        }
        if (password.length < 6 || password1.length < 6) {
            addError('hot_error_id', "密码至少为6位");
            return false;
        }
        if (password != password1) {
            addError('hot_error_id', "密码两次输入不一致");
            return false;
        }

        $.ajax({
            url: '${ctx}/user/save/password',
            type: 'post',
            datatype: 'json',
            data: {
                userId: userId,
                password: password
            },
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        closeModal("change_password_id", "change_password_mask");
                        return true;
                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
            },
            error: function (result) {
                requestError(result);
            }
        });

    }

    $('#change_password_close').on('click', function () {
        closeModal("change_password_id", "change_password_mask");
    });
</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
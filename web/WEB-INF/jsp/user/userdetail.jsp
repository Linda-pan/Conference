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
<%
    SessionUser user = WebUtil.getCurrentUser(request);
    int userId = user.getUserId();
%>
<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
        <div class="tit">
            <h2>用户详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <form id="frm_id" method="post" action="">

                <input type="hidden" id="user_id" name="userId" value="<%=userId%>"/>

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
                                name="is_show_id" readonly="">
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
                        <input type="submit"
                               value="修改"
                               class="btn btn-primary" onclick="changeDetail()">
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
            <input type="hidden" id="user2_id" name="userId"/>
            <ul>
                <li>
                    <label style=" width:150px;">用户名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="username1_id" name="username"
                           validate="{required:true,maxByteLength:60}" readonly>
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">头衔：</label>
                    <input type="text" class="form-input" style="width:500px;" id="title1_id" name="title"
                           validate="{required:true,maxByteLength:60}">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">真实姓名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="trueName1_id" name="trueName"
                           validate="{required:true,maxByteLength:60}">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">是否实名：</label>
                    <select class="form-control-horizontal" style="width:300px;" id="is_show_name1_id"
                            name="is_show_id">
                        <c:forEach items="${StatusMap}" var="type">
                            <option value="${type.key}">${type.value}</option>
                        </c:forEach>
                    </select>
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">电话：</label>
                    <input type="text" class="form-input" style="width:500px;" id="telephone1_id" name="telephone"
                           validate="{required:true}">
                </li>
            </ul>
            <ul>
                <li><label style="width:150px;">邮箱：</label>
                    <input type="text" class="form-input" style="width:500px;" id="email1_id" name="email"
                           validate="{required:true}">
                </li>
            </ul>
            <ul>
                <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                    <input type="submit"
                           value="修改"
                           class="btn btn-primary">
                </li>
            </ul>
        </form>
    </div>
</div>

<div id="change_detail_mask" class="mask" style="display:none;">
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
                                $('#is_show_name_id').val(0);
                            } else {
                                $('#is_show_name_id').val(1);
                            }
                            $('#trueName_id').val(user.trueName);
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
    });

    function changeDetail() {
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
                            $('#user2_id').val(userId);
                            $('#username1_id').val(user.username);
                            $('#telephone1_id').val(user.telephone);
                            $('#title1_id').val(user.title);
                            $('#email1_id').val(user.email);

                            if (user.isShowName == 'true') {
                                $('#is_show_name1_id').val(0);
                            } else {
                                $('#is_show_name1_id').val(1);
                            }
                            $('#trueName1_id').val(user.trueName);
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

        showModal("change_detail_id", "change_detail_mask");
    }

</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
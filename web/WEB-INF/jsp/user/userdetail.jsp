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

<div id="live_detail_id">
    <div class="tit">
        <h2>用户详情</h2>
    </div>
    <div class="mymodal-form clearfix">
        <form id="frm_id" action="" method="post">
            <input type="hidden" name="userId" id="user_id">
            <ul>
                <li>
                    <label style="width:150px;">用户名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="username_id"
                           validate="{required:true,maxByteLength:60}" readonly>
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">头衔：</label>
                    <input type="text" class="form-input" style="width:500px;" id="title_id" name="title_id"
                           validate="{required:true,maxByteLength:60}">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">真实姓名：</label>
                    <input type="text" class="form-input" style="width:500px;" id="name_id" name="name_id"
                           validate="{required:true,maxByteLength:60}">
                </li>
            </ul>
            <ul>
                <li>
                    <label style="width:150px;">电话：</label>
                    <input type="text" class="form-input" style="width:500px;" name="telephone_id" id="telephone_id"
                           validate="{required:true}">
                </li>
            </ul>
            <ul>
                <li><label style="width:150px;">邮箱：</label>
                    <input type="text" class="form-input" style="width:500px;" name="email_id" id="email_id"
                           validate="{required:true}">
                </li>
            </ul>
        </form>
    </div>
</div>
<script>
    $(function (id) {
        id=4;
        $.ajax({
            url: '${ctx}/user/userdetail',
            type: 'post',
            datatype: 'json',
            data: {id: id},
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        var user = result.data;
                        if (user != null) {
                            $('#username_id').val(user.username)
                            $('#telephone_id').val(user.telephone);
                            $('#email_id').val(user.email);
                            $('#name_id').val(user.truename);

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
    })
</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
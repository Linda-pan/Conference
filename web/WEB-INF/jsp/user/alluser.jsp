<%@ page import="com.elin4it.ssm.constant.UserRoleConst" %><%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/3
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>所有用户后台列表</h2>
        </div>
        <div class="search-form">
            <form class="form-horizontal">
                <div class="search-condition">
                    <select class="form-control-horizontal" name="status" id="status_id">
                        <c:forEach items="${status}" var="type">
                            <option value="${type.key}">${type.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <input class="btn btn-primary" type="button" id="searchBtn" value="查询" id="searchBtn">
            </form>
        </div>
        <table id="tb" data-url="${ctx}/user/alluser/list" data-toggle="table"
               data-toolbar="#custem-toolbar" data-show-refresh="true"
               data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
               data-query-params="queryParams">
            <thead>
            <tr>
                <th data-field="userId" data-align="">ID</th>
                <th data-field="username" data-align="">用户名</th>
                <th data-field="trueName" data-align="">真实姓名</th>
                <th data-field="title" data-align="">头衔</th>
                <th data-field="telephone" data-align="">电话</th>
                <th data-field="email" data-align="">邮箱</th>
                <th data-field="roleId" data-align="" data-formatter="roleDetail">角色(点击修改权限)</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="panel-footer">
    </div>
</div>

<div class="mymodal" style="display:none;" id="change_role_id">
    <div class="tit">
        <h2>修改权限</h2>
        <a href="javascript:;" class="closes" id="change_role_close">关闭</a>
    </div>
    <div class="mymodal-form clearfix">
        <ul>
            <li>
                <label style="width:15%;"> 真实姓名：</label>
                <input style="width:30%;" id="userName">
            </li>
        </ul>
        <ul>
            <li>
                <label style="width:15%;"> 权限：</label>
                <select class="form-control-horizontal" name="status" id="role_id">
                    <c:forEach items="${status}" var="type">
                        <option value="${type.key}">${type.value}</option>
                    </c:forEach>
                </select>
            </li>
        </ul>
        <ul>
            <li style="margin-top: 10px;">
                <label style="width:40%;"></label>
                <input type="hidden" id="userId">
                <input type="button" value="确定" id="upload_id" class="btn btn-primary" onclick="changeSubmit()">
            </li>
        </ul>

    </div>
</div>

<div id="change_role_mask" class="mask" style="display:none;">
    <div class="mask-tips"></div>
</div>


<script type="text/javascript">
    function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageNo: params.pageNumber,
            status: $('#status_id').val()
        };
    }

    function roleDetail(value, row, index) {
        var content = [];
        content.push('<a href="javascript:;"  ');
        content.push('onclick="changeRole(' + row.userId + ',\''+ row.trueName+'\');return false;" ');
        content.push('>');

        if (value == 0) {
            content.push("<%=UserRoleConst.AUTHOR0%>");
        } else if (value == 1) {
            content.push("<%=UserRoleConst.ADMIN1%>");
        } else {
            content.push("<%=UserRoleConst.REVIEWER2%>");
        }

        content.push('</a>');
        var a = content.join('');
        return a;
    }

    $('#tb').bootstrapTable().on('load-error.bs.table', function (e, status) {
        requestError(status);
    });

    $('#searchBtn').on('click', function () {
        $('#tb').bootstrapTable('setPageNumber', 1);
        $("#tb").bootstrapTable('refresh');
        return false;
    });

    $('#change_role_close').on('click', function () {
        closeModal("change_role_id", "change_role_mask");
    });

    function changeRole(userId,userName) {
        $('#userName').val(userName);
        $('#userId').val(userId);
        showModal("change_role_id", "change_role_mask");
    }

    function changeSubmit() {
       var roleId=$('#role_id').val();
        var userId=$('#userId').val();
        $.ajax({
            url: '${ctx}/user/alluser/save',
            type: 'post',
            datatype: 'json',
            data: {
                userId: userId,
                roleId: roleId
            },
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        $.scojs_message(result.message, $.scojs_message.TYPE_OK);
                        closeModal("change_role_id", "change_role_mask");
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
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
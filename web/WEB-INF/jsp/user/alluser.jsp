<%@ page import="com.elin4it.ssm.constant.UserRoleConst" %><%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/3
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>所有用户后台列表</h2>
        </div>
        <div class="search-form">
            <form class="form-horizontal">
                <div class="search-condition">
                    <select class="form-control-horizontal" name="status" id="status_id">
                        <c:forEach items="${status}" var="type" >
                            <option value="${type.key}" >${type.value}</option>
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
                <th data-field="username" data-align="" >用户名</th>
                <th data-field="trueName" data-align="">真实姓名</th>
                <th data-field="title" data-align="">头衔</th>
                <th data-field="telephone" data-align="">电话</th>
                <th data-field="email" data-align="">邮箱</th>
                <th data-field="roleId" data-align="" data-formatter="roleDetail">角色</th>
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
                <label style="width:40%;"></label>
                <p id="upload_file_note">
                    请使用 <b>小于2MB</b> <b>jpg/png/bmp</b> 格式图片
                </p>
            </li>
        </ul>
        <ul>
            <li>
                <label style="width:40%;"></label>
                <input type="file" id="upload_file_id" accept="image/jpeg, image/png, image/bmp" onchange="img_upload_handle(this.files)">
            </li>
        </ul>
        <ul>
            <li style="margin-top: 10px;">
                <label style="width:40%;"></label>
                <input type="hidden" id="trigger_id">
                <input type="hidden" id="roleId">
                <input type="button" value="确定" id="upload_id" class="btn btn-primary" onclick="uploadSubmit()">
            </li>
        </ul>

    </div>
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
        content.push('onclick="changeRole(' + row.userId + ');return false;" ');
        content.push('>');

        if(value==0){
            content.push("<%=UserRoleConst.AUTHOR0%>");
        }else if(value==1){
            content.push("<%=UserRoleConst.ADMIN1%>");
        }else{
            content.push("<%=UserRoleConst.REVIEWER2%>");
        }

        content.push('</a>');
        var a = content.join('');
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

    function changeRole(userId){
        show("","");
    }

</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
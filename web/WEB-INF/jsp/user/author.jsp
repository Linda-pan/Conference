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
            <h2>审核缴费单后台列表</h2>
        </div>

        <table id="tb" data-url="${ctx}/user/author/list" data-toggle="table"
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
                <th data-field="paperNum" data-align="" data-formatter="authorPaperList">所有论文(点击跳转作者论文页面)</th>
                <th data-field="passpaperNum" data-align="" >通过论文数目</th>
                <th data-field="isPaymentConfirmed" data-align="" data-formatter="changeStatus">缴费图片是否已通过审核</th>
                <th data-field="" data-align="" >缴费图片（点击审核缴费图片）</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="panel-footer">
    </div>
</div>


<script type="text/javascript">
    function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageNo: params.pageNumber
        };
    }

    function authorPaperList(value, row, index) {
        var content = [];
        content.push('<a href="${authorPaperUrl}' + row.userId + '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }

    function changeStatus(value, row, index) {
        var content = [];
        content.push('<a href="javascript:;"  ');
        content.push('onclick="change(' + row.userId + ');return false;" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }

    function change(userId){
        showModal("","");
    }

</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
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
                <th data-field="username" data-align="">用户名</th>
                <th data-field="trueName" data-align="">真实姓名</th>
                <th data-field="title" data-align="">头衔</th>
                <th data-field="telephone" data-align="">电话</th>
                <th data-field="email" data-align="">邮箱</th>
                <th data-field="paperNum" data-align="" data-formatter="authorPaperList">所有论文(点击跳转作者论文页面)</th>
                <th data-field="passpaperNum" data-align="">通过论文数目</th>
                <th data-field="inform" data-align="" data-formatter="changeEmail">邮件通知(第一次通过审核或者催促缴费或者通知缴费单审核结果)</th>
                <th data-field="isPaymentConfirmed" data-align="" data-formatter="changeStatus">缴费图片是否已通过审核</th>
                <th data-field="" data-align="">缴费图片（点击审核缴费图片）</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="panel-footer">
    </div>
</div>

<div class="mymodal" style="display: none;" id="change_detail_id">
    <div class="tit">
        <h2>修改用户信息</h2>
        <a href="javascript:;" class="closes" id="change_detail_close">取消</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="detail_id" method="post" action="${ctx }/user/author/savepayment">
            <ul>
                <li>
                    <label style=" width:150px;">确认作者Id：</label>
                    <input type="text" class="form-input" style="width:500px;" id="user_id" name="userId" readonly>
                </li>
            </ul>
            <select class="form-control-horizontal" name="isPaymentConfirmed" id="status_id">
                <c:forEach items="${status}" var="type">
                    <option value="${type.key}">${type.value}</option>
                </c:forEach>
            </select>
            <ul>
                <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                    <input type="submit"
                           value="确定"
                           class="btn btn-primary" >
                </li>
            </ul>
        </form>
    </div>
</div>

<div id="change_detail_mask" class="mask" style="display:none;">
    <div class="mask-tips"></div>
</div>


<script type="text/javascript">
    function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageNo: params.pageNumber
        };
    }

    $('#change_detail_close').on('click', function () {
        closeModal("change_detail_id", "change_detail_mask");
    });

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

    function change(userId) {
        $('#user_id').val(userId);
        showModal("change_detail_id", "change_detail_mask");
    }

    function changeEmail(value, row, index) {
        var content = [];
        if (value == 1) {
            content.push('<a href="javascript:;"  ');
            content.push('onclick="sent('+row.userId +',\''+1 +'\');return false;" ');
            content.push('>');
            content.push("通知已通过审核");
            content.push('</a>');
        }else if(value == 2){
            content.push('<a href="javascript:;"  ');
            content.push('onclick="sent('+row.userId +',\''+2 +'\');return false;" ');
            content.push('>');
            content.push("催促缴费");
            content.push('</a>');
        } else if(value == 3){
            content.push('<a href="javascript:;"  ');
            content.push('onclick="sent('+row.userId +',\''+row.isPaymentConfirmed +'\');return false;" ');
            content.push('>');
            content.push("通知缴费单审核结果");
            content.push('</a>');
        }
        var a = content.join('');
        return a;
    }

</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
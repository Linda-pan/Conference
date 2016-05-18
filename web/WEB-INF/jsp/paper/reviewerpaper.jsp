<%@ page import="com.elin4it.ssm.constant.PaperStatusConst" %><%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/6
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>审查者论文列表</h2>
        </div>
        <input type="hidden" id="user_id" name="userId" value="${userId}"/>

        <table id="tb" data-url="${ctx}/paper/reviewerpaper" data-toggle="table"
               data-toolbar="#custem-toolbar" data-show-refresh="true"
               data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
               data-query-params="queryParams">

            <thead>
            <tr>
                <th data-field="paperId" data-align="">ID</th>
                <th data-field="paperName" data-align="">论文名</th>
                <th data-field="authorName" data-align="" data-formatter="authorDetail">论文作家</th>
                <th data-field="paperContent" data-align="" data-formatter="look">论文内容</th>
                <th data-field="themeStr" data-align="">论文主题</th>
                <th data-field="paperStatus" data-align="" data-formatter="paperStatusDetail">论文状态</th>
                <th data-field="averageScore" data-align="" data-formatter="scoreDetail">平均分(点击查看得分情况,三个专家评分后显示平均分)</th>
                <th data-field="isEmailPost" data-align="">是否通知</th>
                <th data-field="isComment" data-align="" data-formatter="commentDetail">是否填写问卷（点击填写问卷）</th>
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
            pageNo: params.pageNumber,
            userId: $('#user_id').val()
        };
    }

    function look(value, row, index) {
        var content = [];
        content.push('<a href= '+value +' ');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }

    function paperStatusDetail(value, row, index) {
        var content = [];

        if (value == 0) {
            content.push("<%=PaperStatusConst.PAPERS0%>");
        } else if (value == 1) {
            content.push("<%=PaperStatusConst.PAPERS1%>");
        } else if (value == 2) {
            content.push("<%=PaperStatusConst.PAPERS2%>");
        } else if (value == 3) {
            content.push("<%=PaperStatusConst.PAPERS3%>");
        } else if (value == 4) {
            content.push("<%=PaperStatusConst.PAPERS4%>");
        } else if (value == 5) {
            content.push("<%=PaperStatusConst.PAPERS5%>");
        } else if (value == 6) {
            content.push("<%=PaperStatusConst.PAPERS6%>");
        }
        var a = content.join('');
        return a;
    }

    function paperReviewerList(value, row, index) {
        var content = [];
        content.push('<a href="${reviewerPaperUrl}' + row.userId + '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }

    function scoreDetail(value, row, index) {
        var content = [];
        content.push('<a href="${paperCommentUrl}' + row.paperId + '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }

    function commentDetail(value, row, index) {
        var content = [];
        if (value == "未填写") {
            content.push('<a href="${emptyCommentUrl}' + row.paperId + '"');
            content.push(' target="_blank" ');
            content.push('>');
            content.push(value);
            content.push('</a>');
        }else{
            content.push(value);
        }
        var a = content.join('');
        return a;
    }

    function authorDetail(value, row, index) {
        var content = [];
        if (row.isShowName) {
            content.push('<a href="${authorDetailUrl}' + row.userId + '"');
            content.push(' target="_blank" ');
            content.push('>');
            content.push(value);
            content.push('</a>');
        } else {
            content.push('<a href="javascript:;"  ');
            content.push('onclick="error();return false;" ');
            content.push('>');
            content.push(value);
            content.push('</a>');
        }
        var a = content.join('');
        return a;
    }

    function error() {
        $.scojs_message("匿名不能查看作者信息", $.scojs_message.TYPE_ERROR);
    }
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
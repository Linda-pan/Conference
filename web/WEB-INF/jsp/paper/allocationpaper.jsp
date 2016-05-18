<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/13
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>未完成分配任务论文列表</h2>
        </div>

        <table id="tb" data-url="${ctx}/paper/paperlist" data-toggle="table"
               data-toolbar="#custem-toolbar" data-show-refresh="true"
               data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
               data-query-params="queryParams">
            <thead>
            <tr>
                <th data-field="paperId" data-align="">ID</th>
                <th data-field="paperName" data-align="">论文名</th>
                <th data-field="theme1" data-align="" data-formatter="select1">主题一</th>
                <th data-field="theme2" data-align="" data-formatter="select2">主题二</th>
                <th data-field="reviewernum" data-align="">已分配审阅者数量</th>
                <th data-field="createTime" data-align="">上传时间</th>
            </tr>
            </thead>
        </table>
    </div>
    <input type="hidden" id="themeId">
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

    function select1(value, row, index) {
        var content = [];
        content.push('<a href="${alloctionReviewerUrl}?themeId=' + row.theme1Id + '&paperId=' + row.paperId + '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }
    function select2(value, row, index) {
        var content = [];
        content.push('<a href="${alloctionReviewerUrl}?themeId=' + row.theme2Id + '&paperId=' + row.paperId + '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }


</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/11
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>论文得分列表</h2>
        </div>
        <input type="hidden" id="paperId" name="paperId" value="${paperId}"/>
        <div class="center-align">
            <input type="text"  id="paperName" name="papername" value="${paperName}"/>
        </div>

        <table id="tb" data-url="${ctx}/comment/list" data-toggle="table"
               data-toolbar="#custem-toolbar" data-show-refresh="true"
               data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
               data-query-params="queryParams">

            <thead>
            <tr>
                <th data-field="reviewerId" data-align="" data-formatter="reviewerDetail">ID(点击查看审稿人员详细信息)</th>
                <th data-field="Q1" data-align="">第一题</th>
                <th data-field="Q2" data-align="">第二题</th>
                <th data-field="Q3" data-align="">第三题</th>
                <th data-field="Q4" data-align="">第四题</th>
                <th data-field="Q5" data-align="">第五题</th>
                <th data-field="shortComment" data-align="">短评</th>
                <th data-field="score" data-align="">得分</th>
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
            paperId: $('#paperId').val()
        };
    }

    function reviewerDetail(value, row, index) {
        var content = [];
        content.push('<a href="${reviewerDetailUrl}' + row.reviewerId+ '"');
        content.push(' target="_blank" ');
        content.push('>');
        content.push(value);
        content.push('</a>');
        var a = content.join('');
        return a;
    }
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
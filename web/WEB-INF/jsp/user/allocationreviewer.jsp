<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/13
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>分配论文（一次只能分配一个审稿人）</h2>
        </div>
        <div>
            <label style=" width:150px;">主题：</label>
            <input type="hidden" id="themeId" value="${themeId}">
            <input type="text" id="themeName" value="${theme}">
        </div>
        <div class="row-fluid">
            <table id="tb1" data-url="${ctx}/paper/reviewerlist" data-toggle="table"
                   data-toolbar="#custem-toolbar" data-show-refresh="true"
                   data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
                   data-query-params="queryParams">
                <thead>
                <tr>
                    <th data-field="userId" data-align="">ID</th>
                    <th data-field="trueName" data-align="">姓名</th>
                    <th data-field="theme1" data-align="">主题一</th>
                    <th data-field="theme2" data-align="">主题二</th>
                    <th data-field="theme1" data-align="">主题三</th>
                    <th data-field="theme2" data-align="">主题四</th>
                    <th data-field="theme1" data-align="">主题五</th>
                    <th data-field="reviewPaperNum" data-align="">所有分配论文数量</th>
                    <th data-field="doReviewPaperNum" data-align="">已完成论文数量</th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="mymodal-form clearfix">
            <form id="detail_id" method="post" action="${ctx}/paper/reviewersave">
                <ul>
                    <li>
                        <input type="hidden" id="paperId" name="paperId" value="${paperId}">
                        <label style=" width:150px;">选择审阅者：</label>
                    </li>
                </ul>
                <select class="form-control-horizontal"  id="status_id" name="userId">
                    <c:forEach items="${userList}" var="type">
                        <option value="${type.userId}">${type.trueName}</option>
                    </c:forEach>
                </select>
                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                        <input type="submit"
                               value="确定"
                               class="btn btn-primary">
                    </li>
                </ul>
            </form>
        </div>
    </div>

    <script type="text/javascript">


        function queryParams(params) {
            return {
                pageSize: params.pageSize,
                pageNo: params.pageNumber,
                themeId: $('#themeId').val()
            };
        }

    </script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
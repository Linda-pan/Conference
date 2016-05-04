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
            <h2>审核员后台列表</h2>
        </div>
        <table id="tb" data-url="${ctx}/user/reviewer/list" data-toggle="table"
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
                <th data-field="roleId" data-align="" data-formatter="roleDetail">角色</th>
                <th data-field="reviewPaperNum" data-align="">审查论文数目(点击查看论文详细内容)</th>

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

    function roleDetail(value, row, index) {
        var content = [];
        if(value==0){
            content.push("<%=UserRoleConst.AUTHOR0%>");
        }else if(value==1){
            content.push("<%=UserRoleConst.ADMIN1%>");
        }else{
            content.push("<%=UserRoleConst.REVIEWER2%>");
        }
        var a = content.join('');
        return a;
    }
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>


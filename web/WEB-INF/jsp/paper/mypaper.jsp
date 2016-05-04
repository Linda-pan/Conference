<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/4
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="tit">
            <h2>所有论文列表</h2>
        </div>
        <table id="tb" data-url="${ctx}/paper/list" data-toggle="table"
               data-toolbar="#custem-toolbar" data-show-refresh="true"
               data-side-pagination="server" data-pagination="true" data-page-size="50" data-page-list="[50, 200]"
               data-query-params="queryParams">
            <thead>
            <tr>
                <th data-field="paperId" data-align="">ID(点击查看分配的审稿人员)</th>
                <th data-field="paperName" data-align="" >论文名</th>
                <th data-field="paperContent" data-align="">论文内容</th>
                <th data-field="" data-align="">论文主题</th>
                <th data-field="paperStatus" data-align="" data-formatter="paperStatusDetail">论文状态</th>
                <th data-field="averageScore" data-align="">平均分(点击查看得分情况)</th>
                <th data-field="isEmailPost" data-align="">是否通知</th>

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

    function paperStatusDetail(value, row, index) {
        var content = [];

        if(value==1){
            content.push("1 正在审核中");
        }else if(value==0){
            content.push("0 正在分配专家");
        }else if(value==2){
            content.push("2 等待上传缴费图片");
        }else if(value==3){
            content.push("3 正在验证缴费图片");
        } else if(value==4){
            content.push("4 缴费验证成功");
        } else if(value==5){
            content.push("5 未通过");
        }
        var a = content.join('');
        return a;
    }
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
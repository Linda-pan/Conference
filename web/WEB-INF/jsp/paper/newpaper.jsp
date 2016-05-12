<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/11
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>


<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
        <div class="tit">
            <h2>用户详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <form id="frm_id" method="post" action="">

                <input type="hidden" id="user_id" name="userId" value="${userId}"/>

                <ul>
                    <li>
                        <label style=" width:150px;">论文名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="username_id" name="username">
                    </li>
                </ul>
                <form method="post" action="/upload/doUpload2" enctype="multipart/form-data">
                    <input type="file" name="file1"/>
                    <input type="submit"/>
                </form>

                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                            <input type="button"
                                   value="确定"
                                   class="btn btn-primary" onclick="saveDetail()">

                    </li>
                </ul>
            </form>
        </div>
        <div class="panel-footer">
        </div>
    </div>
</div>
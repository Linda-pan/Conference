<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/4
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <h1 align="center">会议详情</h1>
        <div class="row-fluid">
            <div style="width:100%;margin: 0 auto;">

                <form class="form-horizontal" id="frm_id" action="${ctx }/conference/change" method="post" role="form"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label style="width:15%;"  class="col-sm-2 control-label">会议名称</label>

                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 90%;" class="form-control" placeholder="" name="fromName"
                                   validate="{required:true}" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">会议概述</label>

                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 90%;" class="form-control" placeholder="" name="conferenceIntro"
                                      id="conferenceIntro_id" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/common/footer.jsp" %>
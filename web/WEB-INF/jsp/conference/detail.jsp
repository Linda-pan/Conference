<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/9
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div align="center">
            <h2>会议详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <div style="width:100%;margin: 0 auto;">
                <form class="form-horizontal" id="frm_id">
                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">会议名称</label>
                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 90%;" class="form-input" placeholder="" id="conferenceId"
                                   name="conferenceName"
                                   validate="{required:true}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">会议概述</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 90%;" class="form-control" placeholder="" name="conferenceIntro"
                                      id="conferenceIntro_id" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">开始时间</label>
                        <div class='input-group date'>
                            <input type='text' style="width:130px;" class="form-control-horizontal" id="start_date_id"
                                   name="startTime" readonly oninput="checkTime();"/>
		                    <span style="width:15%;" class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
	                    	</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">结束时间</label>
                        <div class='input-group date'>
                            <input type='text' style="width:130px;" class="form-control-horizontal" id="end_date_id"
                                   name="endTime" readonly oninput="checkTime();"/>
		                    <span style="width:15%;" class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
	                    	</span>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <script>
        $(function () {
            $.ajax({
                url: '${ctx}/conference/getdetail',
                type: 'post',
                datatype: 'json',
                success: function (result) {
                    result = JSON.parse(result);
                    if (result != null) {
                        if (result.status == 'true') {
                            var conference = result.data;
                            if (conference != null) {
                                $('#conferenceId').val(conference.conferenceName);
                                $('#conferenceIntro_id').val(conference.conferenceIntro);
                                $('#start_date_id').val(conference.startTime);
                                $('#end_date_id').val(conference.endTime);
                            }
                        } else {
                            $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                        }
                    }
                },
                error: function (result) {
                    requestError(result);
                }
            });

        });
    </script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
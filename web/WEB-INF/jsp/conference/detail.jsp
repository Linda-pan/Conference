<%@ page import="com.elin4it.ssm.constant.ConferenceStatusConst" %><%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/9
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>


<div class="container-fluid">
    <div class="row-fluid">
        <div align="center">
            <h2>会议详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <div style="width:100%;margin: 0 auto;">
                <form class="form-horizontal" id="frm_id">
                    <input type="hidden" id="conferenceId">
                    <input type="hidden" id="conference_status">
                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">会议名称</label>
                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 90%;" class="form-input" placeholder="" id="conferenceName"
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
                        <label style="width:15%;" class="col-sm-2 control-label">会议状态</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 90%;" class="form-control" placeholder="" name="conferenceStatus"
                                      id="conference_status1" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">开始时间</label>
                        <div class='input-group date'>
                            <input type='text' style="width:130px;" class="form-control-horizontal" id="start_date_id"
                                   name="startTime" readonly/>
		                    <span style="width:15%;" class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
	                    	</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">结束时间</label>
                        <div class='input-group date'>
                            <input type='text' style="width:130px;" class="form-control-horizontal" id="end_date_id"
                                   name="endTime" readonly/>
		                    <span style="width:15%;" class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
	                    	</span>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<c:if test="${statu==1}">
    <div>
        <div align="center">
            <h2>跟进会议状态</h2>
        </div>
        <div align="center">
            <form>
                <div style="width:15%;">
                    <select class="form-control-horizontal" name="status" id="status_id">
                        <c:forEach items="${StatusMap}" var="type">
                            <option value="${type.key}">${type.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <input class="btn btn-primary" type="button" id="searchBtn" value="确定" onclick="changeStatus()">
            </form>
        </div>
    </div>
</c:if>

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
                            $('#conferenceId').val(conference.conferenceId);
                            $('#conferenceName').val(conference.conferenceName);
                            $('#conferenceIntro_id').val(conference.conferenceIntro);
                            $('#start_date_id').val(conference.startTime);
                            $('#end_date_id').val(conference.endTime);
                            $('#conference_status').val(conference.conferenceStatus);
                            var n = parseInt(conference.conferenceStatus);
                            var status = "";
                            if (n == 0) {
                                status = "<%=ConferenceStatusConst.S0%>";
                            } else if (n == 1) {
                                status = "<%=ConferenceStatusConst.S1%>";
                            } else if (n == 2) {
                                status = "<%=ConferenceStatusConst.S2%>";
                            } else if (n == 3) {
                                status = "<%=ConferenceStatusConst.S3%>";
                            } else if (n == 4) {
                                status = "<%=ConferenceStatusConst.S4%>";
                            }
                            $('#conference_status1').val(status);
                        }
                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
            }
            ,
            error: function (result) {
                requestError(result);
            }
        });

    });

    function changeStatus() {
        var conferenceId = $('#conferenceId').val();
        var c_status = parseInt($('#conference_status').val());
        var now_status = parseInt($('#status_id').val());
        if (c_status >= now_status) {
            $.scojs_message("更改无效，应选择大于当前进度的状态");
            return false;
        }

        $.ajax({
            url: '${ctx}/conferencestatus/savestatus',
            type: 'post',
            datatype: 'json',
            data: {
                conferenceId: conferenceId,
                conferenceStatus: now_status
            },
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        $.scojs_message("保存成功", $.scojs_message.TYPE_OK);
                        return true;
                    }
                    else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
            },
            error: function (result) {
                requestError(result);
            }
        });

    }
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
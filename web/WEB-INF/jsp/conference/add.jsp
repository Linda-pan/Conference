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
        <div align="center">
            <h2>会议详情</h2>
        </div>
        <div class="mymodal-form clearfix">
            <div style="width:100%;margin: 0 auto;">
                <form class="form-horizontal" id="frm_id" action="${ctx }/conference/add" method="post">
                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">会议名称</label>
                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 90%;" class="form-input" placeholder=""
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

                    <input class="btn btn-primary" type="button" value="保存">
                </form>

            </div>
        </div>
    </div>


    <script type="text/javascript">
        $('.date').datetimepicker({
            language: 'zh-CN',
            pickTime: false
        });

        function checkTime() {
            var startTime = $('#start_date_id').val();
            var endTime = $('#end_date_id').val();
            if (startTime > endTime) {
                $.scojs_message("开始时间不能大于结束时间");
                return false;
            }
            return true;
        }
    </script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
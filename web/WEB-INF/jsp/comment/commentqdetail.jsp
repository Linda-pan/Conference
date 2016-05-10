<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/10
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div align="center" >
            <h2>问卷填写</h2>
        </div>
        <div class="mymodal-form clearfix">
            <div style="width:100%;margin: 0 auto;">
                <form class="form-horizontal" id="frm_id" action="${ctx}/comment/save" method="post">

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">问卷打分规则</label>
                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 70%;" class="form-input"
                                   placeholder="每道题有ABCD 4个level，分别为100分，75分，50分，25分，通过论文的表现给其打分" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第一题:</label>
                        <div class="col-sm-6" style="width:85%;">
                           <textarea style="width: 70%;" class="form-control" placeholder=""
                                     id="qq1" readonly> </textarea>
                        </div>
                        <label style="width:15%;" class="col-sm-2 control-label">选择:</label>
                        <select id="q1" name="q1">
                            <c:forEach items="${status}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第二题:</label>
                        <div class="col-sm-6" style="width:85%;">
                             <textarea style="width: 70%;" class="form-control" placeholder=""
                                       id="qq2" readonly> </textarea>
                        </div>
                        <label style="width:15%;" class="col-sm-2 control-label">选择:</label>
                        <div>
                            <select id="q2" name="q2">
                                <c:forEach items="${status}" var="type">
                                    <option value="${type.key}">${type.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第三题:</label>
                        <div class="col-sm-6" style="width:85%;">
                        <textarea style="width: 70%;" class="form-control" placeholder=""
                                  id="qq3" readonly> </textarea>
                        </div>
                        <label style="width:15%;" class="col-sm-2 control-label">选择:</label>
                        <select id="q3" name="q3">
                            <c:forEach items="${status}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第四题:</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 70%;" class="form-control" placeholder=""
                                      id="qq4" readonly> </textarea>
                        </div>
                        <label style="width:15%;" class="col-sm-2 control-label">选择:</label>
                        <select id="q4" name="q4">
                            <c:forEach items="${status}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第五题:</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 70%;" class="form-control" placeholder=""
                                      id="qq5" readonly> </textarea>
                        </div>
                        <label style="width:15%;" class="col-sm-2 control-label">选择:</label>
                        <select id="q5" name="q5">
                            <c:forEach items="${status}" var="type">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">短评:</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 70%;" class="form-control" placeholder=""
                                      name="shortComment" id="shortComment"> </textarea>
                        </div>
                    </div>
                    <div>
                        <input type="hidden" id="themeId" name="themeId"/>
                        <input type="hidden" id="paperId" name="paperId" value="${paperId}"/>
                    </div>
                    <div>
                        <label style="width:15%;" class="col-sm-2 control-label"></label>
                        <input class="btn btn-primary" type="submit" value="保存">
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        var paperId = $('#paperId').val();
        $.ajax({
            url: '${ctx}/commentq/detail',
            type: 'post',
            datatype: 'json',
            data: {paperId: paperId},
            success: function (result) {
                result = JSON.parse(result);
                if (result != null) {
                    if (result.status == 'true') {
                        var comment = result.data;
                        if (comment != null) {
                            $('#qq1').val(comment.q1);
                            $('#qq2').val(comment.q2);
                            $('#qq3').val(comment.q3);
                            $('#qq4').val(comment.q4);
                            $('#qq5').val(comment.q5);

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

        $('#frm_id').validate({
            onsubmit: true,
            onfocusout: false,
            focusCleanup: true,
            focusInvalid: false,
            onkeyup: false,//输入时不验证
            rules: {
                "shortComment": {
                    required: true,
                    maxByteLength: 100,
                    remote: {
                        url: '${ctx}/comment/detail',
                        type: 'post',
                        datatype: 'json',
                        data: {paperId:paperId},
                        dataFilter: function (result) {
                            result = JSON.parse(result);
                            if (result != null && result.status == 'true') {
                                var data = result.data;
                                if(data!=null){
                                    return false;
                                }

                            } else {
                                $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);

                            }
                            return true;
                        }
                    }
                }
            },
            messages: {
                "shortComment": {
                    remote: "已经填写过该论文的调查问卷"
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });

    });

 /*   function check() {
        var q1 = $('#q1').val();
        var q2 = $('#q2').val();
        var q3 = $('#q3').val();
        var q4 = $('#q4').val();
        var q5 = $('#q5').val();

        if (q1.length == 0 || q2.length == 0 || q3.length == 0 || q4.length == 0 || q5.length == 0) {
            $.scojs_message("5道问题必填");
            return false;
        }

        $.scojs_message("保存成功", $.scojs_message.TYPE_OK);
        return true;
    }*/

    function removeError(id) {
        $('#' + id).css('display', 'none');
    }

    function addError(id, message) {
        $('#' + id).text(message);
        $('#' + id).css('display', 'block');
    }

</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
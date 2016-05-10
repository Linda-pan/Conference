<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/10
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div align="center">
            <h2>问卷编辑</h2>
        </div>
        <div class="mymodal-form clearfix">
            <div style="width:100%;margin: 0 auto;">
                <form class="form-horizontal" id="frm_id" action="${ctx }/commentq/save" method="post">
                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">问卷编辑要求</label>
                        <div class="col-sm-6" style="width:85%;">
                            <input type="text" style="width: 90%;" class="form-input"
                                   name="conferenceName"
                                   placeholder="可以添加5道问题，每道题有ABCD 4个level，分别为100分，75分，50分，25分，每篇论文的综合得分在75分及以上可以通过"
                                  readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第一题</label>
                        <div class="col-sm-6" style="width:85%;">
                           <textarea style="width: 90%;" class="form-control" placeholder="" name="q1"
                                     id="q1" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第二题</label>
                        <div class="col-sm-6" style="width:85%;">
                             <textarea style="width: 90%;" class="form-control" placeholder="" name="q2"
                                       id="q2" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第三题</label>
                        <div class="col-sm-6" style="width:85%;">
                        <textarea style="width: 90%;" class="form-control" placeholder="" name="q3"
                                  id="q3" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第四题</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 90%;" class="form-control" placeholder="" name="q4"
                                      id="q4" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="width:15%;" class="col-sm-2 control-label">第五题</label>
                        <div class="col-sm-6" style="width:85%;">
                            <textarea style="width: 90%;" class="form-control" placeholder="" name="q5"
                                      id="q5" validate="{required:true,maxByteLength:100}"> </textarea>
                        </div>
                    </div>

                    <div>
                        <input type="hidden" id="themeId" name="themeId"/>
                    </div>
                    <div>
                        <label style="width:15%;" class="col-sm-2 control-label"></label>
                        <input class="btn btn-primary" type="submit" value="保存" onclick="return check()">
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    function check() {
        var q1 = $('#q1').val();
        var q2 = $('#q2').val();
        var q3 = $('#q3').val();
        var q4 = $('#q4').val();
        var q5 = $('#q5').val();

        if (q1.length==0||q2.length==0||q3.length==0||q4.length==0||q5.length==0) {
            $.scojs_message("5道问题必填");
            return false;
        }
        $.scojs_message("保存成功");
        return true;
    }


</script>

<%@ include file="/WEB-INF/common/footer.jsp" %>
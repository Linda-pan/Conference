<%@ page import="com.elin4it.ssm.utils.WebUtil" %><%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/11
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<%
    String name = WebUtil.getAppAdminRights().getUsername();
%>

<div class="container-fluid">
    <div class="search-form">
        <form class="form-horizontal">
            <div class="search-condition" style="width: 600px;">
                <input class="form-control" type="text" style="width: 600px;" value="<%=name%>"
                       id="image_url_id" name="ImageUrl" readonly>
            </div>
            <input type="button" value="上传" class="btn btn-primary" onclick="uploadImageDialog()">
        </form>
    </div>
    <br>
    <br>

    <div class="container-fluid" id="user_detail_id">
        <div class="row-fluid">
            <div class="tit">
                <h2>添加论文</h2>
            </div>
            <div class="mymodal-form clearfix">
                <form id="frm_id" method="post" action="">

                    <input type="hidden" id="user_id" name="userId" value="${userId}"/>
                    <ul>
                        <li><label style="width: 200px;">论文名：</label>
                            <input class="form-input" type="text" style="width: 400px;"
                                   id="name_id">&nbsp;&nbsp;
                        </li>
                    </ul>
                    <ul>
                        <li><label style="width: 200px;">论文主题1(必选项)：</label>
                            <select id="type1" name="first_theme_id" style="width:400px;">
                                <c:forEach items="${type}" var="type">
                                    <option value="${type.themeId }">${type.theme}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                    <ul>
                        <li><label style="width: 200px;">论文主题2(可选项)：</label>
                            <select id="type2" name="second_theme_id" style="width:400px;">
                                <c:forEach items="${type}" var="type">
                                    <option value="${type.themeId }">${type.theme}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                    <ul>
                        <li><label style="width: 200px;">论文url：</label>
                            <input class="form-input" type="text" style="width: 400px;" value="<%=name%>"
                                   id="paper_content" readonly>&nbsp;&nbsp;
                        </li>
                    </ul>

                    <c:if test="${cstatu==0}">
                        <ul>
                            <li>
                                <label>提醒：当前状态不能投稿</label>
                            </li>
                        </ul>
                    </c:if>

                    <c:if test="${cstatu==1}">
                        <ul>
                            <li>
                                <label style="width:40%;"></label>
                                <input type="button" value="确定" class="btn btn-primary" onclick="save()">
                            </li>
                        </ul>
                    </c:if>
                </form>
            </div>
            <div class="panel-footer">
            </div>
        </div>
    </div>

    <div id="upload_image_id" class="mymodal" style="display:none;">
        <div class="tit">
            <h2>上传论文</h2>
            <a href="javascript:;" class="closes" id="upload_image_close">关闭</a>
        </div>
        <div class="mymodal-form clearfix">
            <form id="frm1_id" method="post" action="${ctx}/user/save/paperurl" enctype="multipart/form-data">
                <input type="hidden" id="userid" name="userId" value="${userId}"/>
                <ul>
                    <li>
                        <label style="width:40%;"></label>
                        <input type="file" id="upload_file_id" name="file" accept="application/pdf"
                               onchange="img_upload_handle(this.files)">
                    </li>
                </ul>
                <ul>
                    <li style="margin-top: 10px;">
                        <label style="width:40%;"></label>
                        <input type="submit" value="确定" id="upload_id" class="btn btn-primary"
                               onclick="return uploadSubmit()">
                    </li>
                </ul>
            </form>
        </div>
    </div>

    <div id="upload_image_mask" class="mask" style="display:none;">
        <div class="mask-tips"></div>
    </div>

    <script type="text/javascript">
        $(function () {

            if (${status==true}) {
                $.scojs_message('${message}', $.scojs_message.TYPE_OK);
            } else if (${status==false}) {
                $.scojs_message('${message}', $.scojs_message.TYPE_ERROR);
            }

            $('#upload_image_close').on('click', function () {
                closeModal('upload_image_id', 'upload_image_mask');
            });
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

                            }
                        }
                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
                ,
                error: function (result) {
                    requestError(result);
                }
            });

        });

        function uploadImageDialog() {

            $("#upload_file_id").attr({
                "accept": 'application/pdf',
                "onchange": "img_upload_handle(this.files)"
            });

            showModal('upload_image_id', 'upload_image_mask');
        }

        function img_upload_handle(files) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                var error = false;

                /* 文件格式限制 */
                if ($.inArray(file.type, ["application/pdf"]) < 0) {
                    alert("请使用pdf格式 ");
                    error = true;
                } else if (error) {
                    $("#upload_file_id").val("");
                    return false;
                }
            }
        }

        function uploadSubmit() {
            var file = $('#upload_file_id')[0].files[0];
            if (file == null) {
                $.scojs_message("请选择上传的文件", $.scojs_message.TYPE_ERROR);
                return false;
            }
            // $('#imply_id').val("[正在上传, 请稍候...]");
            //  uploadFile(file);
            return true;
        }

        /* function uploadFile(file) {
         $('#frm1_id').validate({
         onsubmit: true,
         onfocusout: false,
         focusCleanup: true,
         focusInvalid: false,
         onkeyup: false,//输入时不验证
         rules: {
         "upload_file_id": {
         required: true
         }
         },
         messages: {
         "upload_file_id": {
         remote: "必有"
         }
         }
         ,
         submitHandler: function (form) {
         form.submit();
         return false;
         }
         });*/

        function save() {
            $('#frm_id').validate({
                onsubmit: true,
                onfocusout: false,
                focusCleanup: true,
                focusInvalid: false,
                onkeyup: false,//输入时不验证
                rules: {
                    "first_theme_id": {
                        required: true
                    }
                },
                messages: {
                    "first_theme_id": {
                        remote: "必填"
                    }
                }
                ,
                submitHandler: function (form) {
                    form.submit();
                    return false;
                }
            });

            $.ajax({
                url: '${ctx}/user/save/paper',
                type: 'post',
                datatype: 'json',
                data: {
                    paperContent: $('#paper_content').val(),
                    paperName: $('#name_id').val(),
                    type1: $('#type1').val(),
                    type2: $('#type2').val()
                },
                success: function (result) {
                    result = JSON.parse(result);
                    if (result != null && result.status == 'true') {
                        $.scojs_message(result.message, $.scojs_message.TYPE_OK);

                    } else {
                        $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                    }
                }
                ,
                error: function (result) {
                    requestError(result);
                }
            });


        }

    </script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
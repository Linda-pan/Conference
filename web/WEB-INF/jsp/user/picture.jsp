<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/16
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>


<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
        <div class="tit">
            <h2>上传缴费图片</h2>
        </div>
        <div class="mymodal-form clearfix">
            <form id="frm_id" method="post" action="">

                <input type="hidden" id="user_id" name="userId" value="${userId}"/>

                <ul>
                    <li><label style="width: 120px;">缴费图片url：</label>
                        <input class="form-input" type="text" style="width: 400px;"
                               id="imply_id" readonly>&nbsp;&nbsp;
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width: 120px;">图片：</label>
                        <img class="form-input" style="width: 400px;" src=""
                             id="image1" name="pic" readonly/>&nbsp;&nbsp;
                    </li>
                </ul>
                <c:if test="${statu==1 && cstatu==0}">
                <ul>
                    <li>
                        <label style="width:40%;"></label>
                        <input type="button" value="上传" class="btn btn-primary" onclick="uploadImageDialog()">
                    </li>
                </ul>
            </c:if>

                <c:if test="${cstatu==1}">
                    <ul>
                        <li>
                            <label style="width: 120px;">提醒：</label>
                            <input class="form-input" type="text" style="width: 400px;"
                                   value="申请会议进入资格已经结束，停止审核缴费单" >
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
        <h2>上传图片</h2>
        <a href="javascript:;" class="closes" id="upload_image_close">关闭</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="frm1_id" method="post" action="${ctx}/user/save/pic" enctype="multipart/form-data">
            <input type="hidden" id="userid" name="userId" value="${userId}"/>
            <ul>
                <li>
                    <label style="width:40%;"></label>
                    <input type="file" id="upload_file_id" name=file accept="image/jpeg, image/png, image/bmp"
                           onchange="img_upload_handle(this.files)">
                </li>
            </ul>
            <ul>
                <li style="margin-top: 10px;">
                    <label style="width:40%;"></label>
                    <input type="submit" value="确定" id="upload_id" class="btn btn-primary">
                </li>
            </ul>
        </form>
    </div>
</div>

<div id="upload_image_mask" class="mask" style="display:none;">
    <div class="mask-tips"></div>
</div>

<script>
    $(function () {
        if (${pic==""}) {
            $('#imply_id').val("当且仅当有论文通过时才可上传缴费图");
        } else {
            $('#imply_id').val("${pic}");
            $('#image1').attr("src","${pic}");
        }

        $('#upload_image_close').on('click', function () {
            closeModal('upload_image_id', 'upload_image_mask');
        })
    });
    var img_fileSize_limit = 100;
    function uploadImageDialog() {

        $("#upload_file_note").html("请使用 <b>小于" + img_fileSize_limit + "KB</b> <b>jpg/png/bmp</b> 格式图片");

        $("#upload_file_id").attr({
            "accept": 'image/jpeg, image/png, image/bmp',
            "onchange": "img_upload_handle(this.files)"
        });

        showModal('upload_image_id', 'upload_image_mask');
    }

    function img_upload_handle(files) {
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var error = false;

            /* 文件格式限制 */
            if ($.inArray(file.type, ["image/jpeg", "image/png", "image/bmp"]) < 0) {
                alert("请使用常见图片格式 jpg png bmp");
                error = true;
            } else
            /* 大小限制 */
            if (file.size / 1024 > img_fileSize_limit) {
                alert("请使用小于 " + img_fileSize_limit + "KB 的图片以保证用户体验");
                error = true;
            }
            if (error) {
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
        $('#imply_id').val("[正在上传, 请稍候...]");
        uploadFile(file);
        closeModal('upload_image_id', 'upload_image_mask');
        return true;
    }

    function uploadFile(file) {
        $('#frm1_id').validate({
            submitHandler: function (form) {
                form.submit();
                return false;
            }
        });
        $('#imply_id').val(file.name);
        $('#image1').val(file.name);
        $("#upload_file_id").val("");

    }
</script>
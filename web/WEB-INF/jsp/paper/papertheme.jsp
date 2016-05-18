<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/11
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="search-form">
            <form class="form-horizontal">
                <input class="btn btn-primary" type="button" id="addOneLevelBtn"
                       value="创建主题">
            </form>
        </div>
        <table id="tb" width="25%" border="2">
            <thead>
            <tr>
                <td>分类</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${themeMap}" var="type">
                <tr>
                    <td>
                        <a href="javascript:;" onclick="detail('${type.themeId}',' ${type.theme}')">
                                ${type.themeId}:${type.theme}
                        </a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="detail_id" class="mymodal" style="display: none;">
    <div class="tit">
        <h2>修改主题</h2>
        <a href="javascript:;" class="closes" id="detail_close">取消</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="one_detail_id" action="${ctx }/allpapertheme/change" method="post">
            <input type="hidden" name="id" id="id1">
            <ul>
                <li><label style="width: 120px;">ID：</label> <input
                        class="form-input" type="text" style="width: 400px;"
                        id="themeId" name="themeId" readonly></li>
            </ul>
            <ul>
                <li><label style="width: 120px;">分类：</label>
                    <input class="form-input" type="text" style="width: 400px;"
                           id="theme" name="theme">&nbsp;&nbsp;
                </li>
            </ul>
            <ul>
                <li style="margin-top:10px;"><label style="width: 40%;"></label>
                    <input type="submit"
                           value="修改" class="btn btn-primary">
                </li>
            </ul>
        </form>
    </div>
</div>

<div id="add_id" class="mymodal" style="display: none;">
    <div class="tit">
        <h2>添加主题</h2>
        <a href="javascript:;" class="closes" id="add_close">取消</a>
    </div>
    <div class="mymodal-form clearfix">
        <form id="one_add_id" action="${ctx }/allpapertheme/save" method="post">
            <ul>
                <li><label style="width: 120px;">添加分类：</label>
                    <input class="form-input" type="text" style="width: 400px;"
                           id="theme1" name="theme">&nbsp;&nbsp;
                </li>
            </ul>
            <ul>
                <li style="margin-top:10px;"><label style="width: 40%;"></label>
                    <input type="submit"
                           value="提交" class="btn btn-primary">
                </li>
            </ul>
        </form>
    </div>
</div>

<div id="detail_mask" class="mask" style="display: none;">
    <div class="mask-tips"></div>
</div>

<div id="add_mask" class="mask" style="display: none;">
    <div class="mask-tips"></div>
</div>
<script>
    $('#addOneLevelBtn').on('click', function () {
        showModal('add_id', 'add_mask');
    });

    $('#add_close').on('click', function () {
        closeModal('add_id', 'add_mask');
    });

    $('#detail_close').on('click', function () {
        closeModal('detail_id', 'detail_mask');
    });

    function detail(id,theme) {
        $('#themeId').val(id);
        $('#theme').val(theme);
        showModal('detail_id', 'detail_mask');
    }

    $(function () {

        $('#one_detail_id').validate({
            onsubmit: true,
            onfocusout: false,
            focusCleanup: true,
            focusInvalid: false,
            onkeyup: false,//输入时不验证
            rules: {
                "theme": {
                    required: true,
                    maxByteLength: 100,
                    remote: {
                        url: '${ctx}/allpapertheme/get_by_name',
                        type: 'post',
                        datatype: 'json',
                        data: {
                            theme: theme
                        }
                    },
                    dataFilter: function (result) {
                        result = JSON.parse(result);
                        if (result != null && result.status == 'true') {
                            var data = result.data;
                            if (data != null) {
                                return false;
                            }
                        } else {
                            $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                        }
                        return true;
                    }
                }
            },
            messages: {
                "theme": {
                    remote: "该主题已经存在"
                }
            }
            ,
            submitHandler: function (form) {
                form.submit();
            }
        });

        $('#one_add_id').validate({
            onsubmit: true,
            onfocusout: false,
            focusCleanup: true,
            focusInvalid: false,
            onkeyup: false,//输入时不验证
            rules: {
                "theme": {
                    required: true,
                    maxByteLength: 100,
                    remote: {
                        url: '${ctx}/allpapertheme/get_by_name',
                        type: 'post',
                        datatype: 'json',
                        data: {
                            theme: theme
                        }
                    },
                    dataFilter: function (result) {
                        result = JSON.parse(result);
                        if (result != null && result.status == 'true') {
                            var data = result.data;
                            if (data != null) {
                                return false;
                            }
                        } else {
                            $.scojs_message(result.message, $.scojs_message.TYPE_ERROR);
                        }
                        return true;
                    }
                }
            },
            messages: {
                "theme": {
                    remote: "该主题已经存在"
                }
            }
            ,
            submitHandler: function (form) {
                form.submit();
            }
        });
    })
</script>
<%@ include file="/WEB-INF/common/footer.jsp" %>
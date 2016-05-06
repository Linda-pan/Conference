<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/6
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/head.jsp" %>
<%@ include file="/WEB-INF/common/submenu.jsp" %>

<div align="center">
    <h1>上传多个附件</h1>
    <form method="post" action="/upload/doUpload2" enctype="multipart/form-data">
        <input type="file" name="file1"/>
        <br/>
        <input type="file" name="file2"/>
        <input type="submit"/>
    </form>

</div>
<%@ include file="/WEB-INF/common/footer.jsp" %>
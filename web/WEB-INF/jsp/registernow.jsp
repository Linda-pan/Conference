<%--
  Created by IntelliJ IDEA.
  User: jpan
  Date: 2016/5/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<body>
<div class="container-fluid" id="user_detail_id">
    <div class="row-fluid">
         <div class="tit">
             <h2>注册</h2>
         </div>
        <div class="mymodal-form clearfix">
            <form  method="post" action="${ctx}/registersave" >
                <ul>
                    <li>
                        <label style=" width:150px;">用户名：</label>
                        <input type="text"  class="form-input" style="width:500px;" id="username_id" name="username" required>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">密码：</label>
                        <input type="password" class="form-input" style="width:500px;" id="password_id" name="password" required>
                    </li>
                </ul>
                <ul>
                    <li>
                        <label style="width:150px;">真实姓名：</label>
                        <input type="text" class="form-input" style="width:500px;" id="trueName_id" name="trueName" required>
                    </li>
                </ul>
                <ul>
                    <li><label style="width:150px;">邮箱（仅当验证成功才可登录）：</label>
                        <input type="text" class="form-input" style="width:500px;" id="email_id" name="email" required>
                    </li>
                </ul>
                <ul>
                    <li style="margin-top: 10px;"><label style="width: 40%;"></label>
                        <input type="submit"
                               value="确认"
                               class="btn btn-primary">

                    </li>
                </ul>
            </form>
        </div>
        <div class="panel-footer">
        </div>
    </div>
</div>

</body>

</html>

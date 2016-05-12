<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/head_beflogin.jsp" %>

<html>

<body>
<style>
	html {
		height: 100%;
	}
	body {
		width: 100%;
		height: 100%;
		font-family: "proxima-nova", Helvetica, Tahoma, Arial, sans-serif;
		color: #fff;

		background-size: 100% auto;
	}
	h1, h2, h3, h4, h5, h6 {
		font-family: "ff-din-web", "proxima-nova", Tahoma, Arial, sans-serif;
	}
	p {
		font-size: 14px;
		line-height: 22px;
	}
	p a {
		color: #fff;
		font-weight: bold;
		text-decoration: underline;
	}
	p a:hover {
		color: #fff;
		text-decoration: none;
	}
	.form-control {
		font-family: "proxima-nova", Helvetica, Tahoma, Arial, sans-serif;
		font-size: 16px;
	}
	#auth-wrapper {
		position: relative;
		width: 100%;
		height: 100%;
	}
	#auth {
		position: relative;
		top: 40%;
		-webkit-transform: translateY(-50%);
		-moz-transform: translateY(-50%);
		transform: translateY(-50%);
		padding: 30px;
		border: 1px solid #484848;

		border-radius: 6px;
		box-shadow: 1px 1px 6px rgba(0,0,0,0.2);
		/*max-width: 328px;*/
		max-width: 328px;
		margin: 0 auto;
		box-shadow: 0 2px 22px rgba(0, 0, 0, 0.4);
	}
	.form-group {
		margin-bottom: 10px;
	}
	.form-control {
		color: #515151;
		background: transparent;
		border: 1px solid rgba(255,255,255,.2);
	}
	.form-control:focus {
		border-color: #FED044;
		box-shadow: 0 0 8px rgba(254, 208, 68, .6);
	}
	#glyph {
		color: rgb(5,80,137);
		text-align: center;
		font-size: 24px;
		margin: 10px 0 34px;
	}
	#glyph + p, form > p {
		margin-bottom: 20px;
	}
	hr {
		border-top: 1px solid rgba(255,255,255,.1);
	}
	.btn {
		color: #eee;
		font-family: "futura-pt", Helvetica, Tahoma, Arial, sans-serif;
		font-weight: 600;
		padding: 6px 0;
		letter-spacing: 1px;
		background: transparent;
		text-transform: uppercase;
		text-decoration: none;
		transition: color 250ms ease-out;
	}
	.btn i {
		font-size: 16px;
		vertical-align: -1px;
		margin-right: 4px;
	}
	.btn-primary {
		color: #fff;
		background: rgb(5,80,137);
		border: 2px solid #fec600;
	}
	.btn-primary:hover, .btn-primary:focus, .btn-primary:active {
		color: #000;
		background: #fec600;
		border-color: #fec600;
		box-shadow: none;
		outline: none;
	}
	.btn-default {
		border: 1px solid rgba(255, 255, 255, 0.2);
	}
	.btn-default:hover, .btn-default:focus, .btn-default:active {
		color: #000;
		background: #fff;
		border-color: #fff;
		box-shadow: none;
		outline: none;
	}
	.btn + p {
		margin: 20px 0 0 0;
		color: #999;
		font-size: 12px;
		line-height: 18px;
		text-align: center;
	}
	.misc-links {
		margin: 0;
		font-size: 11px;
		text-transform: uppercase;
		color: #999;
	}
	.text-link {
		color: #fff;
		font-weight: normal;
		text-decoration: none;
		opacity: 0.6;
		transition: all 250ms ease-out;
	}
	.text-link:hover {
		color: #fff;
		text-decoration: none;
		opacity: 1;
	}
	.redirecting {
		margin: 36px 0 10px;
		font-size: 24px;
	}

	@media (max-width: 767px) {
		body {
			background-size: 180%;
		}
	}

	@media (max-width: 480px) {
		body {
			background-size: 380%;
		}
		#auth {
			border: none;
			border-radius: 0;
		}
	}
</style>
<div id="auth-wrapper">
	<div id="auth">
		<div id="glyph">
			<font color="#rgb(5,80,157)">会议系统</font>
		</div>
		<form method="post" action="<c:url value="/login" />">
			<font color="red"><c:out value="${error}" ></c:out></font>
			<div class="form-group">
				<div class="controls">
					<input type="text" class="form-control" id="auth_key" name="userName" value="linda" placeholder="用户名" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<div class="controls">
					<input type="password" class="form-control" id="password" name="password" value="123456" placeholder="密码" required>
				</div>
			</div>
			<button type="submit" class="btn btn-block btn-primary">登录</button>
		</form>
		<hr>
		<p class="text-center misc-links">忘记密码&nbsp;&bull;&nbsp;  <a href="http://localhost:8080/register" target="_blank" class="text-center misc-links">注册</a></p>
	</div>
</div>
</body>

</html>

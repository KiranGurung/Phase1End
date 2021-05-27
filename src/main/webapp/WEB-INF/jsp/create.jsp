<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:out value='${username}' />--%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Create User</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<div>
	<c:if test='${username != null}'>
	<h1>
		Hello ${username}
		<button onclick="document.forms['logoutForm'].submit()"
			class="btn btn-primary" style="float: right">Logout</button>
<!-- 		<br />
 -->		</h1>
	</c:if>
	<h3>
		Sign Up
		<c:if test='${username == null}'>
			<button onclick="document.forms['loginForm'].submit()"
				class="btn btn-primary" style="float: right">Login</button>
		</c:if>
	</h3>
	<form method="POST" action="/create" modelAttribute="user">

		<div class="form-group">
			<input name="username" type="text" class="form-control"
				placeholder="Username" autofocus="true" /> <br /> <input
				name="password" type="password" class="form-control"
				placeholder="Password" /> <span style="color: red">${errorMsg}</span>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				Up</button>
		</div>

	</form>

	<form id="loginForm" method="GET" action="/login"></form>
	<form id="logoutForm" method="POST" action="/logout"></form>

</div>
</html>

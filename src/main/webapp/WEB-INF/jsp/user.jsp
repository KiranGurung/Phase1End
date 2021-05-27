<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<%-- 	<jsp:include page="menu.jsp" />
 --%>
	<div>
		<h3 style="color: blue;">
			<button onclick="document.forms['logoutForm'].submit()"
				class="btn btn-lg btn-primary btn-block" style="float: right">Logout</button>
			Welcome
			<c:out value='${username}' />
		</h3>
		<br/>
		This is the Home Page!
		<form id="logoutForm" method="POST" action="/logout"></form>
		<jsp:include page="menu.jsp" />
	</div>
</body>
</html>
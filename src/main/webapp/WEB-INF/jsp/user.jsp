<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<h3 style="color: blue;">Hello <c:out value='${username}'/></h3>
</body>
</html>
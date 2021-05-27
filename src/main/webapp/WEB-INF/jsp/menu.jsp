<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<footer style="position:fixed; bottom: 0;">
	Want to create an account?
		<button onclick="document.forms['create'].submit()"
			class="btn btn-lg btn-primary btn-block">Create</button>
	</footer>

	<form id="create" method="GET" action="/create"></form>

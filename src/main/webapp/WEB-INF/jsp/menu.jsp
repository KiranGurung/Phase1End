<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
 <h2 style="color: blue;">
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>

    <form id="logoutForm" method="POST" action="${contextPath}/logout">
    </form>

</div>
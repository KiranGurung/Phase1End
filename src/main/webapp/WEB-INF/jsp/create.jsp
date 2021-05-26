<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<div>

    <form method="POST" action="${contextPath}/create" modelAttribute="user">
        <h2 class="form-heading">Sign Up</h2>

        <div class="form-group">
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/> <br/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span style=color:red>${errorMsg}</span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        </div>

    </form>

</div>
</html>

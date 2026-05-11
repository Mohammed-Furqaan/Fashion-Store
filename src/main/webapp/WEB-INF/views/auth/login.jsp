<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/login.css">

</head>

<body>

    <div class="login-container">

        <div class="login-left">

            <h1>Fashion Store</h1>

            <p>
                Welcome back! Login to continue shopping
                the latest fashion collections.
            </p>

        </div>

        <div class="login-right">

            <div class="login-box">

                <jsp:include page="/WEB-INF/views/partials/message.jsp" />

                <h2>Login</h2>

                <form action="${pageContext.request.contextPath}/login"
                      method="post"
                      id="loginForm">

                    <div class="input-group">

                        <label>Email</label>

                        <input type="email"
                               name="email"
                               placeholder="Enter your email"
                               required>

                    </div>

                    <div class="input-group">

                        <label>Password</label>

                        <input type="password"
                               name="password"
                               placeholder="Enter password"
                               required>

                    </div>

                    <button type="submit" class="login-btn">
                        Login
                    </button>

                </form>

                <p class="register-link">

                    Don't have an account?

                    <a href="${pageContext.request.contextPath}/register">
                        Register
                    </a>

                </p>

            </div>

        </div>

    </div>

<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>

</body>
</html>
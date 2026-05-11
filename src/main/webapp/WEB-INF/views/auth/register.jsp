<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/register.css">

</head>

<body>

    <div class="register-container">

        <div class="register-left">

            <h1>Fashion Store</h1>

            <p>
                Discover trendy fashion collections with
                premium quality and modern style.
            </p>

        </div>

        <div class="register-right">

            <div class="register-box">
            
            <jsp:include page="/WEB-INF/views/partials/message.jsp" />

                <h2>Create Account</h2>

                <form action="${pageContext.request.contextPath}/register"
                      method="post"
                      id="registerForm">

                    <div class="input-group">

                        <label>Full Name</label>

                        <input type="text"
                               name="fullName"
                               placeholder="Enter your full name"
                               required>

                    </div>

                    <div class="input-group">

                        <label>Email</label>

                        <input type="email"
                               name="email"
                               placeholder="Enter your email"
                               required>

                    </div>

                    <div class="input-group">

                        <label>Phone Number</label>

                        <input type="text"
                               name="phone"
                               placeholder="Enter your phone number"
                               required>

                    </div>

                    <div class="input-group">

                        <label>Password</label>

                        <input type="password"
                               name="password"
                               placeholder="Enter password"
                               required>

                    </div>

                    <div class="input-group">

                        <label>Confirm Password</label>

                        <input type="password"
                               name="confirmPassword"
                               placeholder="Confirm password"
                               required>

                    </div>

                    <button type="submit" class="register-btn">
                        Register
                    </button>

                </form>

                <p class="login-link">
                    Already have an account?

                    <a href="${pageContext.request.contextPath}/login">
                        Login
                    </a>
                </p>

            </div>

        </div>

    </div>

<script src="${pageContext.request.contextPath}/assets/js/register.js"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.fashionstore.model.User" %>

<%
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/home.css">

</head>

<body>

    <!-- Navbar -->

    <nav class="navbar">

        <div class="logo">
            Fashion Store
        </div>

        <ul class="nav-links">

    <li>
        <a href="${pageContext.request.contextPath}/home">
            Home
        </a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/products">
            Products
        </a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/cart">
            Cart
        </a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/orders">
            Orders
        </a>
    </li>

</ul>

        <div class="user-section">

            <span>
                Welcome,
                <%= user.getFullName() %>
            </span>

            <a href="${pageContext.request.contextPath}/logout"
               class="logout-btn">

               Logout

            </a>

        </div>

    </nav>

    <!-- Hero Section -->

    <section class="hero">

        <div class="hero-content">

            <h1>
                Discover The Latest Fashion Trends
            </h1>

            <p>
                Upgrade your wardrobe with premium quality
                fashion collections curated just for you.
            </p>

            <a href="#" class="shop-btn">
                Shop Now
            </a>

        </div>

    </section>

    <!-- Featured Section -->

    <section class="featured-section">

        <h2>Featured Collections</h2>

        <div class="featured-grid">

            <div class="featured-card">

                <img src="${pageContext.request.contextPath}/assets/images/men-fashion.jpg"
                     alt="Men Fashion">

                <h3>Men Collection</h3>

            </div>

            <div class="featured-card">

                <img src="${pageContext.request.contextPath}/assets/images/women-fashion.jpg"
                     alt="Women Fashion">

                <h3>Women Collection</h3>

            </div>

            <div class="featured-card">

                <img src="${pageContext.request.contextPath}/assets/images/accessories.jpg"
                     alt="Accessories">

                <h3>Accessories</h3>

            </div>

        </div>

    </section>

</body>
</html>
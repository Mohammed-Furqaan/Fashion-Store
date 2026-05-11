<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>

<%
    List<Product> products =
        (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/products.css">

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

        <li>
            <a href="${pageContext.request.contextPath}/logout">
                Logout
            </a>
        </li>

    </ul>

</nav>

    <!-- Products Section -->

    <section class="products-section">

        <h1>Fashion Collections</h1>

        <div class="products-grid">

            <%
                if(products != null && !products.isEmpty()) {

                    for(Product product : products) {
            %>

                <div class="product-card">

                    <img src="<%= product.getImageUrl() %>"
                         alt="Product Image">

                    <div class="product-info">

                        <h3>
                            <%= product.getName() %>
                        </h3>

                        <p class="brand">
                            Brand :
                            <%= product.getBrand() %>
                        </p>

                        <p class="description">
                            <%= product.getDescription() %>
                        </p>

                        <a href="${pageContext.request.contextPath}/product-details?id=<%= product.getProductId() %>"
   class="view-btn">

   View Product

</a>

                    </div>

                </div>

            <%
                    }

                } else {
            %>

                <h2>No Products Available</h2>

            <%
                }
            %>

        </div>

    </section>

</body>
</html>
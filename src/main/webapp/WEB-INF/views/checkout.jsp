<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<%@ page import="com.fashionstore.model.CartItem" %>
<%@ page import="com.fashionstore.model.ProductVariant" %>
<%@ page import="com.fashionstore.model.Product" %>

<%@ page import="com.fashionstore.dao.ProductDAO" %>

<%
    List<CartItem> cartItems =
        (List<CartItem>) request.getAttribute("cartItems");

    double total =
        (double) request.getAttribute("total");

    ProductDAO productDAO =
        (ProductDAO) request.getAttribute("productDAO");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Checkout | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/checkout.css">

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

        </ul>

    </nav>

    <!-- Checkout -->

    <section class="checkout-section">

        <h1>Checkout</h1>

        <div class="checkout-container">

            <!-- Left -->

            <div class="checkout-items">

                <h2>Order Summary</h2>

                <%
                    if(cartItems != null &&
                       !cartItems.isEmpty()) {

                        for(CartItem item : cartItems) {

                            ProductVariant variant =
                                    productDAO.getVariantById(
                                            item.getVariantId());

                            Product product =
                                    productDAO.getProductById(
                                            variant.getProductId());
                %>

                    <div class="checkout-card">

                        <img src="<%= product.getImageUrl() %>"
                             alt="Product Image">

                        <div class="checkout-info">

                            <h3>
                                <%= product.getName() %>
                            </h3>

                            <p>
                                Size :
                                <%= variant.getSize() %>
                            </p>

                            <p>
                                Color :
                                <%= variant.getColor() %>
                            </p>

                            <p>
                                Quantity :
                                <%= item.getQuantity() %>
                            </p>

                            <p class="price">

                                ₹ <%= variant.getPrice()
                                      * item.getQuantity() %>

                            </p>

                        </div>

                    </div>

                <%
                        }
                    }
                %>

            </div>

            <!-- Right -->

            <div class="checkout-form-container">

                <h2>Shipping Details</h2>

                <form action="${pageContext.request.contextPath}/place-order"
                      method="post">

                    <div class="form-group">

                        <label>Full Name</label>

                        <input type="text"
                               name="fullName"
                               required>

                    </div>

                    <div class="form-group">

                        <label>Phone Number</label>

                        <input type="text"
                               name="phone"
                               required>

                    </div>

                    <div class="form-group">

                        <label>Address</label>

                        <textarea name="address"
                                  rows="5"
                                  required></textarea>

                    </div>

                    <div class="total-section">

                        <h2>

                            Total :
                            ₹ <%= total %>

                        </h2>

                    </div>

                    <button type="submit"
                            class="place-order-btn">

                        Place Order

                    </button>

                </form>

            </div>

        </div>

    </section>

</body>
</html>
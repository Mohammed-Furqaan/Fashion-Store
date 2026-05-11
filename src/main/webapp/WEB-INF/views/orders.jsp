<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Order" %>

<%
    List<Order> orders =
        (List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>My Orders | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/orders.css">

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

    <!-- Orders -->

    <section class="orders-section">

        <h1>My Orders</h1>

        <%
            if(orders != null &&
               !orders.isEmpty()) {

                for(Order order : orders) {
        %>

            <div class="order-card">

                <div class="order-top">

                    <div>

                        <h2>
                            Order #<%= order.getOrderId() %>
                        </h2>

                        <p>
                            <%= order.getCreatedAt() %>
                        </p>

                    </div>

                    <div class="status">

                        <%= order.getOrderStatus() %>

                    </div>

                </div>

                <div class="order-body">

                    <p>

                        <strong>Total :</strong>

                        ₹ <%= order.getTotalAmount() %>

                    </p>

                    <p>

                        <strong>Payment :</strong>

                        <%= order.getPaymentMethod() %>

                    </p>

                    <p>

                        <strong>Shipping Address :</strong>

                        <%= order.getShippingAddress() %>

                    </p>

                </div>

            </div>

        <%
                }
            } else {
        %>

            <div class="empty-orders">

                <h2>No Orders Yet</h2>

                <p>
                    Start shopping to place your first order.
                </p>

            </div>

        <%
            }
        %>

    </section>

</body>
</html>
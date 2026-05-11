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

<title>Cart | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/cart.css">

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
                <a href="${pageContext.request.contextPath}/logout">
                    Logout
                </a>
            </li>

        </ul>

    </nav>

    <!-- Cart Section -->

    <section class="cart-section">

        <h1>Your Cart</h1>

        <div class="cart-container">

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

                <div class="cart-card">

                    <img src="<%= product.getImageUrl() %>"
                         alt="Product Image">

                    <div class="cart-info">

                        <h2>
                            <%= product.getName() %>
                        </h2>

                        <p>
                            Brand :
                            <%= product.getBrand() %>
                        </p>

                        <p>
                            Size :
                            <%= variant.getSize() %>
                        </p>

                        <p>
                            Color :
                            <%= variant.getColor() %>
                        </p>

                        <p>
                            Price :
                            ₹ <%= variant.getPrice() %>
                        </p>

                        <form action="${pageContext.request.contextPath}/update-cart-quantity"
      method="post"
      class="quantity-form">

    <input type="hidden"
           name="cartItemId"
           value="<%= item.getCartItemId() %>">

    <label>Quantity :</label>

    <input type="number"
           name="quantity"
           value="<%= item.getQuantity() %>"
           min="1">

    <button type="submit"
            class="update-btn">

        Update

    </button>

</form>

                        <p class="subtotal">

                            Subtotal :

                            ₹ <%= variant.getPrice()
                                  * item.getQuantity() %>

                        </p>
                        
                        <a href="${pageContext.request.contextPath}/remove-cart-item?cartItemId=<%= item.getCartItemId() %>"
   class="remove-btn">

    Remove

</a>

                    </div>
                    

                </div>

            <%
                    }

                } else {
            %>

                <h2>Your Cart Is Empty</h2>

            <%
                }
            %>

        </div>

        <!-- Total -->

        <div class="cart-total">

            <h2>

                Total :
                ₹ <%= total %>

            </h2>
            <a href="${pageContext.request.contextPath}/checkout"
   class="checkout-btn">

    Proceed To Checkout

</a>

        </div>
        

    </section>

</body>
</html>
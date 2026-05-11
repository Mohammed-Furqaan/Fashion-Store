<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page import="com.fashionstore.model.ProductVariant" %>

<%
    Product product =
        (Product) request.getAttribute("product");

    List<ProductVariant> variants =
        (List<ProductVariant>) request.getAttribute("variants");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/product-details.css">

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

    <!-- Product Details -->

    <section class="product-details-section">

        <div class="product-container">

            <!-- Left -->

            <div class="product-image">

                <img src="<%= product.getImageUrl() %>"
                     alt="Product Image">

            </div>

            <!-- Right -->

            <div class="product-info">

                <h1>
                    <%= product.getName() %>
                </h1>

                <h3>
                    Brand :
                    <%= product.getBrand() %>
                </h3>

                <p class="description">

                    <%= product.getDescription() %>

                </p>

                <!-- Variants -->

                <form action="${pageContext.request.contextPath}/add-to-cart"
      method="post">

    <div class="form-group">

        <label>Select Variant</label>

        <select name="variantId" required>

            <%
                if(variants != null &&
                   !variants.isEmpty()) {

                    for(ProductVariant variant : variants) {
            %>

                <option
                    value="<%= variant.getVariantId() %>">

                    Size :
                    <%= variant.getSize() %>

                    |

                    Color :
                    <%= variant.getColor() %>

                    |

                    ₹ <%= variant.getPrice() %>

                    |

                    Stock :
                    <%= variant.getStockQuantity() %>

                </option>

            <%
                    }
                }
            %>

        </select>

    </div>

    <div class="form-group">

        <label>Quantity</label>

        <input type="number"
               name="quantity"
               value="1"
               min="1"
               required>

    </div>

    <button type="submit"
            class="cart-btn">

        Add To Cart

    </button>

</form>

            </div>

        </div>

    </section>

</body>
</html>
package com.fashionstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.daoimpl.OrderDAOImpl;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();

        orderDAO = new OrderDAOImpl();

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        String address =
                request.getParameter("address");

        Cart cart =
                cartDAO.getCartByUserId(
                        user.getUserId());

        if(cart == null) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        List<CartItem> cartItems =
                cartDAO.getCartItems(
                        cart.getCartId());

        if(cartItems == null ||
           cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        double total =
                cartDAO.getCartTotal(
                        cart.getCartId());

        Order order = new Order();

        order.setUserId(user.getUserId());

        order.setTotalAmount(total);

        order.setShippingAddress(address);

        order.setPaymentMethod("Cash On Delivery");

        order.setOrderStatus("Placed");

        List<OrderItem> orderItems =
                new ArrayList<>();

        for(CartItem cartItem : cartItems) {

            ProductVariant variant =
                    productDAO.getVariantById(
                            cartItem.getVariantId());

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setVariantId(
                    cartItem.getVariantId());

            orderItem.setQuantity(
                    cartItem.getQuantity());

            orderItem.setPrice(
                    variant.getPrice());

            orderItems.add(orderItem);
        }

        boolean status =
                orderDAO.placeOrder(
                        order,
                        orderItems);

        if(status) {

            cartDAO.clearCart(
                    cart.getCartId());

            response.sendRedirect(
                    request.getContextPath()
                    + "/orders");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/checkout");
        }
    }
}
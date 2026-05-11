package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();
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

        int userId = user.getUserId();

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart == null) {

            cartDAO.createCart(userId);

            cart = cartDAO.getCartByUserId(userId);
        }

        int cartId = cart.getCartId();

        int variantId =
                Integer.parseInt(
                        request.getParameter("variantId"));

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity"));

        boolean status =
                cartDAO.addToCart(
                        cartId,
                        variantId,
                        quantity
                );

        if(status) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/products");
        }
    }
}
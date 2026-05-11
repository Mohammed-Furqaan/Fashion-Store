package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.daoimpl.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-cart-quantity")
public class UpdateCartQuantityServlet extends HttpServlet {

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

        int cartItemId =
                Integer.parseInt(
                        request.getParameter("cartItemId"));

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity"));

        if(quantity < 1) {

            quantity = 1;
        }

        cartDAO.updateCartItemQuantity(
                cartItemId,
                quantity);

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }
}
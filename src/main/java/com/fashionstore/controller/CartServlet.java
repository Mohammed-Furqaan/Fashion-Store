package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
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

        Cart cart =
                cartDAO.getCartByUserId(
                        user.getUserId());

        if(cart == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/products");

            return;
        }

        List<CartItem> cartItems =
                cartDAO.getCartItems(
                        cart.getCartId());

        double total =
                cartDAO.getCartTotal(
                        cart.getCartId());

        request.setAttribute(
                "cartItems",
                cartItems);

        request.setAttribute(
                "total",
                total);

        request.setAttribute(
                "productDAO",
                productDAO);

        request.getRequestDispatcher(
                "/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }
}
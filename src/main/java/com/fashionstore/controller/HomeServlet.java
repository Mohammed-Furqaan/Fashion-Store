package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Session Validation
        if (session == null ||
            session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        // Get Logged In User
        User user =
                (User) session.getAttribute("loggedInUser");

        request.setAttribute("user", user);

        request.getRequestDispatcher(
                "/WEB-INF/views/home.jsp")
                .forward(request, response);
    }
}
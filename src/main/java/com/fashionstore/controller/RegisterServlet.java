package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.daoimpl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/register.jsp")
                .forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    String fullName = request.getParameter("fullName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");

    // Check Existing Email
    User existingUser = userDAO.getUserByEmail(email);

    if (existingUser != null) {

        request.setAttribute(
                "error",
                "Email already registered!");

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/register.jsp")
                .forward(request, response);

        return;
    }

    // Create User Object
    User user = new User();

    user.setFullName(fullName);
    user.setEmail(email);
    user.setPhone(phone);
    user.setPassword(password);

    // Save User
    boolean status = userDAO.registerUser(user);

    if (status) {

        request.setAttribute(
                "success",
                "Registration successful! Please login.");

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/register.jsp")
                .forward(request, response);

    } else {

        request.setAttribute(
                "error",
                "Registration failed!");

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/register.jsp")
                .forward(request, response);
    }
}
}
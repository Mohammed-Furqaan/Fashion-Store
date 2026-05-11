package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productDAO.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher(
                "/WEB-INF/views/products.jsp")
                .forward(request, response);
    }
}
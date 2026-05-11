package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

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

        String productIdParam =
                request.getParameter("id");

        if(productIdParam == null ||
           productIdParam.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        Product product =
                productDAO.getProductById(productId);

        List<ProductVariant> variants =
                productDAO.getVariantsByProductId(productId);

        if(product == null) {

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        request.setAttribute("product", product);

        request.setAttribute("variants", variants);

        request.getRequestDispatcher(
                "/WEB-INF/views/product-details.jsp")
                .forward(request, response);
    }
}
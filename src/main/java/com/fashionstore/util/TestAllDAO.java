package com.fashionstore.util;

import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.UserDAO;

import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.daoimpl.CategoryDAOImpl;
import com.fashionstore.daoimpl.OrderDAOImpl;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.daoimpl.UserDAOImpl;

import com.fashionstore.model.CartItem;
import com.fashionstore.model.Category;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

public class TestAllDAO {

    public static void main(String[] args) {

        // =========================
        // USER DAO TEST
        // =========================

        System.out.println("\n========== USER DAO TEST ==========\n");

        UserDAO userDAO = new UserDAOImpl();

        List<User> users = userDAO.getAllUsers();

        for (User user : users) {

            System.out.println("User ID   : " + user.getUserId());
            System.out.println("Name      : " + user.getFullName());
            System.out.println("Email     : " + user.getEmail());
            System.out.println("Phone     : " + user.getPhone());

            System.out.println("--------------------------------");
        }

        // =========================
        // CATEGORY DAO TEST
        // =========================

        System.out.println("\n========== CATEGORY DAO TEST ==========\n");

        CategoryDAO categoryDAO = new CategoryDAOImpl();

        List<Category> categories = categoryDAO.getAllCategories();

        for (Category category : categories) {

            System.out.println("Category ID : " + category.getCategoryId());
            System.out.println("Category    : " + category.getName());

            System.out.println("--------------------------------");
        }

        // =========================
        // PRODUCT DAO TEST
        // =========================

        System.out.println("\n========== PRODUCT DAO TEST ==========\n");

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {

            System.out.println("Product ID : " + product.getProductId());
            System.out.println("Name       : " + product.getName());
            System.out.println("Brand      : " + product.getBrand());
            System.out.println("CategoryID : " + product.getCategoryId());

            System.out.println("--------------------------------");
        }

        // =========================
        // PRODUCT SEARCH TEST
        // =========================

        System.out.println("\n========== PRODUCT SEARCH TEST ==========\n");

        List<Product> searchedProducts =
                productDAO.searchProducts("Jeans");

        for (Product product : searchedProducts) {

            System.out.println("Found Product : " + product.getName());
        }

        // =========================
        // PRODUCT FILTER TEST
        // =========================

        System.out.println("\n========== PRODUCT FILTER TEST ==========\n");

        List<Product> filteredProducts =
                productDAO.filterProducts(
                        1,
                        "M",
                        "Black",
                        500.0,
                        1000.0
                );

        for (Product product : filteredProducts) {

            System.out.println("Filtered Product : " + product.getName());
        }

        // =========================
        // PRODUCT VARIANT TEST
        // =========================

        System.out.println("\n========== PRODUCT VARIANT TEST ==========\n");

        List<ProductVariant> variants =
                productDAO.getVariantsByProductId(1);

        for (ProductVariant variant : variants) {

            System.out.println("Variant ID : " + variant.getVariantId());
            System.out.println("Size       : " + variant.getSize());
            System.out.println("Color      : " + variant.getColor());
            System.out.println("Price      : " + variant.getPrice());

            System.out.println("--------------------------------");
        }

        // =========================
        // CART DAO TEST
        // =========================

        System.out.println("\n========== CART DAO TEST ==========\n");

        CartDAO cartDAO = new CartDAOImpl();

        List<CartItem> cartItems = cartDAO.getCartItems(1);

        for (CartItem item : cartItems) {

            System.out.println("Cart Item ID : " + item.getCartItemId());
            System.out.println("Variant ID   : " + item.getVariantId());
            System.out.println("Quantity     : " + item.getQuantity());

            System.out.println("--------------------------------");
        }

        double cartTotal = cartDAO.getCartTotal(1);

        System.out.println("Cart Total : " + cartTotal);

        // =========================
        // ORDER DAO TEST
        // =========================

        System.out.println("\n========== ORDER DAO TEST ==========\n");

        OrderDAO orderDAO = new OrderDAOImpl();

        List<Order> orders = orderDAO.getAllOrders();

        for (Order order : orders) {

            System.out.println("Order ID      : " + order.getOrderId());
            System.out.println("User ID       : " + order.getUserId());
            System.out.println("Total Amount  : " + order.getTotalAmount());
            System.out.println("Order Status  : " + order.getOrderStatus());

            System.out.println("--------------------------------");
        }

        // =========================
        // ORDER ITEMS TEST
        // =========================

        System.out.println("\n========== ORDER ITEMS TEST ==========\n");

        List<OrderItem> orderItems =
                orderDAO.getOrderItems(1);

        for (OrderItem item : orderItems) {

            System.out.println("Order Item ID : " + item.getOrderItemId());
            System.out.println("Variant ID    : " + item.getVariantId());
            System.out.println("Quantity      : " + item.getQuantity());
            System.out.println("Price         : " + item.getPrice());

            System.out.println("--------------------------------");
        }

        System.out.println("\n========== ALL DAO TESTS COMPLETED ==========");
    }
}
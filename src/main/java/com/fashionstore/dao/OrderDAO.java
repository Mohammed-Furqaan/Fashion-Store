package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;

public interface OrderDAO {

    boolean placeOrder(Order order, List<OrderItem> orderItems);

    Order getOrderById(int orderId);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getAllOrders();

    List<OrderItem> getOrderItems(int orderId);

    boolean updateOrderStatus(int orderId, String status);

    boolean cancelOrder(int orderId);
}
package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean placeOrder(Order order, List<OrderItem> orderItems) {

        boolean status = false;

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            String orderQuery = """
                    INSERT INTO orders(user_id, total_amount,
                    shipping_address, payment_method, order_status)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement orderStatement =
                    connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);

            orderStatement.setInt(1, order.getUserId());
            orderStatement.setDouble(2, order.getTotalAmount());
            orderStatement.setString(3, order.getShippingAddress());
            orderStatement.setString(4, order.getPaymentMethod());
            orderStatement.setString(5, order.getOrderStatus());

            int orderRows = orderStatement.executeUpdate();

            if (orderRows > 0) {

                ResultSet generatedKeys = orderStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    int orderId = generatedKeys.getInt(1);

                    String orderItemQuery = """
                            INSERT INTO order_items(order_id,
                            variant_id, quantity, price)
                            VALUES (?, ?, ?, ?)
                            """;

                    PreparedStatement itemStatement =
                            connection.prepareStatement(orderItemQuery);

                    for (OrderItem item : orderItems) {

                        itemStatement.setInt(1, orderId);
                        itemStatement.setInt(2, item.getVariantId());
                        itemStatement.setInt(3, item.getQuantity());
                        itemStatement.setDouble(4, item.getPrice());

                        itemStatement.addBatch();
                    }

                    itemStatement.executeBatch();

                    connection.commit();

                    status = true;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            try {

                if (connection != null) {

                    connection.rollback();
                }

            } catch (SQLException ex) {

                ex.printStackTrace();
            }

        } finally {

            try {

                if (connection != null) {

                    connection.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return status;
    }

    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        String query = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                order = mapOrder(resultSet);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                orders.add(mapOrder(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                orders.add(mapOrder(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem item = new OrderItem();

                item.setOrderItemId(resultSet.getInt("order_item_id"));
                item.setOrderId(resultSet.getInt("order_id"));
                item.setVariantId(resultSet.getInt("variant_id"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getDouble("price"));
                item.setCreatedAt(resultSet.getTimestamp("created_at"));

                orderItems.add(item);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String statusValue) {

        boolean status = false;

        String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, statusValue);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean cancelOrder(int orderId) {

        return updateOrderStatus(orderId, "Cancelled");
    }

    private Order mapOrder(ResultSet resultSet) throws SQLException {

        Order order = new Order();

        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTotalAmount(resultSet.getDouble("total_amount"));
        order.setShippingAddress(resultSet.getString("shipping_address"));
        order.setPaymentMethod(resultSet.getString("payment_method"));
        order.setOrderStatus(resultSet.getString("order_status"));
        order.setCreatedAt(resultSet.getTimestamp("created_at"));

        return order;
    }
}
package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean createCart(int userId) {

        boolean status = false;

        String query = "INSERT INTO cart(user_id) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Cart getCartByUserId(int userId) {

        Cart cart = null;

        String query = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                cart = new Cart();

                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setUserId(resultSet.getInt("user_id"));
                cart.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public boolean addToCart(int cartId, int variantId, int quantity) {

        boolean status = false;

        String query = "INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, variantId);
            preparedStatement.setInt(3, quantity);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {

        boolean status = false;

        String query = "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeCartItem(int cartItemId) {

        boolean status = false;

        String query = "DELETE FROM cart_items WHERE cart_item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {

        List<CartItem> cartItems = new ArrayList<>();

        String query = "SELECT * FROM cart_items WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setVariantId(resultSet.getInt("variant_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setCreatedAt(resultSet.getTimestamp("created_at"));

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public double getCartTotal(int cartId) {

        double total = 0.0;

        String query = """
                SELECT SUM(ci.quantity * pv.price) AS total
                FROM cart_items ci
                JOIN product_variants pv
                ON ci.variant_id = pv.variant_id
                WHERE ci.cart_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                total = resultSet.getDouble("total");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return total;
    }

    @Override
    public boolean clearCart(int cartId) {

        boolean status = false;

        String query = "DELETE FROM cart_items WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }
}
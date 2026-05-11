package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {

        boolean status = false;

        String query = "INSERT INTO users(full_name, email, password, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public User loginUser(String email, String password) {

        User user = null;

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {

        User user = null;

        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        User user = null;

        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean updateUser(User user) {

        boolean status = false;

        String query = "UPDATE users SET full_name = ?, email = ?, phone = ? WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setInt(4, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean changePassword(int userId, String newPassword) {

        boolean status = false;

        String query = "UPDATE users SET password = ? WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteUser(int userId) {

        boolean status = false;

        String query = "DELETE FROM users WHERE user_id = ?";

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
}
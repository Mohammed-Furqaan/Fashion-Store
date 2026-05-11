package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean addCategory(Category category) {

        boolean status = false;

        String query = "INSERT INTO categories(name) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Category getCategoryById(int categoryId) {

        Category category = null;

        String query = "SELECT * FROM categories WHERE category_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                category = new Category();

                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {

        Category category = null;

        String query = "SELECT * FROM categories WHERE name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, categoryName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                category = new Category();

                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM categories";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Category category = new Category();

                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setCreatedAt(resultSet.getTimestamp("created_at"));

                categories.add(category);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public boolean updateCategory(Category category) {

        boolean status = false;

        String query = "UPDATE categories SET name = ? WHERE category_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getCategoryId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteCategory(int categoryId) {

        boolean status = false;

        String query = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }
}
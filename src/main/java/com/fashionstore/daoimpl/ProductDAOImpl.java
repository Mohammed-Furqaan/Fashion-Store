package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {

        boolean status = false;

        String query = "INSERT INTO products(category_id, name, description, brand, image_url) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getImageUrl());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Product getProductById(int productId) {

        Product product = null;

        String query = "SELECT * FROM products WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                product = mapProduct(resultSet);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                products.add(mapProduct(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE category_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapProduct(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE name LIKE ? OR brand LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String searchKeyword = "%" + keyword + "%";

            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapProduct(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> filterProducts(Integer categoryId, String size,
            String color, Double minPrice, Double maxPrice) {

        List<Product> products = new ArrayList<>();

        StringBuilder query = new StringBuilder(
                "SELECT DISTINCT p.* FROM products p " +
                "JOIN product_variants pv ON p.product_id = pv.product_id WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();

        if (categoryId != null) {
            query.append(" AND p.category_id = ?");
            parameters.add(categoryId);
        }

        if (size != null && !size.isEmpty()) {
            query.append(" AND pv.size = ?");
            parameters.add(size);
        }

        if (color != null && !color.isEmpty()) {
            query.append(" AND pv.color = ?");
            parameters.add(color);
        }

        if (minPrice != null) {
            query.append(" AND pv.price >= ?");
            parameters.add(minPrice);
        }

        if (maxPrice != null) {
            query.append(" AND pv.price <= ?");
            parameters.add(maxPrice);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {

                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                products.add(mapProduct(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getLatestProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products ORDER BY created_at DESC LIMIT 10";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                products.add(mapProduct(resultSet));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getFeaturedProducts() {

        return getLatestProducts();
    }

    @Override
    public boolean updateProduct(Product product) {

        boolean status = false;

        String query = "UPDATE products SET category_id = ?, name = ?, description = ?, brand = ?, image_url = ? WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setInt(6, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProduct(int productId) {

        boolean status = false;

        String query = "DELETE FROM products WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean addProductVariant(ProductVariant variant) {

        boolean status = false;

        String query = "INSERT INTO product_variants(product_id, size, color, price, stock_quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variant.getProductId());
            preparedStatement.setString(2, variant.getSize());
            preparedStatement.setString(3, variant.getColor());
            preparedStatement.setDouble(4, variant.getPrice());
            preparedStatement.setInt(5, variant.getStockQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> variants = new ArrayList<>();

        String query = "SELECT * FROM product_variants WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ProductVariant variant = new ProductVariant();

                variant.setVariantId(resultSet.getInt("variant_id"));
                variant.setProductId(resultSet.getInt("product_id"));
                variant.setSize(resultSet.getString("size"));
                variant.setColor(resultSet.getString("color"));
                variant.setPrice(resultSet.getDouble("price"));
                variant.setStockQuantity(resultSet.getInt("stock_quantity"));
                variant.setCreatedAt(resultSet.getTimestamp("created_at"));

                variants.add(variant);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return variants;
    }

    @Override
    public ProductVariant getVariantById(int variantId) {

        ProductVariant variant = null;

        String query = "SELECT * FROM product_variants WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                variant = new ProductVariant();

                variant.setVariantId(resultSet.getInt("variant_id"));
                variant.setProductId(resultSet.getInt("product_id"));
                variant.setSize(resultSet.getString("size"));
                variant.setColor(resultSet.getString("color"));
                variant.setPrice(resultSet.getDouble("price"));
                variant.setStockQuantity(resultSet.getInt("stock_quantity"));
                variant.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return variant;
    }

    @Override
    public boolean updateProductVariant(ProductVariant variant) {

        boolean status = false;

        String query = "UPDATE product_variants SET size = ?, color = ?, price = ?, stock_quantity = ? WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, variant.getSize());
            preparedStatement.setString(2, variant.getColor());
            preparedStatement.setDouble(3, variant.getPrice());
            preparedStatement.setInt(4, variant.getStockQuantity());
            preparedStatement.setInt(5, variant.getVariantId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProductVariant(int variantId) {

        boolean status = false;

        String query = "DELETE FROM product_variants WHERE variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {

        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setBrand(resultSet.getString("brand"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));

        return product;
    }
}
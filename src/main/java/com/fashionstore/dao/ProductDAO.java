package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;

public interface ProductDAO {

    boolean addProduct(Product product);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> filterProducts(
            Integer categoryId,
            String size,
            String color,
            Double minPrice,
            Double maxPrice
    );

    List<Product> getLatestProducts();

    List<Product> getFeaturedProducts();

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    boolean addProductVariant(ProductVariant variant);

    List<ProductVariant> getVariantsByProductId(int productId);

    ProductVariant getVariantById(int variantId);

    boolean updateProductVariant(ProductVariant variant);

    boolean deleteProductVariant(int variantId);
}
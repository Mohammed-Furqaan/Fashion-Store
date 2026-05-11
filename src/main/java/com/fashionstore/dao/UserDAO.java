package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User loginUser(String email, String password);

    User getUserById(int userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean changePassword(int userId, String newPassword);

    boolean deleteUser(int userId);
}
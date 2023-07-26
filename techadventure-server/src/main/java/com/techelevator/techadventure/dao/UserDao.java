package com.techelevator.techadventure.dao;

import com.techelevator.techadventure.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

    BigDecimal getPoints(String name);
}

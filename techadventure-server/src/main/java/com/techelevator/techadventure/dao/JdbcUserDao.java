package com.techelevator.techadventure.dao;

import com.techelevator.techadventure.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM techadventure_user WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM techadventure_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash FROM techadventure_user WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password) {

        boolean created = true;
        // create user
        String sql = "INSERT INTO techadventure_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        newUserId = -1;
        try {
            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
        } catch (DataAccessException e) {
            created = false;
            e.printStackTrace();
        }

        // create account
        sql = "INSERT INTO account (user_id, balance) values(?, ?)";
        try {
            jdbcTemplate.update(sql, newUserId, STARTING_BALANCE);
        } catch (DataAccessException e) {
            created = false;
            e.printStackTrace();
        }

        return created;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }

    @Override
    public BigDecimal getPoints(String userName) {
        
        String sql = "SELECT points FROM account JOIN techadventure_user ON account.user_id = techadventure_user.user_id WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        results.next();

        return results.getBigDecimal("points");
    }
}

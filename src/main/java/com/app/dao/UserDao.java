package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //1st method: requesting data from database
    /**
     * "jdbcTemplate" - is a part of String library responsible for DB queries
     * "jdbcTemplate" has a method that makes queries to DB: "query"
     * "query" makes sql query to DB
     * "query" receives response from DB as a list of rows with data
     * "query" sends this response to "rowMapper" to receive a list ob Java Objects
     * "rowMapper" knows what to do with DB response and how to get list ob Java Objects
     * "rowMapper" generates 2 variables for each row:
     *                                      1: "rs" - puts in all row data (is, first_name...)
     *                                      2: "rowNumber" - puts in current row number
     * "rowMapper" calls "mapUser" method for each row sending current row data to map ("rs")
     * "mapUser" method returns to "rowMapper" Java Object User for each row with data in it
     * "rowMapper" collects all User objects to Java List
     * "rowMapper" returns this list to "query" as a response (after "query" asked "rowMapper" to parse DB response)
     * this list of Users is what we can return as a main method result
     */
    public List<User> getUsers() {
        RowMapper<User> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    //2nd method: mapping response from database to objects
    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));

        return user;
    }

    public void storeUser(User user) {
        jdbcTemplate.update("INSERT INTO users (first_name, last_name, email, phone, password) VALUES (?, ?, ?, ?, '123')",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
    }
}

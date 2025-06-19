package com.example.stocker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.Order;

@Repository
public class OrderRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insertOrder(Order order) {
        String sql = "INSERT INTO orders (stock_id, quantity, user_name, total_price) VALUES (:stockId, :quantity, :userName, :totalPrice)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        template.update(sql, param);
    }
}

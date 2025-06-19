package com.example.stocker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.MessageHistory;

@Repository
public class MessageHistoryRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insert(MessageHistory history) {
        String sql = "INSERT INTO message_history (stock_id, message, message_history_at) VALUES (:stockId, :message, :createdAt)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(history);
        template.update(sql, param);
    }
}

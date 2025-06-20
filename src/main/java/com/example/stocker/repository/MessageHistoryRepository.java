package com.example.stocker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.MessageHistory;
import com.example.stocker.form.HistoryForm;

@Repository
public class MessageHistoryRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<MessageHistory> MESSAGE_HISTORY_ROW_MAPPER = (rs, i) -> {
        MessageHistory history = new MessageHistory();
        history.setId(rs.getInt("id"));
        history.setStockId(rs.getInt("stock_id"));
        history.setMessage(rs.getString("message"));
        history.setCreatedAt(rs.getObject("message_history_at", LocalDateTime.class));
        return history;
    };

    /**
     * 通知履歴をDBに保存
     */
    public void insert(MessageHistory history) {
        String sql = "INSERT INTO message_history (stock_id, message, message_history_at) VALUES (:stockId, :message, :createdAt)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(history);
        template.update(sql, param);
    }

    /**
     * 通知履歴の一覧取得
     * @return
     */
    public List<MessageHistory> findAllMessageHistory () {
        String sql = "SELECT * FROM message_history ORDER BY message_history_at DESC";
        SqlParameterSource param = new MapSqlParameterSource();
        return template.query(sql, param, MESSAGE_HISTORY_ROW_MAPPER);
    }

    public List<MessageHistory> searchHistory (HistoryForm form) {
        StringBuilder sql = new StringBuilder("SELECT * FROM message_history WHERE 1 = 1 ");
        MapSqlParameterSource param = new MapSqlParameterSource();

        if (form.getMessage() != null) {
            sql.append("AND message LIKE :message ");
            param.addValue("message", "%" + form.getMessage() + "%");
        }

        if (form.getStockId() != null) {
            sql.append("AND stock_id = :stockId ");
            param.addValue("stockId", form.getStockId());
        }

        if (form.getFromDate() != null) {
            sql.append("AND message_history_at >= :fromDate ");
            param.addValue("fromDate", form.getFromDate().atStartOfDay());
        }

        if (form.getToDate() != null) {
            sql.append("AND message_history_at <= :toDate ");
            param.addValue("toDate", form.getToDate().atTime(23, 59, 59));
        }
        sql.append("ORDER BY message_history_at DESC");
        return template.query(sql.toString(), param, MESSAGE_HISTORY_ROW_MAPPER);
    }
}

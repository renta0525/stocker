package com.example.stocker.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.User;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString( "name"));
        user.setMail(rs.getString("mail"));
        user.setPassword(rs.getString("password"));
        user.setAdminFlg(rs.getBoolean("admin_flg"));
        user.setDelFlg(rs.getBoolean("del_flg"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdateAt(rs.getTimestamp("update_at"));
        return user;
    };

    /**
     * 
     * @param mail
     * @return
     */
    public Optional<User> findByMail (String mail) {
        String mailSql = "SELECT * FROM users WHERE mail = :mail AND del_flg = false";
        SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);
        try {
            User user = template.queryForObject(mailSql, param, USER_ROW_MAPPER);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}

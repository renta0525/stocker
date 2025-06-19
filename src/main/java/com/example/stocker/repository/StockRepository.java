package com.example.stocker.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.Stock;

@Repository
public class StockRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Stock> STOCK_ROW_MAPPER = (rs, i) -> {
        Stock stock = new Stock();
        stock.setId(rs.getInt("Id"));
        stock.setName(rs.getString("name"));
        stock.setPrice(rs.getInt("price"));
        stock.setQuantity(rs.getInt("quantity"));
        stock.setShopId(rs.getInt("shop_id"));
        stock.setCategoryId(rs.getInt("category_id"));
        stock.setMemo(rs.getString("memo"));
        stock.setBoundaryValue(rs.getInt("boundary_value"));
        stock.setImagePath(rs.getString("image_path"));
        stock.setDelFlg(rs.getBoolean("del_flg"));
        return stock;
    };

    /**
     * 削除されていないstockを全件取得
     * @return
     */
    public List<Stock> findAll () {
        String sql = "SELECT * FROM stocks WHERE del_flg = false ORDER BY id";
        SqlParameterSource source = new MapSqlParameterSource();
        return template.query(sql, source, STOCK_ROW_MAPPER);
    }

    /**
     * shopに紐付いている在庫を取得する
     * @param shopId
     * @return
     */
    public List<Stock> findByShopId (int shopId) {
        String sql = "SELECT * FROM stocks WHERE shop_id = :shopId AND del_flg = false ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("shopId", shopId);
        return template.query(sql, param, STOCK_ROW_MAPPER);
    }

    /**
     * stockを1件取得
     * @param id
     * @return
     */
    public Optional<Stock> findById(int id) {
        String sql = "SELECT * FROM stocks WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(template.queryForObject(sql, param, STOCK_ROW_MAPPER));
    }

    /**
     * stockの編集
     * @param stock
     */
    public void updateByStock (Stock stock) {
        String sql = "UPDATE stocks SET name = :name, price = :price, quantity = :quantity, memo = :memo, boundary_value = :boundaryValue WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                                    .addValue("name", stock.getName())
                                    .addValue("quantity", stock.getQuantity())
                                    .addValue("price", stock.getPrice())
                                    .addValue("memo",stock.getMemo())
                                    .addValue("boundaryValue", stock.getBoundaryValue())
                                    .addValue("id", stock.getId());
        template.update(sql, param);
    }
}

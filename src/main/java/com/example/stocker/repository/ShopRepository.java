package com.example.stocker.repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.stocker.domain.Shop;

@Repository
public class ShopRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Shop> SHOP_ROW_MAPPER = (rs, i) -> {
        Shop shop = new Shop();
        shop.setId(rs.getInt("id"));
        shop.setName(rs.getString("name"));
        shop.setUserId(rs.getInt("user_id"));
        shop.setImagePath(rs.getString("image_path"));
        shop.setDelFlg(rs.getBoolean("del_flg"));
        return shop;
    };

    /**
     * 全件取得(admin用)
     * @return
     */
    public List<Shop> shopList () {
        String sql = "SELECT * FROM shops ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource();
        return template.query(sql, param, SHOP_ROW_MAPPER);
    }

    /**
     * userIdに紐付いたshopを取得
     * @param userId
     * @return
     */
    public List<Shop> shopListByUserId (int userId) {
        String sql = "SELECT * FROM shops WHERE del_flg = false AND user_id = :userId ORDER BY id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        return template.query(sql, param, SHOP_ROW_MAPPER);
    }

    /**
     * shopを1件取得
     * @param shopId
     * @return
     */
    public Optional<Shop> findById (int id) {
        String sql = "SELECT * FROM shops WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(template.queryForObject(sql, param,SHOP_ROW_MAPPER));
    }

    /**
     * shopの名前と画像の編集
     * @param shop
     */
    public void updateByShop (Shop shop) {
        String sql = "UPDATE shops SET name = :name, image_path = :imagePath WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                                    .addValue("name", shop.getName())
                                    .addValue("imagePath",shop.getImagePath())
                                    .addValue("id", shop.getId());
        template.update(sql, param);
    }

    /**
     * shopの削除（削除フラグ変更）
     * @param id
     */
    public void deleteByShop (int id) {
        String sql = "UPDATE shops SET del_flg = true WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}

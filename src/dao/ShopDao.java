package dao;

import db.Database;
import dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDao {
    public void insert(ShopDto shop) {
        try {
            String sql = "INSERT INTO shops (name, description) VALUES (?, ?)";

            PreparedStatement pstmt = Database.conn().prepareStatement(sql);

            pstmt.setString(1, shop.getName());
            pstmt.setString(2,  shop.getDescription());
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ShopDto findOne(int shopId) {
        try {
            String sql = "SELECT * FROM shops WHERE id = ? LIMIT 1";
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new ShopDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ShopDto> findAll() {
        try {
            String sql = "SELECT * FROM shops";
            ResultSet rs = Database.query(sql);

            List<ShopDto> dtos = new ArrayList<>();

            while(rs.next()) {
                dtos.add(new ShopDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }

            return dtos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

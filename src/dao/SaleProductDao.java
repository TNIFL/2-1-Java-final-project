package dao;

import db.Database;
import dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleProductDao {

    public void insert(SaleProductDto saleProductDto) {
        String sql = "INSERT INTO sale_products (name, description, price, shop_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setString(1, saleProductDto.getName());
            pstmt.setString(2, saleProductDto.getDescription());
            pstmt.setInt(3, saleProductDto.getPrice());
            pstmt.setInt(4, saleProductDto.getShopId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public SaleProductDto findOne(int saleProductId) {
        try {
            String sql = "SELECT * FROM Sale_products WHERE id = ?";

            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, saleProductId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new SaleProductDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("shop_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<SaleProductDto> findAll() {
        try {
            String sql = "SELECT * FROM Sale_products";
            ResultSet rs = Database.query(sql);

            return resultSetToSaleProduct(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SaleProductDto> findByShopId(int shopId) {
        try {
            String sql = "SELECT * FROM sale_products WHERE shop_id = ?";

            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();

            return resultSetToSaleProduct(rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<SaleProductDto> resultSetToSaleProduct(ResultSet rs) throws SQLException {
        List<SaleProductDto> dtos = new ArrayList<>();

        while(rs.next()) {
            dtos.add(new SaleProductDto(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getInt("shop_id")
            ));
        }

        return dtos;
    }
}

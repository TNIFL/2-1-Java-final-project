package dao;

import db.Database;
import dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao {

    public void insert(CartItemDto cartItem) {
        String sql = "INSERT INTO cart_items (sale_product_id, quantity) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, cartItem.getSaleProductId());
            pstmt.setInt(2, cartItem.getQuantity());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CartItemDto cartItem) {
        String sql = "UPDATE cart_items SET sale_product_id = ?, quantity = ? WHERE id = ?";

        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, cartItem.getSaleProductId());
            pstmt.setInt(2, cartItem.getQuantity());
            pstmt.setInt(3, cartItem.getId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM cart_items";
        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CartItemDto findOneBySaleProductId(int saleProductId) {
        String sql = "SELECT * FROM cart_items WHERE sale_product_id = ? LIMIT 1";

        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, saleProductId);
            ResultSet rs = pstmt.executeQuery();

            List<CartItemDto> dtos = new ArrayList<>();

            if (rs.next()) {
                return new CartItemDto(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getInt("sale_product_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<CartItemDto> findAll() {
        // cart_items 를 전부 조회하고 싶다.
        try {
            String sql = "SELECT * FROM cart_items";
            ResultSet rs = Database.query(sql);

            List<CartItemDto> dtos = new ArrayList<>();

            while(rs.next()) {
                dtos.add(new CartItemDto(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getInt("sale_product_id")
                ));
            }

            return dtos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CartItemWithSaleProductWithShopDto> findAllWithSaleProductAndShop() {
        try {
            String sql = """
                    SELECT
                        ci.id,
                        ci.quantity,
                        sp.id,
                        sp.name,
                        sp.description,
                        sp.price,
                        s.id,
                        s.name,
                        s.description
                    FROM cart_items ci
                    LEFT JOIN sale_products sp ON ci.sale_product_id = sp.id
                    LEFT JOIN shops s ON sp.shop_id = s.id
                    """;
            ResultSet rs = Database.query(sql);

            List<CartItemWithSaleProductWithShopDto> dtos = new ArrayList<>();

            while (rs.next()) {
                dtos.add(new CartItemWithSaleProductWithShopDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        new SaleProductWithShopDto(
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                new ShopDto(
                                        rs.getInt(7),
                                        rs.getString(8),
                                        rs.getString(9)
                                )
                        )
                ));
            }
            return dtos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

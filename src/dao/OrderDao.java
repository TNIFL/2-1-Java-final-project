package dao;

import db.Database;
import dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public OrderDto findOne(int id) {
        try {
            String sql = "SELECT * FROM orders WHERE id = ?";

            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new OrderDto(
                        rs.getInt("id"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getInt("quantity"),
                        rs.getInt("sale_product_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insert(OrderDto order) {
        String sql = "INSERT INTO orders (status, sale_product_id, created_at, quantity) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setString(1, order.getStatus());
            pstmt.setInt(2, order.getSaleProductId());
            pstmt.setTimestamp(3, Timestamp.valueOf(order.getCreateAt()));
            pstmt.setInt(4, order.getQuantity());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(OrderDto orderDto) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try {
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setString(1, orderDto.getStatus());
            pstmt.setInt(2, orderDto.getId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderWithSaleProductAndShopDto> findAllWithSaleProductAndShop() {
        try {
            String sql = """
                    SELECT
                        o.id,
                        o.status,
                        o.created_at,
                        o.quantity,
                        sp.id,
                        sp.name,
                        sp.description,
                        sp.price,
                        s.id,
                        s.name,
                        s.description
                    FROM orders o
                    LEFT JOIN sale_products sp on o.sale_product_id = sp.id
                    LEFT JOIN shops s on sp.shop_id = s.id
                    ORDER BY o.id DESC
                    """;
            ResultSet rs = Database.query(sql);

            return resultSetToOrderWithSaleProductAndShopDto(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderWithSaleProductAndShopDto> findByShopIdWithSaleProductAndShop(int shopId) {
        try {
            String sql = """
                    SELECT
                        o.id,
                        o.status,
                        o.created_at,
                        o.quantity,
                        sp.id,
                        sp.name,
                        sp.description,
                        sp.price,
                        s.id,
                        s.name,
                        s.description
                    FROM orders o
                    LEFT JOIN sale_products sp on o.sale_product_id = sp.id
                    LEFT JOIN shops s on sp.shop_id = s.id
                    WHERE s.id = ? AND o.status != '배달 완료'
                    ORDER BY o.id DESC
                    """;
            PreparedStatement pstmt = Database.conn().prepareStatement(sql);
            pstmt.setInt(1, shopId);
            ResultSet rs = pstmt.executeQuery();

            return resultSetToOrderWithSaleProductAndShopDto(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderWithSaleProductAndShopDto> resultSetToOrderWithSaleProductAndShopDto(ResultSet rs) throws SQLException {
        List<OrderWithSaleProductAndShopDto> dtos = new ArrayList<>();

        while(rs.next()) {
            dtos.add(new OrderWithSaleProductAndShopDto(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getInt(4),
                    new SaleProductWithShopDto(
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            new ShopDto(
                                    rs.getInt(9),
                                    rs.getString(10),
                                    rs.getString(11)
                            )
                    )
            ));
        }

        return dtos;
    }
}

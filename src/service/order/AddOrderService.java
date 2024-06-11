package service.order;

import dao.CartItemDao;
import dao.OrderDao;
import dto.CartItemDto;
import dto.OrderDto;

import java.time.LocalDateTime;

public class AddOrderService implements Runnable {
    private Runnable before;

    public AddOrderService(Runnable before) {
        this.before = before;
    }

    @Override
    public void run() {
        CartItemDao cartItemDao = new CartItemDao();
        OrderDao orderDao = new OrderDao();
        for (CartItemDto cartItem : cartItemDao.findAll()) {
            OrderDto order = cartItemToOrder(cartItem);
            orderDao.insert(order);
        }
        cartItemDao.deleteAll();
        getBefore().run();
    }

    private OrderDto cartItemToOrder(CartItemDto cartItem) {
        return new OrderDto(
                0,
                "조리 중",
                LocalDateTime.now(),
                cartItem.getQuantity(),
                cartItem.getSaleProductId()
        );
    }

    public Runnable getBefore() {
        return before;
    }
}

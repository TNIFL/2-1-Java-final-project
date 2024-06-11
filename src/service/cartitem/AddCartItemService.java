package service.cartitem;

import dao.CartItemDao;
import dto.CartItemDto;

public class AddCartItemService implements Runnable {
    private int saleProductId;
    private int quantity;

    public AddCartItemService(int saleProductId, int quantity) {
        this.saleProductId = saleProductId;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        CartItemDao cartItemDao = new CartItemDao();
        CartItemDto cartItem = cartItemDao.findOneBySaleProductId(getSaleProductId());

        if (null != cartItem) {
            cartItem.setQuantity(cartItem.getQuantity() + getQuantity());
            cartItemDao.update(cartItem);
        } else {
            cartItem = new CartItemDto(0, getQuantity(), getSaleProductId());
            cartItemDao.insert(cartItem);
        }
    }

    public int getSaleProductId() {
        return saleProductId;
    }

    public int getQuantity() {
        return quantity;
    }
}

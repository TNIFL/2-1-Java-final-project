package service.order;

import dao.OrderDao;
import dto.OrderDto;

public class UpdateOrderStatusService implements Runnable {
    private Runnable before;
    private int id;
    private String status;

    public UpdateOrderStatusService(Runnable before, int id, String status) {
        this.before = before;
        this.id = id;
        this.status = status;
    }

    @Override
    public void run() {
        OrderDao orderDao = new OrderDao();
        OrderDto orderDto = orderDao.findOne(id);
        orderDto.setStatus(getStatus());
        orderDao.update(orderDto);
        getBefore().run();
    }

    public Runnable getBefore() {
        return before;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}

package sia.tacocloud.DAO.orderRepo;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.modules.Order;

import java.util.Date;
import java.util.List;

public interface JPAOrderRepository extends CrudRepository<Order, Long> {
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
}

package sia.tacocloud.DAO.orderRepo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import sia.tacocloud.modules.Order;
import sia.tacocloud.modules.Taco;
@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        Set<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", order.getName());
        values.put("street", order.getStreet());
        values.put("city", order.getCity());
        values.put("state", order.getState());
        values.put("zip", order.getZip());
        values.put("number", order.getNumber());
        values.put("ccExpiration", order.getCcExpiration());
        values.put("ccCvv", order.getCcCvv());
        values.put("placedAt", order.getPlacedAt());

        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
}

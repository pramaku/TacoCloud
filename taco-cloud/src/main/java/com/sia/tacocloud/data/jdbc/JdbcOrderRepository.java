// JdbcOrderRepository.java - (insert one line description here)

package com.sia.tacocloud.data.jdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sia.tacocloud.models.Order;
import com.sia.tacocloud.models.Taco;

/**
 * 
 */
@Repository
public class JdbcOrderRepository implements OrderRepository
{
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate)
    {
        orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("taco_order").usingGeneratedKeyColumns("id");
        orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("taco_order_tacos");
    }

    @Override
    public Order save(Order order)
    {
        order.setPlacedAt(new Date());
        long orderId = saveOrder(order);
        order.setId(orderId);
        for (Taco taco: order.getTacos())
        {
            saveTacoToOrder(taco.getId(), orderId);
        }
        return order;
    }

    private void saveTacoToOrder(Long tacoId, long orderId)
    {
        Map<String, Object> values = new HashMap<>();
        values.put("taco", tacoId);
        values.put("taco_order", orderId);
        orderTacoInserter.execute(values);
    }

    private long saveOrder(Order order)
    {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = mapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        return orderInserter.executeAndReturnKey(values).longValue();
    }
    
}

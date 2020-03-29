
package com.sia.tacocloud.data.jdbc;

import com.sia.tacocloud.models.Order;

/**
 * 
 */
public interface OrderRepository
{
    Order save(Order order);
}

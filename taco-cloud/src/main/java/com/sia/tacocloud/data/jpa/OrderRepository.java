
package com.sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacocloud.models.Order;

/**
 * 
 */
public interface OrderRepository extends CrudRepository<Order, Long>
{
}

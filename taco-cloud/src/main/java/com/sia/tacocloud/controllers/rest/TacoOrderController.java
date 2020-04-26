package com.sia.tacocloud.controllers.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sia.tacocloud.data.jpa.OrderRepository;
import com.sia.tacocloud.models.Order;

@RestController
@RequestMapping(path="/orders")
@CrossOrigin(origins="*")
@Profile("rest")
public class TacoOrderController
{
	Logger log = LoggerFactory.getLogger(TacoOrderController.class);
	@Autowired
	OrderRepository orderRepo;

	@GetMapping(produces="application/json")
	public Iterable<Order> getOrders()
	{
		return orderRepo.findAll();
	}

	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<Order> createTacoOrder(@RequestBody Order order)
			throws URISyntaxException
	{
		Order saved = orderRepo.save(order);
		return ResponseEntity.created(new URI("/orders/" + saved.getId())).body(saved);
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") long orderId)
	{
		try
		{
			orderRepo.deleteById(orderId);
		}
		catch(EmptyResultDataAccessException esx)
		{
			log.warn("Order {} does not exist", orderId);
		}
	}
}

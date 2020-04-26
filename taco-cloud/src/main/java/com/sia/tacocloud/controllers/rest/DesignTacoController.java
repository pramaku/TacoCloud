package com.sia.tacocloud.controllers.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sia.tacocloud.data.jpa.TacoRepository;
import com.sia.tacocloud.models.Taco;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
@Profile("rest")
public class DesignTacoController
{
	@Autowired
	TacoRepository tacoRepo;

	@GetMapping("/recent")
	public Iterable<Taco> getRecentTacos()
	{
		//PageRequest page = PageRequest.of(0, 20, Sort.by("created_at").descending());
		return tacoRepo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> getTaco(@PathVariable("id") long id)
	{
		Optional<Taco> taco = tacoRepo.findById(id);
		if (taco.isPresent())
		{
			return new ResponseEntity<>(taco.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(consumes="application/json")
	public ResponseEntity<Taco> createTaco(@RequestBody Taco taco) throws URISyntaxException
	{
		Taco saved = tacoRepo.save(taco);
		return ResponseEntity.created(new URI("/design/" + saved.getId())).body(saved);
	}
}

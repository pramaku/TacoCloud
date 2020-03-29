// TacoRepository.java - (insert one line description here)

package com.sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacocloud.models.Taco;

/**
 * 
 */
public interface TacoRepository extends CrudRepository<Taco, Long>
{
}

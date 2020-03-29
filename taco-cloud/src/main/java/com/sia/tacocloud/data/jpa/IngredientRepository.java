package com.sia.tacocloud.data.jpa;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacocloud.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>
{
}

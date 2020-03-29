package com.sia.tacocloud.data.jdbc;

import com.sia.tacocloud.models.Ingredient;

public interface IngredientRepository
{
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
}

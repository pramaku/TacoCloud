package com.sia.tacocloud.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sia.tacocloud.data.jdbc.IngredientRepository;
import com.sia.tacocloud.models.Ingredient;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public Ingredient convert(String id) {
    Optional<Ingredient> optionalIngredient = Optional.ofNullable(ingredientRepo.findById(id));
    return optionalIngredient.isPresent() ?
           optionalIngredient.get() : null;
  }

}

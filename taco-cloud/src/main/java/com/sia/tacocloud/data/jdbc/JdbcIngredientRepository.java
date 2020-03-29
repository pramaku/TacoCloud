
package com.sia.tacocloud.data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sia.tacocloud.models.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository
{
    Logger log = LoggerFactory.getLogger(JdbcIngredientRepository.class);
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll()
    {
        return jdbcTemplate.query("select id,name,type from ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id)
    {
        return jdbcTemplate.queryForObject(
                "select id,name,type from ingredient where id=?", 
                this::mapRowToIngredient, id);
    }

    public Ingredient save(Ingredient ingredient)
    {
        int rowsEffected = jdbcTemplate.update("insert into ingredient (id, name, type) values (?, ?, ?)", 
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        log.info("Ingredient {} saved, {} rows updated", ingredient, rowsEffected);
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int id) throws SQLException
    {
        return new Ingredient(
                rs.getString("id"), 
                rs.getString("name"), 
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}

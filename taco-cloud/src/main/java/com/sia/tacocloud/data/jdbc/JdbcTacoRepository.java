package com.sia.tacocloud.data.jdbc;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sia.tacocloud.models.Ingredient;
import com.sia.tacocloud.models.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository
{
    JdbcTemplate jdbc;
    private IngredientRepository ingredientRepo;
    
    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc, IngredientRepository ingredientRepo)
    {
        super();
        this.jdbc = jdbc;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Taco save(Taco taco)
    {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
//        for (String ingredientId: taco.getIngredients())
//        {
//            Ingredient ingredient = ingredientRepo.findById(ingredientId);
//            saveIngredientToTaco(tacoId, ingredient);
//        }

        for (Ingredient ingredient: taco.getIngredients())
        {
            saveIngredientToTaco(tacoId, ingredient);
        }

        return taco;
    }

    private void saveIngredientToTaco(long tacoId, Ingredient ingredient)
    {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) VALUES (?, ?)",tacoId, ingredient.getId());
    }

    private long saveTacoInfo(Taco taco)
    {
        taco.setCreaedAt(new Date());
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        pscFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscFactory
                .newPreparedStatementCreator(
                        Arrays.asList(taco.getName(), new Timestamp(taco.getCreaedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }    
}

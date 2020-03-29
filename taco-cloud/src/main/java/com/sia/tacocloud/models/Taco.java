package com.sia.tacocloud.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 */
@Entity
public class Taco
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be atleast 5 characters long")
    private String name;

    @ManyToMany
    @JoinTable(
            name="taco_ingredients",
            joinColumns= {@JoinColumn(name="taco")},
            inverseJoinColumns= {@JoinColumn(name="ingredient")}
            )
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public Taco()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Taco(String name, List<Ingredient> ingredients)
    {
        super();
        this.name = name;
        this.ingredients = ingredients;
    }
    public Taco(
            Long id,
            Date creaedAt,
            @NotNull @Size(min = 5, message = "Name must be atleast 5 characters long") String name,
            @Size(min = 1, message = "You must choose at least 1 ingredient") List<Ingredient> ingredients)
    {
        super();
        this.id = id;
        this.createdAt = creaedAt;
        this.name = name;
        this.ingredients = ingredients;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Taco other = (Taco) obj;
        if (createdAt == null)
        {
            if (other.createdAt != null)
                return false;
        }
        else if (!createdAt.equals(other.createdAt))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (ingredients == null)
        {
            if (other.ingredients != null)
                return false;
        }
        else if (!ingredients.equals(other.ingredients))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Taco [id=" + id + ", creaedAt=" + createdAt + ", name=" + name + ", ingredients=" + ingredients + "]";
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    @PrePersist
    public void createdAt()
    {
        this.createdAt = new Date();
    }

    public Date getCreaedAt()
    {
        return createdAt;
    }
    public void setCreaedAt(Date creaedAt)
    {
        this.createdAt = creaedAt;
    }    
}

package com.sia.tacocloud.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
@Table(name="Taco_Order")
public class Order
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="placed_at", nullable=false, updatable=false)
    private Date placedAt;
    
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Street is required")
    private String street;
    @NotBlank(message="City is required")
    private String city;
    @NotBlank(message="State is required")
    private String state;
    @NotBlank(message="Zip code is required")
    private String zip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity=Taco.class)
    @JoinTable(
            name="taco_order_tacos",
            joinColumns= {@JoinColumn(name="taco_order")},
            inverseJoinColumns= {@JoinColumn(name="taco")})
    private List<Taco> tacos = new ArrayList<>();

    public Order(
            Long id,
            Date placedAt,
            @NotBlank(message = "Name is required") String name,
            @NotBlank(message = "Street is required") String street,
            @NotBlank(message = "City is required") String city,
            @NotBlank(message = "State is required") String state,
            @NotBlank(message = "Zip code is required") String zip,
            @CreditCardNumber(message = "Not a valid credit card number") String ccNumber,
            @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
            @Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV)
    {
        super();
        this.id = id;
        this.placedAt = placedAt;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
    }
    public Order()
    {
        super();
        // TODO Auto-generated constructor stub
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
    public void placedAt()
    {
        this.placedAt = new Date();
    }

    public Date getPlacedAt()
    {
        return placedAt;
    }
    public void setPlacedAt(Date placedAt)
    {
        this.placedAt = placedAt;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getZip()
    {
        return zip;
    }
    public void setZip(String zip)
    {
        this.zip = zip;
    }
    public String getCcNumber()
    {
        return ccNumber;
    }
    public void setCcNumber(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }
    public String getCcExpiration()
    {
        return ccExpiration;
    }
    public void setCcExpiration(String ccExpiration)
    {
        this.ccExpiration = ccExpiration;
    }
    public String getCcCVV()
    {
        return ccCVV;
    }
    public void setCcCVV(String ccCVV)
    {
        this.ccCVV = ccCVV;
    }
    
    public List<Taco> getTacos()
    {
        return tacos;
    }
    public void setTacos(List<Taco> tacos)
    {
        this.tacos = tacos;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ccCVV == null) ? 0 : ccCVV.hashCode());
        result = prime * result + ((ccExpiration == null) ? 0 : ccExpiration.hashCode());
        result = prime * result + ((ccNumber == null) ? 0 : ccNumber.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((placedAt == null) ? 0 : placedAt.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
        Order other = (Order) obj;
        if (ccCVV == null)
        {
            if (other.ccCVV != null)
                return false;
        }
        else if (!ccCVV.equals(other.ccCVV))
            return false;
        if (ccExpiration == null)
        {
            if (other.ccExpiration != null)
                return false;
        }
        else if (!ccExpiration.equals(other.ccExpiration))
            return false;
        if (ccNumber == null)
        {
            if (other.ccNumber != null)
                return false;
        }
        else if (!ccNumber.equals(other.ccNumber))
            return false;
        if (city == null)
        {
            if (other.city != null)
                return false;
        }
        else if (!city.equals(other.city))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (placedAt == null)
        {
            if (other.placedAt != null)
                return false;
        }
        else if (!placedAt.equals(other.placedAt))
            return false;
        if (state == null)
        {
            if (other.state != null)
                return false;
        }
        else if (!state.equals(other.state))
            return false;
        if (street == null)
        {
            if (other.street != null)
                return false;
        }
        else if (!street.equals(other.street))
            return false;
        if (zip == null)
        {
            if (other.zip != null)
                return false;
        }
        else if (!zip.equals(other.zip))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "Order [id="
                + id
                + ", placedAt="
                + placedAt
                + ", name="
                + name
                + ", street="
                + street
                + ", city="
                + city
                + ", state="
                + state
                + ", zip="
                + zip
                + ", ccNumber="
                + ccNumber
                + ", ccExpiration="
                + ccExpiration
                + ", ccCVV="
                + ccCVV
                + "]";
    }

    /**
     * @param saved
     */
    public void addDesign(Taco saved)
    {
        tacos .add(saved);
    }
    
}

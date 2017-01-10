package voteforlunch.model;

import javax.persistence.*;

/**
 * Created by Котик on 06.01.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restId=:restId ORDER BY d.description DESC"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:id")
})
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity
{
    public static final String DELETE = "Dish.delete";
    public static final String ALL_SORTED = "Dish.getAllSorted";
    public static final String GET = "Dish.get";

    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "restId")
    private Integer restId;

    public Dish(String description, int price) {this.description=description; 
    this.price=price;}


    public Dish() {}

    public Dish(String description, int price, int restId) {
        this.description = description;
        this.price = price;
        this.restId=restId;

    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

}

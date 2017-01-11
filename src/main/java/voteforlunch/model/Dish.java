package voteforlunch.model;

import javax.persistence.*;

/**
 * Created by Котик on 06.01.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restId ORDER BY d.id"),
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId", referencedColumnName = "id")
    private Restaurant restaurant;

    public Dish(String description, int price) {this.description=description; 
    this.price=price;}

    public Dish() {}

    public Dish(int id, String description, int price) {
        this.id=id;
        this.description = description;
        this.price = price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

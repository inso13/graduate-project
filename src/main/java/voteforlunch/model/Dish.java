package voteforlunch.model;

import javax.persistence.*;

/**
 * Created by Котик on 06.01.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.GET, query = "SELECT r FROM Restaurant r WHERE r.id=:id")
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
    private int price;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @CollectionTable(name = "restaurants", joinColumns = @JoinColumn(name = "id"))
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Dish() {}

    public Dish(String description, int price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
       // this.restaurant = restaurant;
    }

    /*public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

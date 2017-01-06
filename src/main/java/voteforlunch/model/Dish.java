package voteforlunch.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Котик on 06.01.2017.
 */
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity
{
    private String description;
    private int price;

    public Dish() {}

    public Dish(String description, int price) {
        this.description = description;
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

    public void setPrice(int price) {
        this.price = price;
    }
}

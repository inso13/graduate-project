package voteforlunch.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Inso on 11.01.2017.
 */
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.user.id=:userId"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restId AND v.dateTime=:dateTime"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.user.id=:userId")
})
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String ALL_SORTED = "Vote.getAllSorted";
    public static final String GET = "Vote.get";

    @Column(name="datetime")
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId", referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vote() {}

    public Vote(LocalDate dateTime) {this.dateTime=dateTime;}

    public Vote(LocalDate dateTime, Restaurant restaurant, User user) {
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.user = user;

    }
}

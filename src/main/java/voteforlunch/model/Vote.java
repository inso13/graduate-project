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

    @ManyToOne
    @JoinColumn(name = "restId", referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToOne
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

    public Vote(Integer id, LocalDate dateTime) {
        super(id);
        this.dateTime = dateTime;
    }

    public Vote(Integer id, LocalDate dateTime, Restaurant restaurant, User user) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Vote vote = (Vote) o;

        if (dateTime != null ? !dateTime.equals(vote.dateTime) : vote.dateTime != null) return false;
        if (restaurant != null ? !restaurant.equals(vote.restaurant) : vote.restaurant != null) return false;
        return user != null ? user.equals(vote.user) : vote.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "dateTime=" + dateTime +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }
}

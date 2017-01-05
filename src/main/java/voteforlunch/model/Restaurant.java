package voteforlunch.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Inso on 05.01.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.dateTime DESC "),
        @NamedQuery(name = Restaurant.GET, query = "SELECT r FROM Restaurant r WHERE r.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{
    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";
    public static final String BETWEEN = "Restaurant.getBetween";
    public static final String GET = "Restaurant.get";
    private Map<String, Integer> menu;
    private LocalDateTime dateTime;

    public Map<String, Integer> getMenu() {
        return menu;
    }



    public void setMenu(Map<String, Integer> menu) {
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, Map<String, Integer> menu, LocalDateTime dateTime) {
        super(id, name);
        this.menu = menu;
        this.dateTime=dateTime;
    }

    public Restaurant() {}

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

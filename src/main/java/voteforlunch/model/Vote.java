package voteforlunch.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Inso on 11.01.2017.
 */

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @Column(name="datetime")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restId",  referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name="user_id",  referencedColumnName = "id")
    private User user;
}

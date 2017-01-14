package voteforlunch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import voteforlunch.model.Vote;
import voteforlunch.web.Restaurant.RestaurantRestController;
import voteforlunch.web.user.AdminRestController;
import voteforlunch.web.vote.VoteRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            VoteRestController voteRestController = appCtx.getBean(VoteRestController.class);
            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
            AuthorizedUser.setId(100001);
           voteRestController.create(new Vote(LocalDate.now()), 100003);
           System.out.println(voteRestController.get());
            System.out.println(voteRestController.getAllVotes(100003));
            voteRestController.delete(100012);
            System.out.println(voteRestController.getAllVotes(100003));
            System.out.println();

        }
    }
}

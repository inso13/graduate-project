package voteforlunch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import voteforlunch.model.Vote;
import voteforlunch.web.Restaurant.RestaurantAbstractController;
import voteforlunch.web.user.AdminRestController;
import voteforlunch.web.vote.VoteAbstractController;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            VoteAbstractController voteRestController = appCtx.getBean(VoteAbstractController.class);
            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            RestaurantAbstractController restaurantRestController = appCtx.getBean(RestaurantAbstractController.class);
            AuthorizedUser.setId(100001);
           voteRestController.create(new Vote(LocalDate.now()), 100003);
           System.out.println(voteRestController.get());
            System.out.println(voteRestController.getAllVotes(100003));
            voteRestController.delete();
            System.out.println(voteRestController.getAllVotes(100003));
            System.out.println();

        }
    }
}

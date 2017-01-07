package voteforlunch.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Role;
import voteforlunch.model.User;
import voteforlunch.web.Restaurant.RestaurantRestController;
import voteforlunch.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    private ConfigurableApplicationContext springContext;
    private AdminRestController adminRestController;
    private RestaurantRestController restaurantRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        adminRestController = springContext.getBean(AdminRestController.class);
        restaurantRestController = springContext.getBean(RestaurantRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User currentUser = adminRestController.get(userId);
        Set<Role> roleSet = currentUser.getRoles();

        if (roleSet.contains(Role.ROLE_ADMIN)) {

            request.setAttribute("dishes", restaurantRestController.getAllDishes(100002));
            request.setAttribute("restaurants", restaurantRestController.getAll());
            request.getRequestDispatcher("/rest_update.jsp").forward(request, response);}

        else response.sendRedirect("meals");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to users");
       // request.getRequestDispatcher("/rest_update.jsp").forward(request, response);
    }
}

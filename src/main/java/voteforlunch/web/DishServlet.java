package voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class DishServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DishServlet.class);

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        restaurantRestController = springContext.getBean(RestaurantRestController.class);
        adminRestController = springContext.getBean(AdminRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       /* String action = request.getParameter("action");
        if (action == null)
            response.sendRedirect("users");
         else if (action.equals("restaurant"))*/
        int restId = Integer.parseInt(request.getParameter("restId"));
        List<Dish> dishes = restaurantRestController.getAllDishes(restId);
        User currentUser = adminRestController.get(AuthorizedUser.id());
        Set<Role> roleSet = currentUser.getRoles();

        if (roleSet.contains(Role.ROLE_ADMIN)) {
            request.setAttribute("dishes", dishes);
            request.getRequestDispatcher("/dishes_edit.jsp").forward(request, response);
        } else {
            request.setAttribute("dishes", dishes);
            request.getRequestDispatcher("/dishes_vote.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

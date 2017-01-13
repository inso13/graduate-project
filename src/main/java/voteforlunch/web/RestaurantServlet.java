package voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.*;
import voteforlunch.web.Restaurant.RestaurantRestController;
import voteforlunch.web.user.AdminRestController;
import voteforlunch.web.vote.VoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class RestaurantServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServlet.class);

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
            String action = request.getParameter("action");
            if (action == null) {
                final Restaurant restaurant = new Restaurant(
                        request.getParameter("name"));

                if (request.getParameter("id").isEmpty()) {
                    restaurantRestController.create(restaurant);
                    LOG.info("Create {}", restaurant);
                } else {
                   restaurantRestController.update(restaurant, getId(request));
                    LOG.info("Update {}", restaurant);
                }
                response.sendRedirect("restaurants");
            }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("restaurants", restaurantRestController.getAll());
            User currentUser = adminRestController.get(AuthorizedUser.id());
            Set<Role> roleSet = currentUser.getRoles();

            if (roleSet.contains(Role.ROLE_ADMIN)) {
                request.getRequestDispatcher("/rest_edit_select.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/rest_select.jsp").forward(request, response);}
        }

         else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            restaurantRestController.delete(id);
            response.sendRedirect("restaurants");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Restaurant restaurant = "create".equals(action) ?
                    new Restaurant("New Restaurant") :
                    restaurantRestController.get(getId(request));
            request.setAttribute("restaurant", restaurant);
            request.getRequestDispatcher("/restaurant_edit_menu.jsp").forward(request, response);
        }
        else if (action.equals("vote"))
        {
            Restaurant restaurant = restaurantRestController.get(Integer.parseInt(request.getParameter("restId")));
            request.setAttribute("restaurant", restaurant);
            User user = adminRestController.get(AuthorizedUser.id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/vote.jsp").forward(request, response);
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

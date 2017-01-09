package voteforlunch.web;

import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.model.Role;
import voteforlunch.model.User;
import voteforlunch.web.Restaurant.RestaurantRestController;
import voteforlunch.web.dish.DishRestController;
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
    private DishRestController dishRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
       dishRestController = springContext.getBean(DishRestController.class);
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
        if (action == null)
        {int restId = Integer.parseInt(request.getParameter("restId"));
        List<Dish> dishes = dishRestController.getAllDishes(restId);
        User currentUser = adminRestController.get(AuthorizedUser.id());
        Set<Role> roleSet = currentUser.getRoles();
            request.setAttribute("dishes", dishes);
            request.setAttribute("restId", restId);
        if (roleSet.contains(Role.ROLE_ADMIN)) {
            request.getRequestDispatcher("/dishes_edit.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/dishes_vote.jsp").forward(request, response);}
        }
        else if (action.equals("create"))
        {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String restId = request.getParameter("restId");
            final Dish dish = new Dish(
                    request.getParameter(name),
                    Integer.parseInt(price), Integer.parseInt(restId));

            if (request.getParameter("id").isEmpty()) {
                LOG.info("Create {}", dish);
                dishRestController.create(dish);
            } else {
                LOG.info("Update {}", dish);
                dishRestController.update(dish, getId(request));
            }
            response.sendRedirect("dishes");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            LOG.info("getAll");
            int restId = Integer.parseInt(request.getParameter("restId"));
            request.setAttribute("dishes", dishRestController.
                    getAllDishes(restId));
            User currentUser = adminRestController.get(AuthorizedUser.id());
            Set<Role> roleSet = currentUser.getRoles();

            if (roleSet.contains(Role.ROLE_ADMIN)) {
                request.getRequestDispatcher("/dishes_edit.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/dishes_vote.jsp").forward(request, response);}
        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            dishRestController.delete(id);
           response.sendRedirect("dishes");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Dish dish = "create".equals(action) ?
                    new Dish("New Dish", 100) :
                   dishRestController.get(getId(request));
            request.setAttribute("dish", dish);
            request.getRequestDispatcher("/dish_edit_menu.jsp").forward(request, response);
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

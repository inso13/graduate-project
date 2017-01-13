package voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.AuthorizedUser;
import voteforlunch.model.Dish;
import voteforlunch.model.Role;
import voteforlunch.model.User;
import voteforlunch.model.Vote;
import voteforlunch.web.dish.DishRestController;
import voteforlunch.web.user.AdminRestController;
import voteforlunch.web.vote.VoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


public class VoteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(VoteServlet.class);

    private ConfigurableApplicationContext springContext;
        private VoteRestController voteRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
       voteRestController = springContext.getBean(VoteRestController.class);}

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       String action = request.getParameter("action");
        if (action.equals("confirm"))
        {
            String restId = request.getParameter("restId");
            String userId = request.getParameter("userId");
            final Vote vote = new Vote(LocalDate.now());
            request.setAttribute("restId", Integer.parseInt(restId));
            if (voteRestController.get(Integer.parseInt(userId))==null) {
                LOG.info("Create {}", vote);
                if (voteRestController.create(vote, Integer.parseInt(restId))!=null)
                    request.getRequestDispatcher("vote_success.jsp").forward(request, response);
                else request.getRequestDispatcher("vote_fail.jsp").forward(request, response);
            } else {
                LOG.info("Update {}", vote);
                Vote before = voteRestController.get(Integer.parseInt(userId));
                voteRestController.update(vote, voteRestController.get(Integer.parseInt(userId)).getId(), Integer.parseInt(request.getParameter("restId")));
                Vote after = voteRestController.get(Integer.parseInt(userId));
                if ((after.getRestaurant().getId().equals(before.getRestaurant().getId())) &&
                        after.getDateTime().equals(before.getDateTime())) request.getRequestDispatcher("vote_success.jsp").forward(request, response);
                else request.getRequestDispatcher("vote_fail.jsp").forward(request, response);
            }

        }
        if (action.equals("get"))
        {
            int restId = Integer.parseInt(request.getParameter("restId"));
            response.sendRedirect("dishes?action=get&restId="+restId);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

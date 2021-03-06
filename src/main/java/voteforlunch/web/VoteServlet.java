package voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import voteforlunch.model.Vote;
import voteforlunch.web.vote.VoteAbstractController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;


public class VoteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(VoteServlet.class);

    private ConfigurableApplicationContext springContext;
        private VoteAbstractController voteRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
       voteRestController = springContext.getBean(VoteAbstractController.class);}

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
            if (voteRestController.get()==null) {
                LOG.info("Create {}", vote);
                if (voteRestController.create(vote, Integer.parseInt(restId))!=null)
                    request.getRequestDispatcher("vote_success.jsp").forward(request, response);
                else request.getRequestDispatcher("vote_fail.jsp").forward(request, response);
            } else {
                LOG.info("Update {}", vote);

                if ((voteRestController.update(vote, voteRestController.get().getId(), Integer.parseInt(request.getParameter("restId"))))!=null)
                    request.getRequestDispatcher("vote_success.jsp").forward(request, response);
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
        String paramId = Objects.requireNonNull(request.getParameter("getId"));
        return Integer.valueOf(paramId);
    }
}

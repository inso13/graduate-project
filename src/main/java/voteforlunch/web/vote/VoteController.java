package voteforlunch.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import voteforlunch.model.Vote;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by win-7.1 on 25.01.2017.
 */
@Controller
@RequestMapping(value = "/votes")
public class VoteController extends VoteAbstractController {

    @Override
    public Vote get() {
        return super.get();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(Model model, HttpServletRequest request)
    {
        String restId = request.getParameter("restId");
        String userId = request.getParameter("userId");
        final Vote vote = new Vote(LocalDate.now());
        model.addAttribute("restId", Integer.parseInt(restId));
        if (get()==null) {
            if (super.create(vote, Integer.parseInt(restId))!=null)
                return "vote_success";
            else return "vote_fail";
        } else {
            if ((super.update(vote, get().getId(), Integer.parseInt(request.getParameter("restId"))))!=null)
                return "vote_success";
            else return "vote_fail";
        }
}}

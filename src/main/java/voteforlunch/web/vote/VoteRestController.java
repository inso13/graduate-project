package voteforlunch.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import voteforlunch.model.Dish;
import voteforlunch.model.Vote;

import java.net.URI;
import java.util.List;

import static voteforlunch.web.vote.VoteRestController.REST_URL;

/**
 * Created by Inso on 01.02.2017.
 */
@RestController
@RequestMapping(value = REST_URL)
public class VoteRestController extends VoteAbstractController {

    static final String REST_URL = "/rest/votes";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get() {
        return super.get();
    }

    @Override
    @DeleteMapping
    public void delete() {
        super.delete();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllVotes(@PathVariable("id") int restId) {
        return super.getAllVotes(restId);
    }

    @Override
    public Vote create(Vote vote, int restId) {
        return super.create(vote, restId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @RequestBody int restId) {
        Vote created = super.create(vote, restId);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uriOfNewResource);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote update(@RequestBody Vote vote, @PathVariable("id")int id, @RequestBody int restId) {
        return super.update(vote, id, restId);
    }
}

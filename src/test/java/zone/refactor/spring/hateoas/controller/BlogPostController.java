package zone.refactor.spring.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zone.refactor.spring.hateoas.annotation.EntityEndpoint;
import zone.refactor.spring.hateoas.annotation.ListingEndpoint;
import zone.refactor.spring.hateoas.contract.LinkProvider;
import zone.refactor.spring.hateoas.entity.BlogPost;
import zone.refactor.spring.hateoas.entity.BlogPostList;
import zone.refactor.spring.hateoas.exception.NotFoundException;

import java.util.Arrays;

@RestController
@RequestMapping("/posts")
public class BlogPostController {
    private final LinkProvider linkProvider;

    @Autowired
    public BlogPostController(LinkProvider linkProvider) {
        this.linkProvider = linkProvider;
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ListingEndpoint(BlogPost.class)
    public BlogPostList list() {
        return new BlogPostList(
            Arrays.asList(
                new BlogPost(
                    "1",
                    "Hello world!",
                    linkProvider
                ),
                new BlogPost(
                    "2",
                    "My second post",
                    linkProvider
                )
            ),
            linkProvider
        );
    }


    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @EntityEndpoint(BlogPost.class)
    public BlogPost get(
        @PathVariable
        String id
    ) throws NotFoundException {
        switch (id) {
            case "1":
                return new BlogPost(
                    "1",
                    "Hello world!",
                    linkProvider
                );
            case "2":
                return new BlogPost(
                    "2",
                    "My second post",
                    linkProvider
                );
            default:
                throw new NotFoundException();
        }
    }
}

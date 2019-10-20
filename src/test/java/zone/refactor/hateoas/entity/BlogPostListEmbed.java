package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BlogPostListEmbed extends Entity {
    @SuppressWarnings("WeakerAccess")
    @JsonProperty(required = true, index = 1)
    public final List<BlogPost> posts;

    public BlogPostListEmbed(List<BlogPost> posts) {
        this.posts = posts;
    }
}

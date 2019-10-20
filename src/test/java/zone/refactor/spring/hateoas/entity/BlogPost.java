package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import zone.refactor.spring.hateoas.contract.LinkProvider;
import zone.refactor.spring.hateoas.contract.SelfUpLink;

@SuppressWarnings("WeakerAccess")
public class BlogPost extends LinkedEntity<SelfUpLink> {
    @JsonProperty(required = true, index = 1)
    public final String id;
    @JsonProperty(required = true, index = 2)
    public final String title;

    public BlogPost(String id, String title, LinkProvider linkProvider) {
        super(new zone.refactor.spring.hateoas.entity.SelfUpLink(
            linkProvider.getResourceLink(BlogPost.class, id),
            linkProvider.getResourceListLink(BlogPost.class)
        ));
        this.id = id;
        this.title = title;
    }
}

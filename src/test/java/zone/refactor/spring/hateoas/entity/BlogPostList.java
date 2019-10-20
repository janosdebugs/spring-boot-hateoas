package zone.refactor.spring.hateoas.entity;

import zone.refactor.spring.hateoas.contract.LinkProvider;

import java.util.List;

public class BlogPostList extends EmbedingEntity<BlogPostListEmbed, SelfLink> {
    public BlogPostList(List<BlogPost> posts, LinkProvider linkProvider) {
        super(new BlogPostListEmbed(posts), new SelfLink(linkProvider.getResourceListLink(BlogPost.class)));
    }
}

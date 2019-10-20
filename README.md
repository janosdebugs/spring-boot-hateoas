# A better HATEOAS implementation for Spring Boot

This library was born out of my frustration with the, admittedly very generic, [HATEOAS/HAL implementation of
Spring Boot](https://spring.io/projects/spring-hateoas).

This library completely replaces the HATEOAS library of Spring Boot and can be used as a replacement, or as a 
transition package as well.

## Installation

This package can be installed from Maven Central:

```xml
<dependencies>
    <dependency>
        <groupId>zone.refactor</groupId>
        <artifactId>hateoas</artifactId>
    </dependency>
</dependencies>
```

## Usage

### Base classes

This HATEOAS library gives you very simple tools to work with. The most basic types are `Entity` and `ExceptionEntity`.
These are two base classes for all other objects that add the `@type` field to the result. This field will always
contain the simple class name, or if you have declared the Swagger `@ApiModel` property with a name, that will be used.
This helps a client decode the object to its proper representation and also helps with debugging. You can extend these
classes to your liking to get the `@type` field.

## Linked entities

Next up is the `LinkedEntity` class. This class adds the `_links` field, which should contain links such as a link
to the current object, parent objects, etc. You could add a custom link class such as this:

```java
import zone.refactor.hateoas.entity.Entity;

@SuppressWarnings("WeakerAccess")
class BlogPostLinks extends Entity {
    @ApiModelProperty(required = true)
    @JsonProperty(required = true)
    public final Link self;
    
    @ApiModelProperty(required = true)
    @JsonProperty(required = true)
    public final Link up;
    
    @ApiModelProperty(required = true)
    @JsonProperty(required = true)
    public final Link author;

    public BlogPostLinks(
        PartialLink self,
        PartialLink up,
        PartialLink author
    ) {
        this.self = self.withSelfRel();
        this.up = up.withUpRel();
        this.author = author.withRel("author");
    }
}
```

As you can see the `PartialLink` class gives you the ability to define the `rel` field as needed right where it is used,
reducing the visual clutter in other parts of your code.

Now you can use this `BlogPostLinks` class in your `BlogPost` entity:

```java
class BlogPost extends LinkedEntity<BlogPostLinks> {
    public BlogPost(String id, String authorId, LinkProvider linkProvider) {
        super(new BlogPostLinks(
            linkProvider.getResourceLink(BlogPost.class, id),
            linkProvider.getResourceListLink(BlogPost.class),
            linkProvider.getResourceLink(Author.class, authorId)
        ));
    }
}
```

Simple as that!

> **Hint:** we provide a default implementation for `LinkProvider`, but you can of course also use your own if you wish.

### Setting up the link provider

As mentioned above we provide a default link provider which reads annotations. Let's say you have the following
controller:

```java
@RestController
@RequestMapping("/posts")
class BlogController {
    @ListingEndpoint(BlogPost.class)
    @RequestMapping(
        method = RequestMethod.GET
    )
    public List<BlogPost> list() {
        //...
    }
    
    @EntityEndpoint(BlogPost.class)
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET
    )
    public BlogPost get(
        String id
    ) {
        //...
    }
}
```

In this case you can have the annotation link provider can read the `@ListingEndpoint` and `@EntityEndpoint` annotations
and provide you with the link objects to this endpoint as follows:

```java
import zone.refactor.hateoas.provider.AnnotationLinkProvider;

class MyLinks {
    public Link getSingleLink(AnnotationLinkProvider linkProvider) {
        return linkProvider.getResourcLink(BlogPost.class, "1");
    }
    
    public Link getListingLink(AnnotationLinkProvider linkProvider) {
        return linkProvider.getResourceListLink(BlogPost.class);
    }
}
```

If more parameters are required to construct the path, you can pass them as additional parameters to these methods.
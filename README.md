# A better HATEOAS implementation for Spring Boot

[![CircleCI](https://img.shields.io/circleci/build/gh/refactorzone/spring-boot-hateoas)](https://circleci.com/gh/refactorzone/spring-boot-hateoas)
[![Maven Central](https://img.shields.io/maven-central/v/zone.refactor.spring/hateoas)](https://search.maven.org/search?q=g:zone.refactor.spring%20AND%20a:hateoas)
[![Code Quality](https://img.shields.io/lgtm/grade/java/g/refactorzone/spring-boot-hateoas.svg)](https://lgtm.com/projects/g/refactorzone/spring-boot-hateoas/)
[![GitHub last commit](https://img.shields.io/github/last-commit/refactorzone/spring-boot-hateoas)](https://github.com/refactorzone/spring-boot-hateoas)
[![GitHub top language](https://img.shields.io/github/languages/top/refactorzone/spring-boot-hateoas.svg)](https://github.com/refactorzone/spring-boot-hateoas)
[![GitHub repo size](https://img.shields.io/github/repo-size/refactorzone/spring-boot-hateoas.svg)](https://github.com/refactorzone/spring-boot-hateoas)
[![GitHub issues](https://img.shields.io/github/issues/refactorzone/spring-boot-hateoas.svg)](https://github.com/refactorzone/spring-boot-hateoas/issues)
[![GitHub pull requests](https://img.shields.io/github/issues-pr/refactorzone/spring-boot-hateoas.svg)](https://github.com/refactorzone/spring-boot-hateoas/pulls)
[![GitHub](https://img.shields.io/github/license/refactorzone/spring-boot-hateoas)](https://github.com/refactorzone/spring-boot-hateoas/blob/master/LICENSE.md)

This library was born out of my frustration with the, admittedly very generic, [HATEOAS/HAL implementation of
Spring Boot](https://spring.io/projects/spring-hateoas).

This library completely replaces the HATEOAS library of Spring Boot and can be used as a replacement, or as a 
transition package as well.

## Installation

This package can be installed from Maven Central:

```xml
<dependencies>
    <dependency>
        <groupId>zone.refactor.spring</groupId>
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
import zone.refactor.spring.hateoas.entity.Entity;

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
import zone.refactor.spring.hateoas.provider.AnnotationLinkProvider;

class MyLinks {
    public Link getSingleLink(AnnotationLinkProvider linkProvider) {
        return linkProvider.getResourcecLink(BlogPost.class, "1");
    }
    
    public Link getListingLink(AnnotationLinkProvider linkProvider) {
        return linkProvider.getResourceListLink(BlogPost.class);
    }
}
```

If more parameters are required to construct the path, you can pass them as additional parameters to these methods.

## Contracts provided

This library provides a number of interfaces as contracts:

|-----|-----|
| [`Entity`](src/main/java/zone/refactor/spring/hateoas/contract/Entity.java) | A simple entity with only the `@type` field attached to signal its type. |
| [`LinkedEntity`](src/main/java/zone/refactor/spring/hateoas/contract/LinkedEntity.java) | An `Entity` that also has links attached in `_links`. |
| [`EmbeddingEntity`](src/main/java/zone/refactor/spring/hateoas/contract/EmbeddingEntity.java) | An entity that has one or more embedded objects that will be returned in the `_embedded` field. Also, a `LinkedEntity`. |
| [`Link`](src/main/java/zone/refactor/spring/hateoas/contract/Link.java) | A link to a different resource. |
| [`LinkProvider`](src/main/java/zone/refactor/spring/hateoas/contract/LinkProvider.java) | An API to generate links for a certain resource. |
| [`PartialLink`](src/main/java/zone/refactor/spring/hateoas/contract/PartialLink.java) | A link without its `rel=` attribute set, returned from a `LinkProvider`. Provides APIs to generate full links. |
| [`SelfLink`](src/main/java/zone/refactor/spring/hateoas/contract/SelfLink.java) | An object that can be used inside a `LinkedEntity` and provides one link with the `self` rel. |
| [`SelfUpLink`](src/main/java/zone/refactor/spring/hateoas/contract/SelfUpLink.java) | An object that can be used inside a `LinkedEntity` and provides link with the `self` and `up` rels. |

## Entities provided

|-----|-----|
| [`Entity`](src/main/java/zone/refactor/spring/hateoas/entity/Entity.java) | An implementation of the `Entity` contract. |
| [`LinkedEntity`](src/main/java/zone/refactor/spring/hateoas/entity/LinkedEntity.java) | An implementation of the `LinkedEntity` contract. |
| [`EmbeddingEntity`](src/main/java/zone/refactor/spring/hateoas/entity/EmbeddingEntity.java) | An implementation of the `EmbeddingEntity` contract. |
| [`Link`](src/main/java/zone/refactor/spring/hateoas/entity/Link.java) | An implementation of the `Link` contract. |
| [`PartialLink`](src/main/java/zone/refactor/spring/hateoas/entity/PartialLink.java) | An implementation of the `PartialLink` contract. |
| [`SelfLink`](src/main/java/zone/refactor/spring/hateoas/entity/SelfLink.java) | An implementation of the `SelfLink` contract |
| [`SelfUpLink`](src/main/java/zone/refactor/spring/hateoas/entity/SelfUpLink.java) | An implementation of the `SelfUpLink` contract. |
| [`ExceptionEntity`](src/main/java/zone/refactor/spring/hateoas/entity/ExceptionEntity.java) | An abstract exception class that hides the usual exception fields from the JSON output. |
| [`RuntimeExceptionEntity`](src/main/java/zone/refactor/spring/hateoas/entity/RuntimeExceptionEntity.java) | An abstract runtime exception class that hides the usual exception fields from the JSON output. |


## Controllers provided

For easier error handling in an API we also provide the
[`ErrorController`](src/main/java/zone/refactor/spring/hateoas/controller/ErrorController.java), which automatically
takes any subclasses of `ExceptionEntity` and `RuntimeExceptionEntity` and turns them into valid HTTP responses.  
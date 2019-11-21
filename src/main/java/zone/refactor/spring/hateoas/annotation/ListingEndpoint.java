package zone.refactor.spring.hateoas.annotation;

import zone.refactor.spring.hateoas.contract.Entity;

import java.lang.annotation.*;

/**
 * The ListingEndpoint annotation declares that a given method is an endpoint for a list of the specified entity. The
 * actual URL of the endpoint is read from the @RequestMapping annotations.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListingEndpoint {
    Class<? extends Entity> value();
}

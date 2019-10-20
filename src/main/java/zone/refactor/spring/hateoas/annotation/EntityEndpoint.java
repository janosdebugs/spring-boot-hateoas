package zone.refactor.spring.hateoas.annotation;

import java.lang.annotation.*;

/**
 * The EntityEndpoint annotation declares that a given method is an endpoint for a specific entity. The URL of the
 * endpoint is read from the @RequestMapping annotations and must end with the ID of the entity as a parameter.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityEndpoint {
    Class<?> value();
}

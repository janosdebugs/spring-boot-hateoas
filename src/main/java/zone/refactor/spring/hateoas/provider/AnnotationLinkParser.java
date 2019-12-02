package zone.refactor.spring.hateoas.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import zone.refactor.spring.hateoas.annotation.EntityEndpoint;
import zone.refactor.spring.hateoas.annotation.ListingEndpoint;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class AnnotationLinkParser {
    final Map<Class<?>, ParsedPath> resourcePaths = new HashMap<>();
    final Map<Class<?>, ParsedPath> listingPaths = new HashMap<>();

    @Autowired
    public AnnotationLinkParser(
        RequestMappingHandlerMapping requestMappingHandlerMapping
    ) {
        Objects.requireNonNull(requestMappingHandlerMapping);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod : handlerMethods.entrySet()) {
            Method method = handlerMethod.getValue().getMethod();
            EntityEndpoint entityEndpoint = method.getAnnotation(EntityEndpoint.class);
            ListingEndpoint listingEndpoint = method.getAnnotation(ListingEndpoint.class);
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            RequestMapping classRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);

            List<String> paths = new ArrayList<>();
            if (classRequestMapping != null && classRequestMapping.value().length > 0) {
                for (String basePath : classRequestMapping.value()) {
                    if (requestMapping != null && requestMapping.value().length > 0) {
                        for (String path : requestMapping.value()) {
                            paths.add(basePath + path);
                        }
                    } else {
                        paths.add(basePath);
                    }

                }
            } else {
                if (requestMapping != null) {
                    Collections.addAll(paths, requestMapping.value());
                }
            }

            if (entityEndpoint != null) {
                Class<?> entityClass = entityEndpoint.value();
                for (String path : paths) {
                    resourcePaths.put(entityClass, new ParsedPath(path));
                }
            }
            if (listingEndpoint != null) {
                Class<?> entityClass = listingEndpoint.value();
                for (String path : paths) {
                    listingPaths.put(entityClass, new ParsedPath(path));
                }
            }
        }
    }

}

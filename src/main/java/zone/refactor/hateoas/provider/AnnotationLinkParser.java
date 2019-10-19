package zone.refactor.hateoas.provider;

import zone.refactor.hateoas.annotation.EntityEndpoint;
import zone.refactor.hateoas.annotation.ListingEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnnotationLinkParser {
    final Map<Class<?>, ParsedPath> resourcePaths = new HashMap<>();
    final Map<Class<?>, ParsedPath> listingPaths = new HashMap<>();

    @Autowired
    public AnnotationLinkParser(
        RequestMappingHandlerMapping requestMappingHandlerMapping
    ) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod : handlerMethods.entrySet()) {
            Method method = handlerMethod.getValue().getMethod();
            EntityEndpoint entityEndoint = method.getAnnotation(EntityEndpoint.class);
            ListingEndpoint listingEndpoint = method.getAnnotation(ListingEndpoint.class);
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            RequestMapping classRequestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);

            String path = "";
            if (classRequestMapping != null) {
                path += classRequestMapping.value();
            }
            if (requestMapping != null) {
                path += requestMapping.value();
            }

            if (entityEndoint != null) {
                Class<?> entityClass = entityEndoint.value();
                resourcePaths.put(entityClass, new ParsedPath(path));
            }
            if (listingEndpoint != null) {
                Class<?> entityClass = listingEndpoint.value();
                listingPaths.put(entityClass, new ParsedPath(path));
            }
        }
    }

}

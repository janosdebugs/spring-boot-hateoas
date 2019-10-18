package at.pasztor.hateoas.provider;

import at.pasztor.hateoas.entity.LinkedEntity;
import at.pasztor.hateoas.entity.PartialLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Service
public class AnnotationLinkProvider implements LinkProvider {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public AnnotationLinkProvider(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

    }



    @Override
    public <T extends LinkedEntity<?>> PartialLink getResourceListLink(Class<T> entityClass, Object... pathParameters) {
        return null;
    }

    @Override
    public <T extends LinkedEntity<?>> PartialLink getResourceLink(Class<T> entityClass, Object id, Object... pathParameters) {
        return null;
    }
}

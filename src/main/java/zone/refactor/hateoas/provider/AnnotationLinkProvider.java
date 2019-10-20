package zone.refactor.hateoas.provider;

import zone.refactor.hateoas.contract.LinkProvider;
import zone.refactor.hateoas.contract.LinkedEntity;
import zone.refactor.hateoas.contract.PartialLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;

@Service
@RequestScope
public class AnnotationLinkProvider implements LinkProvider {
    private final String basePath;
    private final AnnotationLinkParser annotationLinkParser;

    @Autowired
    public AnnotationLinkProvider(
        HttpServletRequest request,
        AnnotationLinkParser annotationLinkParser
    ) {
        Objects.requireNonNull(request, "No request object was passed. Is your injection configuration correct?");
        Objects.requireNonNull(annotationLinkParser, "No annotation link parser was passed. Is your injection configuration correct?");

        this.annotationLinkParser = annotationLinkParser;
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getLocalPort();

        basePath = scheme + "://" + host + ((scheme.equalsIgnoreCase("http") && port != 80) || (scheme.equalsIgnoreCase("https") && port != 443)?":" + port : "");
    }

    private PartialLink get(Map<Class<?>, ParsedPath> data, Class<?> entityClass, String... pathParameters) {
        if (!data.containsKey(entityClass)) {
            throw new RuntimeException("No entity mapping found for " + entityClass.getName());
        }

        ParsedPath path = data.get(entityClass);
        return new zone.refactor.hateoas.entity.PartialLink(path.render(basePath, pathParameters));
    }

    @Override
    public <T extends LinkedEntity<?>> PartialLink getResourceListLink(Class<T> entityClass, String... pathParameters) {
        return get(this.annotationLinkParser.listingPaths, entityClass, pathParameters);
    }

    @Override
    public <T extends LinkedEntity<?>> PartialLink getResourceLink(Class<T> entityClass, String id, String... pathParameters) {
        String[] parameters = (String[]) Array.newInstance(String.class, pathParameters.length + 1);
        System.arraycopy(pathParameters, 0, parameters, 0, pathParameters.length);
        parameters[parameters.length - 1] = id;
        return get(this.annotationLinkParser.resourcePaths, entityClass, parameters);
    }
}

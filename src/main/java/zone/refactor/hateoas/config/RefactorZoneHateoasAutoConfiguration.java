package zone.refactor.hateoas.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import zone.refactor.hateoas.provider.AnnotationLinkParser;
import zone.refactor.hateoas.provider.AnnotationLinkProvider;
import zone.refactor.hateoas.contract.LinkProvider;

import javax.servlet.http.HttpServletRequest;

@Configuration
@ConditionalOnClass({AnnotationLinkProvider.class, AnnotationLinkParser.class})
@ConditionalOnMissingBean(type = "zone.refactor.hateoas.contract.LinkProvider")
public class RefactorZoneHateoasAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    AnnotationLinkParser annotationLinkParser(
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") RequestMappingHandlerMapping requestMappingHandlerMapping
    ) {
        return new AnnotationLinkParser(requestMappingHandlerMapping);
    }

    @Bean
    @ConditionalOnMissingBean
    @RequestScope
    LinkProvider linkProvider(
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") HttpServletRequest request,
        AnnotationLinkParser annotationLinkParser
    ) {
        return new AnnotationLinkProvider(request, annotationLinkParser);
    }

}

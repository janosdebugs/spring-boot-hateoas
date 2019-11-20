package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class Entity implements zone.refactor.spring.hateoas.contract.Entity {
    @SuppressWarnings("DefaultAnnotationParam")
    @ApiModelProperty(required = true, position = 0)
    @JsonProperty(value = "@type", required = true, index = 0)
    public String getType() {
        Class<? extends Entity> currentClass = this.getClass();
        ApiModel annotation = currentClass.getAnnotation(ApiModel.class);
        if (annotation != null) {
            annotation.value();
            if (!annotation.value().isEmpty()) {
                return annotation.value();
            }
        }
        return currentClass.getSimpleName();
    }
}

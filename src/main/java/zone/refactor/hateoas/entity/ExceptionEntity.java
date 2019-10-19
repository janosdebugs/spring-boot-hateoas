package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ExceptionEntity extends Exception {
    @SuppressWarnings("DefaultAnnotationParam")
    @ApiModelProperty(required = true, position = 0)
    @JsonProperty(value = "@type", required = true, index = 0)
    public String getType() {
        Class<? extends ExceptionEntity> currentClass = this.getClass();
        ApiModelProperty annotation = currentClass.getAnnotation(ApiModelProperty.class);
        if (annotation != null) {
            annotation.name();
            if (!annotation.name().isEmpty()) {
                return annotation.name();
            }
        }
        return currentClass.getSimpleName();
    }
}

package at.pasztor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Entity {
    @ApiModelProperty()
    @JsonProperty(value = "@type", required = true)
    public String getType() {
        Class<? extends Entity> currentClass = this.getClass();
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

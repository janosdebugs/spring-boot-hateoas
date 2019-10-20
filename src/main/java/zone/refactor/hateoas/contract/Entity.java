package zone.refactor.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface Entity {
    @SuppressWarnings("DefaultAnnotationParam")
    @ApiModelProperty(required = true, position = 0)
    @JsonProperty(value = "@type", required = true, index = 0)
    String getType();
}

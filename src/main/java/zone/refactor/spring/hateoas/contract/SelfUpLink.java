package zone.refactor.spring.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface SelfUpLink extends SelfLink {
    @ApiModelProperty(name = "up", value = "Link to the parent resource", required = true, position = 2)
    @JsonProperty(value = "up", required = true, index = 2)
    Link getUp();
}

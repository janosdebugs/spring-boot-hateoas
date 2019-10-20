package zone.refactor.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface SelfLink extends Entity {
    @ApiModelProperty(name = "self", value = "Link to the current resource", required = true, position = 1)
    @JsonProperty(value = "self", required = true, index = 1)
    Link getSelf();
}

package zone.refactor.spring.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface LinkedEntity<LINK_TYPE extends Entity> extends Entity {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(name = "_links", required = true, position = 999)
    @JsonProperty(value = "_links", required = true, index = 999)
    LINK_TYPE getLinks();
}

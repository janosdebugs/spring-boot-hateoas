package zone.refactor.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface LinkedEntity<LINKTYPE extends Entity> extends Entity {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(name = "_links", required = true, position = 999)
    @JsonProperty(value = "_links", required = true, index = 999)
    LINKTYPE getLinks();
}

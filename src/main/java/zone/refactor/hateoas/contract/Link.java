package zone.refactor.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface Link extends Entity {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "rel",
        value = "Relation",
        required = true,
        position = 1
    )
    @JsonProperty(required = true, index = 1)
    String getRel();

    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "href",
        value = "URL",
        required = true,
        position = 2
    )
    @JsonProperty(required = true, index = 2)
    String getHref();
}

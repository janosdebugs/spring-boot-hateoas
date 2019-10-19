package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Link extends Entity {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "rel",
        value = "Relation",
        required = true,
        position = 1
    )
    @JsonProperty(required = true, index = 1)
    public final String rel;

    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "href",
        value = "URL",
        required = true,
        position = 2
    )
    @JsonProperty(required = true, index = 2)
    public final String href;

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }
}

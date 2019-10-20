package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Link extends Entity implements zone.refactor.hateoas.contract.Link {
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final String rel;

    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final String href;

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    @Override
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "rel",
        value = "Relation",
        required = true,
        position = 1
    )
    @JsonProperty(required = true, index = 1)
    public String getRel() {
        return rel;
    }

    @Override
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(
        name = "href",
        value = "URL",
        required = true,
        position = 2
    )
    @JsonProperty(required = true, index = 2)
    public String getHref() {
        return href;
    }
}

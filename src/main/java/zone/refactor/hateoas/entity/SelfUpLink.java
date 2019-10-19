package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SelfUpLink extends SelfLink {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(name = "up", value = "Link to the parent resource", required = true, position = 2)
    @JsonProperty(value = "up", required = true, index = 2)
    public final Link up;

    public SelfUpLink(PartialLink self, PartialLink up) {
        super(self);
        this.up = up.withUpRel();
    }
}

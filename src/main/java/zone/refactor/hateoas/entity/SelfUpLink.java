package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zone.refactor.hateoas.contract.Link;

@ApiModel
public class SelfUpLink extends SelfLink implements zone.refactor.hateoas.contract.SelfUpLink {
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final Link up;

    @SuppressWarnings("WeakerAccess")
    public SelfUpLink(zone.refactor.hateoas.contract.PartialLink self, zone.refactor.hateoas.contract.PartialLink up) {
        super(self);
        this.up = up.withUpRel();
    }

    @Override
    @ApiModelProperty(name = "up", value = "Link to the parent resource", required = true, position = 2)
    @JsonProperty(value = "up", required = true, index = 2)
    public zone.refactor.hateoas.contract.Link getUp() {
        return up;
    }
}

package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zone.refactor.spring.hateoas.contract.Link;

@ApiModel
public class SelfUpLink extends SelfLink implements zone.refactor.spring.hateoas.contract.SelfUpLink {
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final Link up;

    @SuppressWarnings("WeakerAccess")
    public SelfUpLink(zone.refactor.spring.hateoas.contract.PartialLink self, zone.refactor.spring.hateoas.contract.PartialLink up) {
        super(self);
        this.up = up.withUpRel();
    }

    @Override
    @ApiModelProperty(name = "up", value = "Link to the parent resource", required = true, position = 2)
    @JsonProperty(value = "up", required = true, index = 2)
    public zone.refactor.spring.hateoas.contract.Link getUp() {
        return up;
    }
}

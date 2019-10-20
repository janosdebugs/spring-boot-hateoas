package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zone.refactor.hateoas.contract.Link;

@ApiModel
@SuppressWarnings("WeakerAccess")
public class SelfLink extends Entity implements zone.refactor.hateoas.contract.SelfLink {
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public final Link self;

    public SelfLink(zone.refactor.hateoas.contract.PartialLink self) {
        this.self = self.withSelfRel();
    }

    @Override
    @ApiModelProperty(name = "self", value = "Link to the current resource", required = true, position = 1)
    @JsonProperty(value = "self", required = true, index = 1)
    public zone.refactor.hateoas.contract.Link getSelf() {
        return self;
    }
}

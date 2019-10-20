package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zone.refactor.spring.hateoas.contract.Link;

@ApiModel
@SuppressWarnings("WeakerAccess")
public class SelfLink extends Entity implements zone.refactor.spring.hateoas.contract.SelfLink {
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public final Link self;

    public SelfLink(zone.refactor.spring.hateoas.contract.PartialLink self) {
        this.self = self.withSelfRel();
    }

    @Override
    @ApiModelProperty(name = "self", value = "Link to the current resource", required = true, position = 1)
    @JsonProperty(value = "self", required = true, index = 1)
    public zone.refactor.spring.hateoas.contract.Link getSelf() {
        return self;
    }
}

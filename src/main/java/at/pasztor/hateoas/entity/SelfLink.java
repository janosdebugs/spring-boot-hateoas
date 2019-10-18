package at.pasztor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SelfLink extends Entity {
    @ApiModelProperty(name = "self", value = "Link to the current resource", required = true, position = 1)
    @JsonProperty(value = "self", required = true, index = 1)
    public final Link self;

    public SelfLink(PartialLink self) {
        this.self = self.withSelfRel();
    }
}

package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @param <LINKTYPE> the object embedded as a list of links. The links should each be indexed with their relation in
 *                  the embedding object.
 */
public class LinkedEntity<LINKTYPE extends zone.refactor.spring.hateoas.contract.Entity>
    extends Entity
    implements zone.refactor.spring.hateoas.contract.LinkedEntity<LINKTYPE>
{
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final LINKTYPE links;

    public LinkedEntity(LINKTYPE links) {
        this.links = links;
    }

    @Override
    @ApiModelProperty(name = "_links", required = true, position = 999)
    @JsonProperty(value = "_links", required = true, index = 999)
    public LINKTYPE getLinks() {
        return links;
    }
}

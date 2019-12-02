package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @param <LINK_TYPE> the object embedded as a list of links. The links should each be indexed with their relation in
 *                  the embedding object.
 */
public class LinkedEntity<LINK_TYPE extends zone.refactor.spring.hateoas.contract.Entity>
    extends Entity
    implements zone.refactor.spring.hateoas.contract.LinkedEntity<LINK_TYPE>
{
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public final LINK_TYPE links;

    @SuppressWarnings({"WeakerAccess"})
    public LinkedEntity(LINK_TYPE links) {
        this.links = links;
    }

    @Override
    @ApiModelProperty(name = "_links", required = true, position = 999)
    @JsonProperty(value = "_links", required = true, index = 999)
    public LINK_TYPE getLinks() {
        return links;
    }
}

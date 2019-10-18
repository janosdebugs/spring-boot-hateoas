package at.pasztor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @param <LINKTYPE> the object embedded as a list of links. The links should each be indexed with their relation in
 *                  the embedding object.
 */
public class LinkedEntity<LINKTYPE extends Entity> extends Entity {
    @ApiModelProperty(name = "_links", required = true, position = 999)
    @JsonProperty(value = "_links", required = true, index = 999)
    public final LINKTYPE links;

    public LinkedEntity(LINKTYPE links) {
        this.links = links;
    }
}

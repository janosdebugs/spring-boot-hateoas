package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class EmbedEntity<EMBEDTYPE extends Entity, LINKTYPE extends Entity> extends LinkedEntity<LINKTYPE> {
    @SuppressWarnings("WeakerAccess")
    @ApiModelProperty(name = "_embedded", value = "Embedded resource", required = true, position = 1000)
    @JsonProperty(value = "_embedded", required = true, index = 1000)
    public final EMBEDTYPE embedded;

    public EmbedEntity(LINKTYPE links, EMBEDTYPE embedded) {
        super(links);
        this.embedded = embedded;
    }
}

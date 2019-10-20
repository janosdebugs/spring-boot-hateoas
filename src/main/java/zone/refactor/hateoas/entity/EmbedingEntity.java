package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class EmbedingEntity<EMBEDTYPE extends zone.refactor.hateoas.contract.Entity, LINKTYPE extends zone.refactor.hateoas.contract.Entity>
    extends LinkedEntity<LINKTYPE>
    implements zone.refactor.hateoas.contract.EmbedingEntity<EMBEDTYPE, LINKTYPE>
{
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    public final EMBEDTYPE embedded;

    @SuppressWarnings("WeakerAccess")
    public EmbedingEntity(EMBEDTYPE embedded, LINKTYPE links) {
        super(links);
        this.embedded = embedded;
    }

    @Override
    @ApiModelProperty(name = "_embedded", value = "Embedded resource", required = true, position = 1000)
    @JsonProperty(value = "_embedded", required = true, index = 1000)
    public EMBEDTYPE getEmbedded() {
        return embedded;
    }
}

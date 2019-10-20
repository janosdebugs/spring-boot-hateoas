package zone.refactor.spring.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface EmbedingEntity<EMBEDTYPE extends Entity, LINKTYPE extends Entity> extends LinkedEntity<LINKTYPE> {
    @ApiModelProperty(name = "_embedded", value = "Embedded resource", required = true, position = 1000)
    @JsonProperty(value = "_embedded", required = true, index = 1000)
    EMBEDTYPE getEmbedded();
}

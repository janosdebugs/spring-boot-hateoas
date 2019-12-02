package zone.refactor.spring.hateoas.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public interface EmbeddingEntity<EMBED_TYPE extends Entity, LINK_TYPE extends Entity> extends LinkedEntity<LINK_TYPE> {
    @ApiModelProperty(name = "_embedded", value = "Embedded resource", required = true, position = 1000)
    @JsonProperty(value = "_embedded", required = true, index = 1000)
    EMBED_TYPE getEmbedded();
}

package zone.refactor.spring.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class EmbeddingEntity<EMBED_TYPE extends zone.refactor.spring.hateoas.contract.Entity, LINK_TYPE extends zone.refactor.spring.hateoas.contract.Entity>
    extends LinkedEntity<LINK_TYPE>
    implements zone.refactor.spring.hateoas.contract.EmbeddingEntity<EMBED_TYPE, LINK_TYPE>
{
    @SuppressWarnings("WeakerAccess")
    @JsonIgnore
    public final EMBED_TYPE embedded;

    @SuppressWarnings("WeakerAccess")
    public EmbeddingEntity(EMBED_TYPE embedded, LINK_TYPE links) {
        super(links);
        this.embedded = embedded;
    }

    @Override
    @ApiModelProperty(name = "_embedded", value = "Embedded resource", required = true, position = 1000)
    @JsonProperty(value = "_embedded", required = true, index = 1000)
    public EMBED_TYPE getEmbedded() {
        return embedded;
    }
}

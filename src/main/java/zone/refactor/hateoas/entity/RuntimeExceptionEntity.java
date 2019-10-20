package zone.refactor.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@JsonIgnoreProperties(value = {
    "detailMessage",
    "cause",
    "stackTrace",
    "suppressedExceptions",
    "backtrace",
    "message",
    "localizedMessage",
    "suppressed"
})
public class RuntimeExceptionEntity extends RuntimeException implements zone.refactor.hateoas.contract.Entity {
    @SuppressWarnings("DefaultAnnotationParam")
    @ApiModelProperty(required = true, position = 0)
    @JsonProperty(value = "@type", required = true, index = 0)
    public String getType() {
        Class<? extends RuntimeExceptionEntity> currentClass = this.getClass();
        ApiModelProperty annotation = currentClass.getAnnotation(ApiModelProperty.class);
        if (annotation != null) {
            annotation.name();
            if (!annotation.name().isEmpty()) {
                return annotation.name();
            }
        }
        return currentClass.getSimpleName();
    }

    //region Hide fields from Swagger and JSON
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      String                        detailMessage;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      String                        localizedMessage;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      String                        message;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      Throwable                     cause;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      StackTraceElement[]           stackTrace;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private List<Throwable> suppressedExceptions;
    @ApiModelProperty(
        hidden = true
    )
    @JsonIgnore
    private      List<?>                       suppressed;
    //endregion
}

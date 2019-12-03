package zone.refactor.spring.hateoas.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import zone.refactor.spring.hateoas.entity.ExceptionEntity;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@ApiModel
public class NotFoundException extends ExceptionEntity {
    @ResponseHeader(name = "X-Response-Type")
    public String testHeader() {
        return "NotFoundException";
    }
}

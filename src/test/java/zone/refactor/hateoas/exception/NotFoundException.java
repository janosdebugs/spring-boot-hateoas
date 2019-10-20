package zone.refactor.hateoas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import zone.refactor.hateoas.entity.ExceptionEntity;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends ExceptionEntity {
}

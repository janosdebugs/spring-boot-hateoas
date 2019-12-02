package zone.refactor.spring.hateoas.controller;

import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import zone.refactor.spring.hateoas.entity.ExceptionEntity;
import zone.refactor.spring.hateoas.entity.RuntimeExceptionEntity;

import java.lang.reflect.Method;

@Controller
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = { ExceptionEntity.class })
    public <T extends ExceptionEntity> ResponseEntity<T> onException(T apiException) {
        ResponseStatus status = apiException.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (status != null) {
            statusCode = status.code();
        }
        return new ResponseEntity<>(
            apiException,
            statusCode
        );
    }

    @ExceptionHandler(value = { RuntimeExceptionEntity.class })
    public <T extends RuntimeExceptionEntity> ResponseEntity<T> onException(T apiException) {
        ResponseStatus status = apiException.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (status != null) {
            statusCode = status.code();
        }
        return new ResponseEntity<>(
            apiException,
            statusCode
        );
    }
}

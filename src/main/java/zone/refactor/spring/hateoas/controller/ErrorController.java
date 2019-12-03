package zone.refactor.spring.hateoas.controller;

import io.swagger.annotations.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import zone.refactor.spring.hateoas.entity.ExceptionEntity;
import zone.refactor.spring.hateoas.entity.RuntimeExceptionEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@ControllerAdvice
public class ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    private HttpStatus getResponseStatus(Object entity) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus statusAnnotation = entity.getClass().getAnnotation(ResponseStatus.class);
        if (statusAnnotation != null) {
            status = statusAnnotation.code();
        }
        for (Method method : entity.getClass().getMethods()) {
            statusAnnotation = method.getAnnotation(ResponseStatus.class);
            if (statusAnnotation != null) {
                if (method.getParameters().length > 0) {
                    logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it has more than one parameter.");
                } else if (!Modifier.isPublic(method.getModifiers())) {
                    logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it is not public.");
                } else if (Modifier.isAbstract(method.getModifiers())) {
                    logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it is abstract.");
                } else {
                    try {
                        Object methodResponse;
                        if (Modifier.isStatic(method.getModifiers())) {
                            methodResponse = method.invoke(null);
                        } else {
                            methodResponse = method.invoke(entity);
                        }
                        if (methodResponse == null) {
                            logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it returned null.");
                        } else if (int.class.isAssignableFrom(methodResponse.getClass())) {
                            status = HttpStatus.valueOf((int) methodResponse);
                        } else if (Integer.class.isAssignableFrom(methodResponse.getClass())) {
                            status = HttpStatus.valueOf((Integer) methodResponse);
                        } else if (short.class.isAssignableFrom(methodResponse.getClass())) {
                            status = HttpStatus.valueOf((short) methodResponse);
                        } else if (Short.class.isAssignableFrom(methodResponse.getClass())) {
                            status = HttpStatus.valueOf((Short) methodResponse);
                        } else if (HttpStatus.class.isAssignableFrom(methodResponse.getClass())) {
                            status = (HttpStatus) methodResponse;
                        } else {
                            logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it returned an unsupported type: " + methodResponse.getClass().getSimpleName());
                        }
                    } catch (IllegalAccessException e) {
                        logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it threw an IllegalAccessException.", e);
                    } catch (InvocationTargetException e) {
                        logger.warn("Cannot fetch HTTP status from " + entity.getClass().getSimpleName() + "#" + method.getName() + "() because it threw an exception.", e.getCause());
                    }
                }
            }
        }
        return status;
    }

    private MultiValueMap<String, String> getHeaders(Object entity) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        for (Method method : entity.getClass().getMethods()) {
            ResponseHeader responseHeader = method.getAnnotation(ResponseHeader.class);
            if (responseHeader == null) {
                continue;
            }
            String headerName = responseHeader.name();
            if (headerName.isEmpty()) {
                headerName = method.getName();
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                logger.warn("Cannot fetch header from " + entity.getClass().getSimpleName() + "#" + method + "() because it is not public.");
                continue;
            }
            if (Modifier.isAbstract(method.getModifiers())) {
                logger.warn("Cannot fetch header from " + entity.getClass().getSimpleName() + "#" + method + "() because it is abstract.");
                continue;
            }
            if (method.getParameters().length != 0) {
                logger.warn("Cannot fetch header from " + entity.getClass().getSimpleName() + "#" + method + "() because it has parameters.");
                continue;
            }
            Object value;
            try {
                if (Modifier.isStatic(method.getModifiers())) {
                    value = method.invoke(null);
                } else {
                    value = method.invoke(entity);
                }
            } catch (IllegalAccessException e) {
                //Should never happen since we checked.
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                logger.warn("Cannot fetch header from " + entity.getClass().getSimpleName() + "#" + method + "() because it threw an exception.", e.getCause());
                continue;
            }
            if (Map.class.isAssignableFrom(method.getReturnType())) {
                //Assume a set of headers
                //noinspection unchecked
                headers.putAll((Map<? extends String, ? extends List<String>>) value);
            } else if (List.class.isAssignableFrom(method.getReturnType())) {
                //Assume list of values
                //noinspection unchecked
                headers.put(headerName, (List<String>) ((List)value).stream().map(Object::toString).collect(Collectors.toList()));
            } else {
                //Assume a single value
                headers.put(headerName, Collections.singletonList(value.toString()));
            }
        }

        return headers;
    }

    @ExceptionHandler(value = { ExceptionEntity.class })
    public <T extends ExceptionEntity> ResponseEntity<T> onException(T apiException) {
        return new ResponseEntity<>(
            apiException,
            getHeaders(apiException),
            getResponseStatus(apiException)
        );
    }

    @ExceptionHandler(value = { RuntimeExceptionEntity.class })
    public <T extends RuntimeExceptionEntity> ResponseEntity<T> onException(T apiException) {
        return new ResponseEntity<>(
            apiException,
            getHeaders(apiException),
            getResponseStatus(apiException)
        );
    }
}

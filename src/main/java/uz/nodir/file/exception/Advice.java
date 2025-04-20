package uz.nodir.file.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import uz.nodir.file.model.dto.core.response.ResultData;

import java.sql.SQLException;

/**
 * Created by Nodir
 * on Date 09 янв., 2024
 * Java Spring Boot by Davr Coders
 */
@RestControllerAdvice
@Slf4j
public class Advice {


    @ExceptionHandler(value = {GatewayException.class, GeneralErrorException.class})
    public ResponseEntity<?> thirdPartyErrors(RuntimeException e) {
        log.info("Advice catch Third party error: {}", e.getMessage());
        return ResponseEntity.ok(new ResultData<>(101, e.getMessage()));
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<?> badRequestErrors(Exception e) {
        log.info("Web error: {}", e.getMessage());
        return ResponseEntity.ok(new ResultData<>(102, "Bad request or fields wrong filled!"));
    }

    @ExceptionHandler(value = {
            InvalidDataException.class,
            EntityNotFoundException.class,
            InvalidDataException.class,
            SQLException.class,
            MissingServletRequestPartException.class
    })
    public ResponseEntity<?> backErrors(Exception e) {
        log.info("Back end catch error: {}", e.getMessage());
        return ResponseEntity.ok(new ResultData<>(103, e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(RuntimeException e) {
        log.info("Bad Request exception: {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ResultData<>(105, e.getMessage()));
    }

}

package org.ml.shipping.configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.ml.shipping.exception.DepositLimitException;
import org.ml.shipping.exception.ProductMaxTypesReachException;
import org.ml.shipping.exception.ProductNotFoundException;
import org.ml.shipping.exception.ProductNotValidException;
import org.ml.shipping.response.ProductStockResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@ControllerAdvice
public class ExceptionHandlerConfiguration {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @JsonIgnoreProperties(value = {"data"})
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @JsonIgnoreProperties(value = {"data"})
    public ProductStockResponse productNotFoundException(ProductNotFoundException ex) {
        return new ProductStockResponse(404, ex.getMessage(), null);
    }

    @ResponseStatus(PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ProductMaxTypesReachException.class)
    @JsonIgnoreProperties(value = {"data"})
    public ProductStockResponse productMaxTypesReachException(ProductMaxTypesReachException ex) {
        return new ProductStockResponse(412, ex.getMessage(), null);
    }

    @ResponseStatus(PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ProductNotValidException.class)
    @JsonIgnoreProperties(value = {"data"})
    public ProductStockResponse productNotValidException(ProductNotValidException ex) {
        return new ProductStockResponse(412, ex.getMessage(), null);
    }

    @ResponseStatus(PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(DepositLimitException.class)
    @JsonIgnoreProperties(value = {"data"})
    public ProductStockResponse depositLimitException(DepositLimitException ex) {
        return new ProductStockResponse(412, ex.getMessage(), null);
    }


    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(BAD_REQUEST.value(), "validation error");
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String path, String message) {
            FieldError error = new FieldError(path, message, "");
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }
}

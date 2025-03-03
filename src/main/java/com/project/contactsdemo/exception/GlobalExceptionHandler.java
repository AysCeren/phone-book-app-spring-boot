package com.project.contactsdemo.exception;

import com.project.contactsdemo.dto.GenericDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private GenericDTO errorGenericDTO =new GenericDTO(null,1);

    @ExceptionHandler({NoDataFoundException.class})
    public ResponseEntity<GenericDTO> handleDataNotFoundException(NoDataFoundException exception) {
        errorGenericDTO.setErrorMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorGenericDTO);
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<GenericDTO> handleRuntimeException(RuntimeException exception) {
        errorGenericDTO.setErrorMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorGenericDTO);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<GenericDTO>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<GenericDTO> genericDTOList = new ArrayList<>();
        //ex.getErrorCount();
        String error;
        for(int i =0; i<ex.getBindingResult().getAllErrors().size(); i++){
            error = ex.getBindingResult().getAllErrors().get(i).getDefaultMessage();
            errorGenericDTO.setErrorMessage(error);
            genericDTOList.add(errorGenericDTO);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(genericDTOList);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public List<ResponseEntity<GenericDTO>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
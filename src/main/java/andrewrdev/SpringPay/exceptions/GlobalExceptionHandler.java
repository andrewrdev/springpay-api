package andrewrdev.SpringPay.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import andrewrdev.SpringPay.responses.CustomResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // --------------------------------------------------------------------------------------------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        CustomResponse response = new CustomResponse(errorMessage, "BAD_REQUEST", 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // --------------------------------------------------------------------------------------------

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Insert valid data types: " + ex.getMessage();
        CustomResponse response = new CustomResponse(errorMessage,"BAD_REQUEST", 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // --------------------------------------------------------------------------------------------

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleNotFoundException(NotFoundException ex) {
        String errorMessage = ex.getMessage();
        CustomResponse response = new CustomResponse(errorMessage, "NOT_FOUND", 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // --------------------------------------------------------------------------------------------

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleGenericException(Exception ex) {
        String errorMessage = "INTERNAL_SERVER_ERROR";
        CustomResponse response = new CustomResponse(errorMessage, "INTERNAL_SERVER_ERROR", 500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }   
    
    // --------------------------------------------------------------------------------------------
}

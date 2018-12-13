package alimentum.alimentum.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(value = Exception.class)
  public String handleException(Exception exception){
    System.out.println("Exception: " + exception);
    return "exception";
  }
}

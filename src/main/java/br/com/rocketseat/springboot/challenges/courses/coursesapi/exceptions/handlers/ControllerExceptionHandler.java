package br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.handlers;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.AllFieldsNullException;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.CourseNoteFoundException;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.dtos.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  private final MessageSource messageSource;

  @Autowired
  public ControllerExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    List<ErrorMessageDTO> dto = new ArrayList<>();

    exception.getBindingResult().getFieldErrors().forEach(err -> {
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      var error = new ErrorMessageDTO(message, err.getField());
      dto.add(error);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CourseNoteFoundException.class)
  public ResponseEntity<?> handleCourseNotFoundException(CourseNoteFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @ExceptionHandler(AllFieldsNullException.class)
  public ResponseEntity<?> handleAllFieldsNullException(AllFieldsNullException exception) {
    var fields = Arrays.stream(exception.getFields()).reduce((acc, value) -> {
      return acc + "," + value;
    }).orElse("");
    var error = new ErrorMessageDTO(exception.getMessage(), fields);
    
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

}

package br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions;

import lombok.Getter;

@Getter
public class AllFieldsNullException extends BaseException {

  private final String[] fields;

  public AllFieldsNullException(String message, String[] fields) {
    super(message);
    this.fields = fields;
  }
}

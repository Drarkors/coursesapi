package br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions;

public class CourseNoteFoundException extends BaseException {

  public CourseNoteFoundException() {
    super("Course not found");
  }

}

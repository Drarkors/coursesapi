package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseStatus {
  ACTIVE("active"),
  INACTIVE("inactive");

  private final String value;

  public CourseStatus toogleStatus() {
    return this.ordinal() == 0 ? INACTIVE : ACTIVE;
  }
}

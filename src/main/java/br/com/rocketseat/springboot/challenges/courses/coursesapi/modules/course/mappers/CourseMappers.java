package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.mappers;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CourseDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CreateCourseRequestDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.entities.Course;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.enums.CourseStatus;

public class CourseMappers {

  public static CourseDTO domainToHttp(Course course) {
    return CourseDTO.builder()
        .id(course.getId())
        .nome(course.getName())
        .categoria(course.getCategory())
        .status(course.getStatus())
        .build();
  }

  public static Course httpToDomain(CreateCourseRequestDTO createCourseRequest) {
    return Course.builder()
        .name(createCourseRequest.name())
        .category(createCourseRequest.category())
        .status(CourseStatus.ACTIVE)
        .build();
  }
}

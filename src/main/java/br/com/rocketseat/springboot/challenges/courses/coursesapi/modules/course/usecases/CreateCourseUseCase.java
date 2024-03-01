package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CourseDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CreateCourseRequestDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.mappers.CourseMappers;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {
  private final CourseRepository courseRepository;

  @Autowired
  public CreateCourseUseCase(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public CourseDTO execute(CreateCourseRequestDTO createCourseRequest) {
    var course = this.courseRepository.save(CourseMappers.httpToDomain(createCourseRequest));

    return CourseMappers.domainToHttp(course);
  }
}

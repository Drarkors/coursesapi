package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.CourseNoteFoundException;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CourseDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.mappers.CourseMappers;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ToggleStatusCourseUseCase {

  private final CourseRepository courseRepository;

  @Autowired
  public ToggleStatusCourseUseCase(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public CourseDTO execute(UUID id) {
    var course = this.courseRepository.findById(id).orElseThrow(CourseNoteFoundException::new);

    course.setStatus(course.getStatus().toogleStatus());

    return CourseMappers.domainToHttp(this.courseRepository.save(course));
  }
}

package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.CourseNoteFoundException;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CourseDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.UpdateCourseRequestDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.mappers.CourseMappers;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCourseUseCase {

  private final CourseRepository courseRepository;

  @Autowired
  public UpdateCourseUseCase(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public CourseDTO execute(UUID id, UpdateCourseRequestDTO updateCourseRequestDTO) {
    var course = this.courseRepository.findById(id).orElseThrow(CourseNoteFoundException::new);

    if (updateCourseRequestDTO.name() != null)
      course.setName(updateCourseRequestDTO.name());

    if (updateCourseRequestDTO.category() != null)
      course.setCategory(updateCourseRequestDTO.category());

    return CourseMappers.domainToHttp(this.courseRepository.save(course));
  }

}

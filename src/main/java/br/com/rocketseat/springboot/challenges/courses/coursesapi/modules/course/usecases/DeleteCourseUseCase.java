package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.CourseNoteFoundException;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseUseCase {

  private final CourseRepository courseRepository;

  @Autowired
  public DeleteCourseUseCase(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public void execute(UUID id) {
    this.courseRepository.findById(id).orElseThrow(CourseNoteFoundException::new);
    this.courseRepository.deleteById(id);
  }
}

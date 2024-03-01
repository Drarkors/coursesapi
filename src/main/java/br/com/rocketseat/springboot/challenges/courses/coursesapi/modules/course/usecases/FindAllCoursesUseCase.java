package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.FindAllCoursesResponseDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.mappers.CourseMappers;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAllCoursesUseCase {
  private final CourseRepository courseRepository;

  @Autowired
  public FindAllCoursesUseCase(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public FindAllCoursesResponseDTO execute() {
    var courses = this.courseRepository.findAll();

    return FindAllCoursesResponseDTO.builder()
        .cursos(courses.stream().map(CourseMappers::domainToHttp).toList())
        .build();
  }
}

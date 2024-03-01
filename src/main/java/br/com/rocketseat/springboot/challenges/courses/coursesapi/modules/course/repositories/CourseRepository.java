package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.repositories;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
  
}

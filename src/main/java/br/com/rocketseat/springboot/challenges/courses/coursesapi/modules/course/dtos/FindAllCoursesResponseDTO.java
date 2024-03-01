package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record FindAllCoursesResponseDTO(List<CourseDTO> cursos) {
}

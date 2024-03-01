package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.enums.CourseStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseDTO(
    UUID id,
    String nome,
    String categoria,
    CourseStatus status
) {
}

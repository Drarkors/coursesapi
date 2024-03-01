package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CreateCourseRequestDTO(@NotEmpty(message = "O campo [name] deve ser informado") String name,
                                     @NotEmpty(message = "O campo [category] deve ser informado") String category) {
}

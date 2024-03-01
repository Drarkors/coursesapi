package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.validations.annotations.CheckAtLeastOneNotNull;

@CheckAtLeastOneNotNull(fieldNames = {"name", "category"}, message = "Pelo menos um dos campos deve ser informado")
public record UpdateCourseRequestDTO(String name, String category) {
}

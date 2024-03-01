package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.controllers;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.CreateCourseRequestDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.dtos.UpdateCourseRequestDTO;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases.CreateCourseUseCase;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases.DeleteCourseUseCase;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases.FindAllCoursesUseCase;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases.ToggleStatusCourseUseCase;
import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.usecases.UpdateCourseUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/cursos")
public class CourseController {

  private final CreateCourseUseCase createCourseUseCase;
  private final FindAllCoursesUseCase findAllCoursesUseCase;
  private final UpdateCourseUseCase updateCourseUseCase;
  private final ToggleStatusCourseUseCase toggleStatusCourseUseCase;
  private final DeleteCourseUseCase deleteCourseUseCase;

  @Autowired
  public CourseController(CreateCourseUseCase createCourseUseCase,
                          FindAllCoursesUseCase findAllCoursesUseCase,
                          UpdateCourseUseCase updateCourseUseCase,
                          ToggleStatusCourseUseCase toggleStatusCourseUseCase,
                          DeleteCourseUseCase deleteCourseUseCase) {
    this.createCourseUseCase = createCourseUseCase;
    this.findAllCoursesUseCase = findAllCoursesUseCase;
    this.updateCourseUseCase = updateCourseUseCase;
    this.toggleStatusCourseUseCase = toggleStatusCourseUseCase;
    this.deleteCourseUseCase = deleteCourseUseCase;
  }

  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody @Valid CreateCourseRequestDTO createCourseRequest) {
    var result = this.createCourseUseCase.execute(createCourseRequest);
    return ResponseEntity.ok(result);
  }

  @GetMapping("")
  public ResponseEntity<?> findAll() {
    var result = this.findAllCoursesUseCase.execute();
    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid UpdateCourseRequestDTO updateCourseRequest) {
    var result = this.updateCourseUseCase.execute(UUID.fromString(id), updateCourseRequest);
    return ResponseEntity.ok(result);
  }

  @PatchMapping("/{id}/active")
  public ResponseEntity<?> activation(@PathVariable String id) {
    var result = this.toggleStatusCourseUseCase.execute(UUID.fromString(id));
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    this.deleteCourseUseCase.execute(UUID.fromString(id));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

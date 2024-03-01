package br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.entities;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.modules.course.enums.CourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "course")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "category"})})
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  @NotEmpty(message = "O campo [name] deve ser informado")
  private String name;

  @Column
  @NotEmpty(message = "O campo [category] deve ser informado")
  private String category;

  @Column
  @Enumerated(EnumType.STRING)
  private CourseStatus status;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

}

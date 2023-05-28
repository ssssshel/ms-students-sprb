package com.tests.ms_students.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_course", schema = "tests")
public class StudentCourseModel {
  @Id
  @Column(name = "id_student")
  private Long idStudent;

  @Column(name = "id_course")
  private Long idCourse;
}
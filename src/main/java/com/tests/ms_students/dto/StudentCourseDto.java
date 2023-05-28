package com.tests.ms_students.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentCourseDto {
  private Long id_student;
  private Long id_course;
}

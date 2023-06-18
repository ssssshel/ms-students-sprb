package com.tests.ms_students.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentCourseDto {
  private Long id_student;
  private String student_name;
  private Boolean student_state;
  private Long id_course;
  private String course_name;
}

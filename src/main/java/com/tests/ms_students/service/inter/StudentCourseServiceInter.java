package com.tests.ms_students.service.inter;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentCourseDto;

public interface StudentCourseServiceInter {
  public ResponseDto getAllStudentCourse();

  public ResponseDto getStudentCourse(Long id);

  public ResponseDto createStudentCourse(StudentCourseDto studentCourse);
}

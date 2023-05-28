package com.tests.ms_students.service.inter;

import com.tests.ms_students.dto.ResponseDto;

public interface StudentCourseServiceInter {
  public ResponseDto getAllStudentCourse();

  public ResponseDto getStudentCourse(Long id);
}

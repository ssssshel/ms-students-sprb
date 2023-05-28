package com.tests.ms_students.service.inter;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentDto;

public interface StudentServiceInter {
  public ResponseDto getAllStudents();

  public ResponseDto getStudentById(Long id);

  public ResponseDto createStudent(StudentDto student);

  public ResponseDto updateStudent(StudentDto student);

  public ResponseDto deleteStudent(Long id);
}

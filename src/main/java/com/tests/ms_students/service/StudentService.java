package com.tests.ms_students.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentDto;
import com.tests.ms_students.model.StudentModel;
import com.tests.ms_students.repository.StudentRepository;
import com.tests.ms_students.service.inter.StudentServiceInter;
import com.tests.ms_students.shared.Utils;

public class StudentService implements StudentServiceInter {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public ResponseDto getAllStudents() {
    try {
      List<StudentModel> studentsList = studentRepository.findAll();
      if (studentsList.isEmpty()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, studentsList, false);
      }

      List<StudentDto> studentDto = new ArrayList<StudentDto>();
      for (StudentModel studentObject : studentsList) {
        studentDto.add(StudentDto.builder()
            .id(studentObject.getId())
            .name(studentObject.getName())
            .surname(studentObject.getSurname())
            .gender(studentObject.getGender())
            .state(studentObject.getState())
            .build());
      }
      return Utils.getResponse(HttpStatus.OK, studentDto, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getStudentById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseDto createStudent(StudentDto student) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseDto updateStudent(StudentDto student) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResponseDto deleteStudent(Long id) {
    // TODO Auto-generated method stub
    return null;
  }
}

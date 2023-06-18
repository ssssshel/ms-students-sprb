package com.tests.ms_students.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentDto;
import com.tests.ms_students.model.StudentModel;
import com.tests.ms_students.repository.StudentRepository;
import com.tests.ms_students.service.inter.StudentServiceInter;
import com.tests.ms_students.shared.Utils;

@Service
public class StudentService implements StudentServiceInter {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public ResponseDto getAllStudents() {
    System.out.println("getAllStudents");
    try {
      List<StudentModel> studentsList = studentRepository.findAll();
      if (studentsList.isEmpty()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, studentsList, false);
      }

      List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
      for (StudentModel studentObject : studentsList) {
        studentDtoList.add(StudentDto.builder()
            .id(studentObject.getId())
            .name(studentObject.getName())
            .surname(studentObject.getSurname())
            .gender(studentObject.getGender())
            .state(studentObject.getState())
            .build());
      }
      return Utils.getResponse(HttpStatus.OK, studentDtoList, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getStudentById(Long id) {
    try {
      StudentModel studentModel = studentRepository.findById(id).orElse(null);
      if (studentModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }
      StudentDto studentDto = StudentDto.builder()
          .id(studentModel.getId())
          .name(studentModel.getSurname())
          .surname(studentModel.getSurname())
          .gender(studentModel.getGender())
          .state(studentModel.getState())
          .build();

      return Utils.getResponse(HttpStatus.OK, studentDto, true);

    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto createStudent(StudentDto student) {
    try {
      StudentModel studentModel = StudentModel.builder()
          .name(student.getName())
          .surname(student.getSurname())
          .gender(student.getGender())
          .state(student.getState())
          .build();

      studentRepository.save(studentModel);
      student.setId(studentModel.getId());
      return Utils.getResponse(HttpStatus.CREATED, studentModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto updateStudent(StudentDto student) {
    try {
      System.out.println(student.getId());
      StudentModel studentModel = studentRepository.findById(student.getId()).orElse(null);
      if (studentModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }
      studentModel.setName(student.getName());
      studentModel.setSurname(student.getSurname());
      studentModel.setGender(student.getGender());
      studentRepository.save(studentModel);
      return Utils.getResponse(HttpStatus.OK, studentModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }

  }

  @Override
  public ResponseDto deleteStudent(Long id) {
    try {
      StudentModel studentModel = studentRepository.findById(id).orElse(null);
      studentModel.setState(false);
      studentRepository.save(studentModel);
      return Utils.getResponse(HttpStatus.OK, studentModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }
}

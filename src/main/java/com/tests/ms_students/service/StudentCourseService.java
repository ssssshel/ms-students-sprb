package com.tests.ms_students.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests.ms_students.client.CourseClient;
import com.tests.ms_students.dto.CourseDto;
import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentCourseDto;
import com.tests.ms_students.model.StudentCourseModel;
import com.tests.ms_students.model.StudentModel;
import com.tests.ms_students.repository.StudentCourseRespository;
import com.tests.ms_students.repository.StudentRepository;
import com.tests.ms_students.service.inter.StudentCourseServiceInter;
import com.tests.ms_students.shared.Utils;

import feign.RetryableException;

@Service
public class StudentCourseService implements StudentCourseServiceInter {
  @Autowired
  private StudentCourseRespository studentCourseRespository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseClient courseClient;

  @Override
  public ResponseDto getAllStudentCourse() {
    ObjectMapper mapper = new ObjectMapper();

    try {
      List<StudentCourseModel> studentCourseModels = studentCourseRespository.findAll();

      List<StudentCourseDto> studentCourseDtos = new ArrayList<StudentCourseDto>();

      for (int i = 0; i < studentCourseModels.size(); i++) {
        StudentModel studentModel = studentRepository.findById(studentCourseModels.get(i).getIdStudent()).orElse(null);

        ResponseDto responseDto = courseClient.getCourseById(studentCourseModels.get(i).getIdCourse());
        CourseDto courseDto = mapper.convertValue(responseDto.getData(), CourseDto.class);

        studentCourseDtos.add(StudentCourseDto.builder()
            .id_student(studentModel.getId())
            .student_name(studentModel.getName() + " " + studentModel.getSurname())
            .student_state(studentModel.getState())
            .id_course(courseDto.getId())
            .course_name(courseDto.getName())
            .build());
      }
      ;

      return Utils.getResponse(HttpStatus.OK, studentCourseDtos, true);

    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getStudentCourse(Long id) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      StudentCourseModel studentCourseModel = studentCourseRespository.findByidStudent(id);

      if (studentCourseModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, "Student not found", false);
      }

      StudentModel studentModel = studentRepository.findById(studentCourseModel.getIdStudent()).orElse(null);

      ResponseDto responseDto = courseClient.getCourseById(studentCourseModel.getIdCourse());

      CourseDto courseDto = mapper.convertValue(responseDto.getData(), CourseDto.class);

      StudentCourseDto studentCourseDto = StudentCourseDto.builder()
          .id_student(studentModel.getId())
          .student_name(studentModel.getName() + " " + studentModel.getSurname())
          .student_state(studentModel.getState())
          .id_course(courseDto.getId())
          .course_name(courseDto.getName())
          .build();

      return Utils.getResponse(HttpStatus.OK, studentCourseDto, true);

    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto createStudentCourse(StudentCourseDto studentCourse) {
    try {

      ResponseDto responseDto = courseClient.getCourseById(studentCourse.getId_course());

      if (responseDto.isError()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, "Course not found", false);
      }

      // StudentModel studentModel =
      // studentRepository.findById(studentCourse.getId_student()).orElse(null);

      // if (studentModel == null) {
      // return Utils.getResponse(HttpStatus.NOT_FOUND, "Student not found", false);
      // }

      StudentCourseModel studentCourseModel = StudentCourseModel.builder()
          .idStudent(studentCourse.getId_student())
          .idCourse(studentCourse.getId_course())
          .build();

      studentCourseRespository.save(studentCourseModel);

      return Utils.getResponse(HttpStatus.CREATED, "Student course created", true);

    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }
}

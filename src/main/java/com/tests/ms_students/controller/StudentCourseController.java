package com.tests.ms_students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentCourseDto;
import com.tests.ms_students.service.StudentCourseService;

@RestController
@RequestMapping("/v1/student-courses")
public class StudentCourseController {
  @Autowired
  private StudentCourseService studentCourseService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllStudentCourse() {
    ResponseDto response = studentCourseService.getAllStudentCourse();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getStudentCourse(@PathVariable("id") Long id) {
    ResponseDto response = studentCourseService.getStudentCourse(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping
  public ResponseEntity<ResponseDto> createStudentCourse(@RequestBody StudentCourseDto studentCourse) {
    System.out.println("studentCourse: " + studentCourse);
    ResponseDto response = studentCourseService.createStudentCourse(studentCourse);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}

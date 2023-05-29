package com.tests.ms_students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_students.dto.ResponseDto;
import com.tests.ms_students.dto.StudentDto;
import com.tests.ms_students.service.StudentService;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllStudents() {
    ResponseDto response = studentService.getAllStudents();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getStudentById(@PathVariable Long id) {
    ResponseDto response = studentService.getStudentById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createStudent(@RequestBody StudentDto studentDto) {
    ResponseDto response = studentService.createStudent(studentDto);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateStudent(@RequestBody StudentDto studentDto) {
    ResponseDto response = studentService.updateStudent(studentDto);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteStudent(@PathVariable Long id) {
    ResponseDto response = studentService.deleteStudent(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}

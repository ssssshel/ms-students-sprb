package com.tests.ms_students.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tests.ms_students.dto.ResponseDto;

@FeignClient(name = "ms-courses", url = "http://localhost:9001/api")
public interface CourseClient {

  @GetMapping("/v1/courses")
  ResponseDto getAllCourses();

  @GetMapping("/v1/courses/{id}")
  ResponseDto getCourseById(@PathVariable("id") Long id);
}

package com.tests.ms_students.repository;

import com.tests.ms_students.model.StudentCourseModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRespository extends JpaRepository<StudentCourseModel, Long> {
  StudentCourseModel findByidStudent(Long idStudent);
}

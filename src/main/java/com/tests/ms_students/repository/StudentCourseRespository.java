package com.tests.ms_students.repository;

import org.springframework.stereotype.Repository;

import com.tests.ms_students.model.StudentCourseModel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentCourseRespository extends JpaRepository<StudentCourseModel, Long> {
  StudentCourseModel findByIdStudent(Long idStudent);
}

package com.tests.ms_students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tests.ms_students.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {

}

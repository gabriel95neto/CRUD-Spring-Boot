package com.gabriel.CrudSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.CrudSpring.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}

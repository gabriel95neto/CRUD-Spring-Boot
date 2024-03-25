package com.gabriel.CrudSpring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import com.gabriel.CrudSpring.dto.CourseDto;
import com.gabriel.CrudSpring.dto.CoursePageDto;
import com.gabriel.CrudSpring.dto.mapper.CourseMapper;
import com.gabriel.CrudSpring.exception.RecordNotFoundException;
import com.gabriel.CrudSpring.model.Course;
import com.gabriel.CrudSpring.repositories.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    public CourseService (CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDto list(@PositiveOrZero int page, @Positive @Max(100) int size) {
        Page<Course> pageCourse = this.courseRepository.findAll(PageRequest.of(page, size));
        List<CourseDto> courses = pageCourse.get().map(courseMapper::toDto).collect(Collectors.toList());
        return new CoursePageDto(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

     public CourseDto getCourseByID(@NotNull @Positive Long id) {
        return this.courseRepository.findById(id).map(courseMapper::toDto).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDto create(@Valid CourseDto course) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDto update(@NotNull @Positive long id, @Valid CourseDto course) {
        return courseRepository.findById(id)
        .map(record -> {
            record.setName(course.name());
            record.setCategory(courseMapper.convertCategory(course.category()));
            courseRepository.save((record));
            return courseMapper.toDto(record);
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive long id){
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}

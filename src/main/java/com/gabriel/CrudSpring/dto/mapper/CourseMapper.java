package com.gabriel.CrudSpring.dto.mapper;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gabriel.CrudSpring.dto.CourseDto;
import com.gabriel.CrudSpring.dto.LessonDto;
import com.gabriel.CrudSpring.enums.Category;
import com.gabriel.CrudSpring.model.Course;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDto> lessons = course.getLessons().stream().map(lesson -> new LessonDto(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl())).collect(Collectors.toList());
        return new CourseDto( course.getId() ,course.getName(), course.getCategory().getValue(), lessons);
    }

    public Course toEntity(CourseDto courseDto) {
        if (courseDto == null) {
            return null;
        }

        Course output = new Course();
        output.setName(courseDto.name());
        output.setCategory(convertCategory(courseDto.category()));

        return output;
    }

    public Category convertCategory(String value) {
        if(value == null) {
            return null;
        }

        return switch (value) {
            case "Back-end" -> Category.BACK_END;
            case "Front-end" -> Category.FRONT_END;
        
            default -> throw new IllegalArgumentException("Categoria inválida " + value);
        };
    }
}

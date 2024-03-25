package com.gabriel.CrudSpring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.gabriel.CrudSpring.enums.Category;
import com.gabriel.CrudSpring.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDto(

    Long id,

    @NotBlank
    @NotNull
    @Length(min =5, max= 100)
    String name,

    @NotBlank 
    @NotNull 
    @Length(max= 10)
    @ValueOfEnum(enumClass = Category.class)
    String category,

    List<LessonDto> lessons

    ) {}

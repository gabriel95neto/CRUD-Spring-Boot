package com.gabriel.CrudSpring.dto;

import java.util.List;

public record CoursePageDto(List<CourseDto> content, long totalElements, int totalPages) {
    
}

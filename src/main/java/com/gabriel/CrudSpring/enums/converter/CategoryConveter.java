package com.gabriel.CrudSpring.enums.converter;

import java.util.stream.Stream;

import com.gabriel.CrudSpring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class CategoryConveter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        // TODO Auto-generated method stub
        if (category == null) {
            return null;
        }

        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return Stream.of(Category.values()).filter(c -> c.getValue().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
    
}

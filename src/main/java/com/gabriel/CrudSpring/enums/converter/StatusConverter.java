package com.gabriel.CrudSpring.enums.converter;

import java.util.stream.Stream;

import com.gabriel.CrudSpring.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        // TODO Auto-generated method stub
        if (status == null) {
            return null;
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return Stream.of(Status.values()).filter(c -> c.getValue().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
    
}

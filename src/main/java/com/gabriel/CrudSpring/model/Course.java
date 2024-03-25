package com.gabriel.CrudSpring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import com.gabriel.CrudSpring.enums.Category;
import com.gabriel.CrudSpring.enums.Status;
import com.gabriel.CrudSpring.enums.converter.CategoryConveter;
import com.gabriel.CrudSpring.enums.converter.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE Course SET status = 'Inativo' WHERE id = ?")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min =5, max= 100)
    @Column(length =  200, nullable =  false)
    private String name;

    @NotNull
    @Column(length =  10, nullable =  false)
    @Convert(converter = CategoryConveter.class)
    private Category category;

    @NotNull
    @Convert(converter = StatusConverter.class)
    @Column(length =  10, nullable =  false)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();
}

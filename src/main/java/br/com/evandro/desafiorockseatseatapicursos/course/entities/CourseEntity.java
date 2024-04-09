package br.com.evandro.desafiorockseatseatapicursos.course.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "Course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "O campo [name] é obrigatório!")
    private String name;
    @NotBlank(message = "O campo [category] é obrigatório!")
    private String category;
    private boolean Active;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;

}

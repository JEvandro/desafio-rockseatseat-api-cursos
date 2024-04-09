package br.com.evandro.desafiorockseatseatapicursos.course.dto;

import jakarta.validation.constraints.NotNull;

public record CoursePutDTO(@NotNull(message = "O campo [name] não pode ser nulo") String name, @NotNull(message = "O campo [category] não pode ser nulo") String category) {
}

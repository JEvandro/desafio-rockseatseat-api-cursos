package br.com.evandro.desafiorockseatseatapicursos.exceptions;

public class CourseFoundException extends RuntimeException{

    public CourseFoundException(){ super ("O Curso já existe!"); }

}

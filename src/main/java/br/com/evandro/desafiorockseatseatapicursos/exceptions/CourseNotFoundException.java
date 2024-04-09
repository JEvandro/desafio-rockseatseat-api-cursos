package br.com.evandro.desafiorockseatseatapicursos.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(){
        super ("Sem resultado não há curso");
    }

}

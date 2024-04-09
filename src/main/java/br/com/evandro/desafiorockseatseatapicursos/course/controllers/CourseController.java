package br.com.evandro.desafiorockseatseatapicursos.course.controllers;

import br.com.evandro.desafiorockseatseatapicursos.course.dto.CoursePutDTO;
import br.com.evandro.desafiorockseatseatapicursos.course.entities.CourseEntity;
import br.com.evandro.desafiorockseatseatapicursos.course.usecases.CourseUseCase;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseUseCase courseUseCase;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity){
        var course = courseUseCase.executeCreate(courseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("")
    public ResponseEntity<Object> get(@RequestParam(name = "name") Optional<String> name, @RequestParam(name = "category") Optional<String> category) {
        var course = courseUseCase.executeGet(name, category);
        return ResponseEntity.ok().body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable UUID id,@Valid @RequestBody CoursePutDTO coursePutDTO){
        var course = courseUseCase.executePut(coursePutDTO, id);
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        courseUseCase.executeDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> patch(@PathVariable UUID id){
        var course = courseUseCase.executePatch(id);
        return ResponseEntity.ok().body(course);
    }


}

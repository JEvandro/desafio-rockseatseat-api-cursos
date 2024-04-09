package br.com.evandro.desafiorockseatseatapicursos.course.usecases;

import br.com.evandro.desafiorockseatseatapicursos.course.dto.CoursePutDTO;
import br.com.evandro.desafiorockseatseatapicursos.course.entities.CourseEntity;
import br.com.evandro.desafiorockseatseatapicursos.course.repositories.CourseRepository;
import br.com.evandro.desafiorockseatseatapicursos.exceptions.CourseFoundException;
import br.com.evandro.desafiorockseatseatapicursos.exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseUseCase {

    @Autowired
    CourseRepository courseRepository;

    public CourseEntity executeCreate(CourseEntity courseEntity){
        courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory()).ifPresent((course) -> {
            throw new CourseFoundException();
        });

        return courseRepository.save(courseEntity);
    }

    public List<CourseEntity> executeGet(Optional<String> name, Optional<String> category){

        var name_replace = name.toString().replace("Optional[", "").replace("]","");
        var category_replace = category.toString().replace("Optional[", "").replace("]","");

        if(name.isPresent() || category.isPresent()){
            if(name.isPresent()){
                if(category.isEmpty()) {
                    if(courseRepository.findByNameContainingIgnoreCase(name_replace).isEmpty()) throw new CourseNotFoundException();
                    return courseRepository.findByNameContainingIgnoreCase(name_replace);
                }else {
                    if(courseRepository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name_replace, category_replace).isEmpty()) throw new CourseNotFoundException();
                    return courseRepository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name_replace, category_replace).stream().toList();
                }
            }else {
                if (courseRepository.findByCategoryContainingIgnoreCase(category_replace).isEmpty()) throw new CourseNotFoundException();
                return courseRepository.findByCategoryContainingIgnoreCase(category_replace);
            }
        }
        if (courseRepository.findAll().isEmpty()) throw new CourseNotFoundException();
        return courseRepository.findAll();
    }

    public CourseEntity executePut(CoursePutDTO coursePutDTO, UUID id){
        var course = courseRepository.findById(id).orElseThrow(() ->{
            throw new CourseNotFoundException();
        });

        if(!coursePutDTO.name().isEmpty()) course.setName(coursePutDTO.name());
        if(!coursePutDTO.category().isEmpty()) course.setCategory(coursePutDTO.category());
        courseRepository.save(course);

        return course;
    }

    public void executeDelete(UUID id){
        courseRepository.deleteById(id);
    }

    public CourseEntity executePatch(UUID id){
        var course = courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException();
        });

        course.setActive(!course.isActive());
        course.setUpdated_at(new Date());
        courseRepository.save(course);

        return course;
    }

}

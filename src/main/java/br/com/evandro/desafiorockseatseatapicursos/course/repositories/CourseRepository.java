package br.com.evandro.desafiorockseatseatapicursos.course.repositories;

import br.com.evandro.desafiorockseatseatapicursos.course.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    Optional<CourseEntity> findByNameAndCategory(String name, String category);

    List<CourseEntity> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);
    List<CourseEntity> findByNameContainingIgnoreCase(String name);
    List<CourseEntity> findByCategoryContainingIgnoreCase(String category);


}

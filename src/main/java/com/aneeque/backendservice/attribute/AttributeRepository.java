package com.aneeque.backendservice.category.department.attribute;

import com.aneeque.backendservice.category.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Okala III
 */
public interface AttributeRepository extends JpaRepository<Attribute, Long> {


    List<Attribute> findAllByDepartment(Department department);
}

package com.aneeque.backendservice.attribute;

import com.aneeque.backendservice.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Okala III
 */
public interface AttributeRepository extends JpaRepository<Attribute, Long> {


    List<Attribute> findAllByDepartment(Department department);
}

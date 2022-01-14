package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Okala III
 */
public interface AttributeRepository extends JpaRepository<Attribute, Long> {


}

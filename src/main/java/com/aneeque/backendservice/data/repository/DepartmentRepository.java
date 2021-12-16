package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Okala III
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

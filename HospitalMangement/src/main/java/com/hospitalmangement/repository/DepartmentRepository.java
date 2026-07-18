package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitalmangement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}

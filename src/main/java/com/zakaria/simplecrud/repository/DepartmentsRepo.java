package com.zakaria.simplecrud.repository;

import com.zakaria.simplecrud.model.Departments;
import com.zakaria.simplecrud.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepo extends JpaRepository<Departments, String> {

}

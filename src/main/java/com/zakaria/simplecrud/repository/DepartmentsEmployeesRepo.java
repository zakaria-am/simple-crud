package com.zakaria.simplecrud.repository;

import com.zakaria.simplecrud.model.DepartmentManager;
import com.zakaria.simplecrud.model.DepartmentsEmployees;
import com.zakaria.simplecrud.model.DepartmentsEmployeesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsEmployeesRepo extends JpaRepository<DepartmentsEmployees, DepartmentsEmployeesId> {

}

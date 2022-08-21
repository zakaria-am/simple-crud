package com.zakaria.simplecrud.repository;

import com.zakaria.simplecrud.model.DepartmentManager;
import com.zakaria.simplecrud.model.DepartmentManagerId;
import com.zakaria.simplecrud.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentManagerRepo extends JpaRepository<DepartmentManager, DepartmentManagerId> {

}

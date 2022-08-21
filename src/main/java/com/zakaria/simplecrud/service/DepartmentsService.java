package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.Departments;
import com.zakaria.simplecrud.model.Employees;
import com.zakaria.simplecrud.repository.DepartmentsRepo;
import com.zakaria.simplecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentsService {

    @Autowired
    DepartmentsRepo departmentsRepo;

    public List<Departments> findAllDepartments(){
        return departmentsRepo.findAll();
    }

    public Departments findById(String departmentName){
        return departmentsRepo.findById(departmentName).get();
    }

    public void saveDepartments(Departments departments){
        departmentsRepo.save(departments);
    }

    public void deleteDepartments(Departments departments){
        departmentsRepo.deleteById(departments.getDepartmentName());
    }

}

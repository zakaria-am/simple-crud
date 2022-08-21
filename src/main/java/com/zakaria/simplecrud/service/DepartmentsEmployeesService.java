package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.DepartmentsEmployees;
import com.zakaria.simplecrud.model.DepartmentsEmployeesId;
import com.zakaria.simplecrud.repository.DepartmentsEmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentsEmployeesService {

    @Autowired
    DepartmentsEmployeesRepo departmentsEmployeesRepo;

    public List<DepartmentsEmployees> findAllDepartmentsEmployees(){
        return departmentsEmployeesRepo.findAll();
    }

    public DepartmentsEmployees findById(DepartmentsEmployeesId departmentsEmployeesId){
        return departmentsEmployeesRepo.findById(departmentsEmployeesId).get();
    }

    public void saveDepartmentsEmployees(DepartmentsEmployees departmentsEmployees){
        departmentsEmployeesRepo.save(departmentsEmployees);
    }

    public void deleteDepartmentManager(DepartmentsEmployeesId departmentsEmployeesId){
        departmentsEmployeesRepo.deleteById(departmentsEmployeesId);
    }

}

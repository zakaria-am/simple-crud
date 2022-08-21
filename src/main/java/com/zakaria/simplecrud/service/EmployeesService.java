package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.Employees;
import com.zakaria.simplecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeesService {

    @Autowired
    EmployeesRepo employeesRepo;

    public List<Employees> findAllEmployees(){
        return employeesRepo.findAll();
    }

    public Employees findById(Integer employeeNumber){
        return employeesRepo.findById(employeeNumber).get();
    }

    public void saveEmployees(Employees employees){
        employeesRepo.save(employees);
    }

    public void deleteEmployees(Employees employees){
        employeesRepo.deleteById(employees.getEmployeeNumber());
    }

}

package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.DepartmentsEmployees;
import com.zakaria.simplecrud.model.DepartmentsEmployeesId;
import com.zakaria.simplecrud.service.DepartmentsEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/departmentsEmployees")
@Validated
public class DepartmentsEmployeesController {
    @Autowired
    DepartmentsEmployeesService departmentsEmployeesService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentsEmployees>> findAllDepartmentsEmployees(){
        return new ResponseEntity<List<DepartmentsEmployees>>(departmentsEmployeesService.findAllDepartmentsEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity<DepartmentsEmployees> findDepartmentsEmployeesById(@PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentsEmployeesId departmentsEmployeesId = new DepartmentsEmployeesId();
            departmentsEmployeesId.setDepartmentNumber(departmentNumber);
            departmentsEmployeesId.setEmployeeNumber(employeeNumber);

            DepartmentsEmployees departmentsEmployees = departmentsEmployeesService.findById(departmentsEmployeesId);
            return new ResponseEntity<DepartmentsEmployees>(departmentsEmployees, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<DepartmentsEmployees>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addDepartmentEmployees(@RequestBody @Valid DepartmentsEmployees departmentsEmployees) {
        departmentsEmployeesService.saveDepartmentsEmployees(departmentsEmployees);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity updateDepartmentEmployees(@RequestBody @Valid DepartmentsEmployees departmentsEmployees, @PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentsEmployeesId departmentsEmployeesId = new DepartmentsEmployeesId();
            departmentsEmployeesId.setDepartmentNumber(departmentNumber);
            departmentsEmployeesId.setEmployeeNumber(employeeNumber);

            DepartmentsEmployees existsDepartmentsEmployees = departmentsEmployeesService.findById(departmentsEmployeesId);
            departmentsEmployees.setDepartmentsEmployeesId(departmentsEmployeesId);
            departmentsEmployeesService.saveDepartmentsEmployees(departmentsEmployees);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity deleteDepartmentsEmployeesById(@PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentsEmployeesId departmentsEmployeesId = new DepartmentsEmployeesId();
            departmentsEmployeesId.setDepartmentNumber(departmentNumber);
            departmentsEmployeesId.setEmployeeNumber(employeeNumber);

            DepartmentsEmployees existsDepartmentsEmployees = departmentsEmployeesService.findById(departmentsEmployeesId);
            departmentsEmployeesService.deleteDepartmentManager(departmentsEmployeesId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

}

package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.Departments;
import com.zakaria.simplecrud.model.Employees;
import com.zakaria.simplecrud.service.DepartmentsService;
import com.zakaria.simplecrud.service.EmployeesService;
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
@RequestMapping("/departments")
@Validated
public class DepartmentsController {
    @Autowired
    DepartmentsService departmentsService;

    @GetMapping("")
    public ResponseEntity<List<Departments>> findAllDepartments(){
        return new ResponseEntity<List<Departments>>(departmentsService.findAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{departmentNumber}")
    public ResponseEntity<Departments> findDepartmentsById(@PathVariable String departmentNumber){
        try{
            Departments departments = departmentsService.findById(departmentNumber);
            return new ResponseEntity<Departments>(departments, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Departments>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addDepartments(@RequestBody @Valid Departments departments) {
        departmentsService.saveDepartments(departments);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{departmentNumber}")
    public ResponseEntity updateDepartments(@RequestBody @Valid Departments departments, @PathVariable String departmentNumber){
        try{
            Departments existsDepartments = departmentsService.findById(departmentNumber);
            departments.setDepartmentNumber(departmentNumber);
            departmentsService.saveDepartments(departments);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{departmentNumber}")
    public ResponseEntity deleteDepartmentById(@PathVariable String departmentNumber){
        try{
            Departments existsDepartments = departmentsService.findById(departmentNumber);
            departmentsService.deleteDepartments(existsDepartments);
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

package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.Employees;
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
@RequestMapping("/employees")
@Validated
public class EmployeesController {
    @Autowired
    EmployeesService employeesService;

    @GetMapping("")
    public ResponseEntity<List<Employees>> findAllEmployees(){
        return new ResponseEntity<List<Employees>>(employeesService.findAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<Employees> findEmployeesById(@PathVariable Integer employeeNumber){
        try{
            Employees employees = employeesService.findById(employeeNumber);
            return new ResponseEntity<Employees>(employees, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Employees>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addEmployees(@RequestBody @Valid Employees employees) {
        employeesService.saveEmployees(employees);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{employeeNumber}")
    public ResponseEntity updateEmployees(@RequestBody @Valid Employees employees, @PathVariable Integer employeeNumber){
        try{
            Employees existEmployees = employeesService.findById(employeeNumber);
            employees.setEmployeeNumber(employeeNumber);
            employeesService.saveEmployees(employees);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity deleteEmployeesById(@PathVariable Integer employeeNumber){
        try{
            Employees existEmployees = employeesService.findById(employeeNumber);
            employeesService.deleteEmployees(existEmployees);
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

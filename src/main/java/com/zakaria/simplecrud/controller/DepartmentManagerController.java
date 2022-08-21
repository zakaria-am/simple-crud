package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.DepartmentManager;
import com.zakaria.simplecrud.model.DepartmentManagerId;
import com.zakaria.simplecrud.model.Departments;
import com.zakaria.simplecrud.service.DepartmentManagerService;
import com.zakaria.simplecrud.service.DepartmentsService;
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
@RequestMapping("/departmentManager")
@Validated
public class DepartmentManagerController {
    @Autowired
    DepartmentManagerService departmentManagerService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentManager>> findAllDepartmentManager(){
        return new ResponseEntity<List<DepartmentManager>>(departmentManagerService.findAllDepartmentManager(), HttpStatus.OK);
    }

    @GetMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity<DepartmentManager> findDepartmentManagerById(@PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentManagerId departmentManagerId = new DepartmentManagerId();
            departmentManagerId.setDepartmentNumber(departmentNumber);
            departmentManagerId.setEmployeeNumber(employeeNumber);

            DepartmentManager departmentManager = departmentManagerService.findById(departmentManagerId);
            return new ResponseEntity<DepartmentManager>(departmentManager, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<DepartmentManager>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addDepartmentManager(@RequestBody @Valid DepartmentManager departmentManager) {
        departmentManagerService.saveDepartmentManager(departmentManager);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity updateDepartmentManager(@RequestBody @Valid DepartmentManager departmentManager, @PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentManagerId departmentManagerId = new DepartmentManagerId();
            departmentManagerId.setDepartmentNumber(departmentNumber);
            departmentManagerId.setEmployeeNumber(employeeNumber);

            DepartmentManager existsDepartmentManager = departmentManagerService.findById(departmentManagerId);
            departmentManager.setDepartmentManagerId(departmentManagerId);
            departmentManagerService.saveDepartmentManager(departmentManager);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{departmentNumber}/{employeeNumber}")
    public ResponseEntity deleteDepartmentById(@PathVariable String departmentNumber, @PathVariable Integer employeeNumber){
        try{
            DepartmentManagerId departmentManagerId = new DepartmentManagerId();
            departmentManagerId.setDepartmentNumber(departmentNumber);
            departmentManagerId.setEmployeeNumber(employeeNumber);

            DepartmentManager existsDepartmentManager = departmentManagerService.findById(departmentManagerId);
            departmentManagerService.deleteDepartmentManager(departmentManagerId);
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

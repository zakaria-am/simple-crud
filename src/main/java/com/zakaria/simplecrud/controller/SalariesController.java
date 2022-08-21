package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.Salaries;
import com.zakaria.simplecrud.model.SalariesId;
import com.zakaria.simplecrud.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/salaries")
@Validated
public class SalariesController {
    @Autowired
    SalariesService salariesService;

    @GetMapping("")
    public ResponseEntity<List<Salaries>> findAllSalaries(){
        return new ResponseEntity<List<Salaries>>(salariesService.findAllSalaries(), HttpStatus.OK);
    }

    @GetMapping("/{employeeNumber}/{fromDate}")
    public ResponseEntity<Salaries> findSalariesById(@PathVariable Integer employeeNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            SalariesId salariesId = new SalariesId();
            salariesId.setEmployeeNumber(employeeNumber);
            salariesId.setFromDate(fromDate);

            Salaries salaries = salariesService.findById(salariesId);
            return new ResponseEntity<Salaries>(salaries, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Salaries>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addSalaries(@RequestBody @Valid Salaries salaries) {
        salariesService.saveSalaries(salaries);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{employeeNumber}/{fromDate}")
    public ResponseEntity updateSalaries(@RequestBody @Valid Salaries salaries, @PathVariable Integer employeeNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            SalariesId salariesId = new SalariesId();
            salariesId.setEmployeeNumber(employeeNumber);
            salariesId.setFromDate(fromDate);

            Salaries existsSalaries = salariesService.findById(salariesId);
            salaries.setSalariesId(salariesId);
            salariesService.saveSalaries(salaries);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeNumber}/{fromDate}")
    public ResponseEntity deleteSalariesById(@PathVariable Integer employeeNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            SalariesId salariesId = new SalariesId();
            salariesId.setEmployeeNumber(employeeNumber);
            salariesId.setFromDate(fromDate);

            Salaries existsSalaries = salariesService.findById(salariesId);
            salariesService.deleteSalaries(salariesId);
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

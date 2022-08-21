package com.zakaria.simplecrud.controller;

import com.zakaria.simplecrud.model.Titles;
import com.zakaria.simplecrud.model.TitlesId;
import com.zakaria.simplecrud.service.TitlesService;
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
@RequestMapping("/titles")
@Validated
public class TitlesController {
    @Autowired
    TitlesService titlesService;

    @GetMapping("")
    public ResponseEntity<List<Titles>> findAllTitles(){
        return new ResponseEntity<List<Titles>>(titlesService.findAllTitles(), HttpStatus.OK);
    }

    @GetMapping("/{employeeNumber}/{title}/{fromDate}")
    public ResponseEntity<Titles> findTitlesById(@PathVariable Integer employeeNumber, @PathVariable String title,  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            TitlesId titlesId = new TitlesId();
            titlesId.setEmployeeNumber(employeeNumber);
            titlesId.setTitle(title);
            titlesId.setFromDate(fromDate);

            Titles titles = titlesService.findById(titlesId);
            return new ResponseEntity<Titles>(titles, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Titles>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity addTitles(@RequestBody @Valid Titles titles) {
        titlesService.saveTitles(titles);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{employeeNumber}/{title}/{fromDate}")
    public ResponseEntity updateTitles(@RequestBody @Valid Titles titles, @PathVariable Integer employeeNumber, @PathVariable String title,  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            TitlesId titlesId = new TitlesId();
            titlesId.setEmployeeNumber(employeeNumber);
            titlesId.setTitle(title);
            titlesId.setFromDate(fromDate);

            Titles existsTitles = titlesService.findById(titlesId);
            titles.setTitlesId(titlesId);
            titlesService.saveTitles(titles);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeNumber}/{title}/{fromDate}")
    public ResponseEntity deleteTitlesById(@PathVariable Integer employeeNumber, @PathVariable String title,  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        try{
            TitlesId titlesId = new TitlesId();
            titlesId.setEmployeeNumber(employeeNumber);
            titlesId.setTitle(title);
            titlesId.setFromDate(fromDate);

            Titles existsTitles = titlesService.findById(titlesId);
            titlesService.deleteTitles(titlesId);
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

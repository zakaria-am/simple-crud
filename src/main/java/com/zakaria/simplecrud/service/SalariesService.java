package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.Salaries;
import com.zakaria.simplecrud.model.SalariesId;
import com.zakaria.simplecrud.repository.SalariesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SalariesService {

    @Autowired
    SalariesRepo salariesRepo;

    public List<Salaries> findAllSalaries(){
        return salariesRepo.findAll();
    }

    public Salaries findById(SalariesId salariesId){
        return salariesRepo.findById(salariesId).get();
    }

    public void saveSalaries(Salaries salaries){
        salariesRepo.save(salaries);
    }

    public void deleteSalaries(SalariesId salariesId){
        salariesRepo.deleteById(salariesId);
    }

}

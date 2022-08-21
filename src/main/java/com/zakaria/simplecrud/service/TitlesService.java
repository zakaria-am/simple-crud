package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.Titles;
import com.zakaria.simplecrud.model.TitlesId;
import com.zakaria.simplecrud.repository.TitlesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TitlesService {

    @Autowired
    TitlesRepo titlesRepo;

    public List<Titles> findAllTitles(){
        return titlesRepo.findAll();
    }

    public Titles findById(TitlesId titlesId){
        return titlesRepo.findById(titlesId).get();
    }

    public void saveTitles(Titles titles){
        titlesRepo.save(titles);
    }

    public void deleteTitles(TitlesId titlesId){
        titlesRepo.deleteById(titlesId);
    }

}

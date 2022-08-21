package com.zakaria.simplecrud.service;

import com.zakaria.simplecrud.model.DepartmentManager;
import com.zakaria.simplecrud.model.DepartmentManagerId;
import com.zakaria.simplecrud.repository.DepartmentManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentManagerService {

    @Autowired
    DepartmentManagerRepo departmentManagerRepo;

    public List<DepartmentManager> findAllDepartmentManager(){
        return departmentManagerRepo.findAll();
    }

    public DepartmentManager findById(DepartmentManagerId departmentManagerId){
        return departmentManagerRepo.findById(departmentManagerId).get();
    }

    public void saveDepartmentManager(DepartmentManager departmentManager){
        departmentManagerRepo.save(departmentManager);
    }

    public void deleteDepartmentManager(DepartmentManagerId departmentManagerId){
        departmentManagerRepo.deleteById(departmentManagerId);
    }

}

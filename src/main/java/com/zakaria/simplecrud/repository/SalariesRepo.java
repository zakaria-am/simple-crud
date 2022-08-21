package com.zakaria.simplecrud.repository;

import com.zakaria.simplecrud.model.Employees;
import com.zakaria.simplecrud.model.Salaries;
import com.zakaria.simplecrud.model.SalariesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalariesRepo extends JpaRepository<Salaries, SalariesId> {

}

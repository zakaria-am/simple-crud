package com.zakaria.simplecrud.repository;

import com.zakaria.simplecrud.model.Titles;
import com.zakaria.simplecrud.model.TitlesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlesRepo extends JpaRepository<Titles, TitlesId> {

}

package com.sagmura.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagmura.jpa.dto.Alien;

public interface AlienRepository extends JpaRepository<Alien, Integer>{
	
	List<Alien> findByAidGreaterThan(int id);

}

package com.compass.sprint4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.sprint4.entity.IdeologiaEnum;
import com.compass.sprint4.entity.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
	
	List<Partido> findByIdeologia(IdeologiaEnum ideologia);

}

package com.compass.sprint4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.sprint4.entity.Associado;
import com.compass.sprint4.entity.CargoEnum;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
	
	List<Associado> findByCargo(CargoEnum cargo);
	
	List<Associado> findByCargoOrderByNomeAsc(boolean ordencao);
	
	List<Associado> findAllByPartido(int id);
	
	
	
	

}

package com.hmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hmdy.recipe.domain.UnitOfMeasurement;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {
	
	Optional<UnitOfMeasurement> findByName(String name);
}

package com.hmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hmdy.recipe.domain.UnitOfMeasurement;

public interface UomService {
	List<UnitOfMeasurement> getAllUoms();
	Optional<UnitOfMeasurement> getUomByName(String name);

}

package com.hmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmdy.recipe.domain.UnitOfMeasurement;
import com.hmdy.recipe.repository.UnitOfMeasurementRepository;
import com.hmdy.recipe.service.UomService;

@Service
public class UomServiceImpl implements UomService{
	private UnitOfMeasurementRepository unitOfMeasurementRepository;
	
	public UomServiceImpl(UnitOfMeasurementRepository unitOfMeasurementRepository) {
		super();
		this.unitOfMeasurementRepository = unitOfMeasurementRepository;
	}

	@Override
	public List<UnitOfMeasurement> getAllUoms() {
		// TODO Auto-generated method stub
		return (List<UnitOfMeasurement>) unitOfMeasurementRepository.findAll();
	}

	@Override
	public Optional<UnitOfMeasurement> getUomByName(String name) {
		// TODO Auto-generated method stub
		return unitOfMeasurementRepository.findByName(name);
	}

}

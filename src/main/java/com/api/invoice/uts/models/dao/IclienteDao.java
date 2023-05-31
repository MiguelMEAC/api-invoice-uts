package com.api.invoice.uts.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.invoice.uts.models.entities.Region;
import  com.api.invoice.uts.models.entities.cliente;

public interface IclienteDao extends CrudRepository<cliente,Long>{

	@Query("from Region")
	public List<Region> findAllRegiones();
	
	
}

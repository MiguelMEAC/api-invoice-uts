package com.api.invoice.uts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.invoice.uts.models.dao.IFacturaDao;
import com.api.invoice.uts.models.dao.IProductoDao;
import com.api.invoice.uts.models.dao.IclienteDao;
import com.api.invoice.uts.models.entities.Factura;
import com.api.invoice.uts.models.entities.Producto;
import com.api.invoice.uts.models.entities.Region;
import com.api.invoice.uts.models.entities.cliente;



@Service
public class clienteServiceImpl implements IclienteServices{

	@Autowired
	private IclienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<cliente> findAll() {
		return (List<cliente>)clienteDao.findAll();
	}

	@Override
	public cliente findById(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public cliente save(cliente Cliente) {
		return clienteDao.save(Cliente);
	}

	@Override
	public void delet(cliente Cliente) {
		clienteDao.delete(Cliente);
		
	}

	@Override
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Factura> findFacturaAll() {
		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	@Transactional()
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional()
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	}

	
	
}

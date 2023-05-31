	package com.api.invoice.uts.services;
	
	import java.util.List;

import com.api.invoice.uts.models.entities.Factura;
import com.api.invoice.uts.models.entities.Producto;
import com.api.invoice.uts.models.entities.Region;
import com.api.invoice.uts.models.entities.cliente;
	
	public interface IclienteServices {
	
		public List<cliente> findAll();
		
		public cliente findById(Long id);
		
		public cliente save(cliente Cliente);
		
		public void delet(cliente Cliente);
		
	    public List<Region> findAllRegiones();
	    
	    public Factura findFacturaById(Long id);

	    public List<Factura> findFacturaAll();

	    public Factura saveFactura(Factura factura);

	    public void deleteFacturaById(Long id);

	    public List<Producto> findProductoByNombre(String term);
	    
	    
	}

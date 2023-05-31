package com.api.invoice.uts.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="facturas")
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long Id;
	
   private String descripcion;
   
   private String observacion;
   
   
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
   private Date createAt;

	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id")
   private List<ItemFactura> items;
   
	@JsonIgnoreProperties(value={"facturas","hibernateLazyInitializer","handler"},allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
   private cliente cliente;
   
   public Factura() {
	   this.items= new ArrayList<>();
   }
  
   

   public Long getId() {
	return Id;
}



public void setId(Long id) {
	Id = id;
}



public String getDescripcion() {
	return descripcion;
}



public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}



public String getObservacion() {
	return observacion;
}



public void setObservacion(String observacion) {
	this.observacion = observacion;
}



public Date getCreateAt() {
	return createAt;
}



public void setCreateAt(Date createAt) {
	this.createAt = createAt;
}



public List<ItemFactura> getItems() {
	return items;
}



public void setItems(List<ItemFactura> items) {
	this.items = items;
}



public cliente getCliente() {
	return cliente;
}



public void setCliente(cliente cliente) {
	this.cliente = cliente;
}



public static long getSerialversionuid() {
	return serialVersionUID;
}



private static final long serialVersionUID = 1L;

}

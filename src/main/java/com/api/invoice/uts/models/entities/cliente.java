package com.api.invoice.uts.models.entities;

import java.io.Serializable;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class cliente implements Serializable{
	

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
@NotEmpty(message="no puede estar vacio")
@Size(min=3,max=30, message="el tamaño debe estar entre 3 y 30")
@Column(nullable=false)
private String nombre;
@Column(nullable=false)
private String apellido;
@Email(message="no es una dirrecion de correo bien formada")
@Column(nullable=false)
private String email;
@Column(name="create_at")
private Date createAT;

@NotNull(message="la region no puede ser vacia")
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="region_id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
private Region region;

@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters=true)
@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade=CascadeType.ALL)
private List<Factura> facturas;

//Gets y Setts
public String getApellidos() {
	return apellido;
}
public void setApellidos(String apellido) {
	this.apellido = apellido;
}
public List<Factura> getFacturas() {
	return facturas;
}
public void setFacturas(List<Factura> facturas) {
	this.facturas = facturas;
}
public Region getRegion() {
	return region;
}
public void setRegion(Region region) {
	this.region = region;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getCreateAT() {
	return createAT;
}
public void setCreateAT(Date createAT) {
	this.createAT = createAT;
}

private static final long serialVersionUID = 1L;
}

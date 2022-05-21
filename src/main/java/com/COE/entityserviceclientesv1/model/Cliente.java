package com.COE.entityserviceclientesv1.model;

public class Cliente {
	private Integer id;
	private String nombre;
	private String correo;
	
	
	public Cliente(String nombre, Integer id, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public Integer getID() {
		return id;
	}
	public void setID(Integer id) {
		this.id = id;
	}
}
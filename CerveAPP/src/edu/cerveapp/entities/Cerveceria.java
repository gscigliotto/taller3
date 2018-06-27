package edu.cerveapp.entities;

import java.util.List;


public class Cerveceria {

	private String nombreCerveceria;
	private List<GustoProduccion> gustos;
	private List<Usuario> usuarios;
	private List<Sucursal> sucursales;
	private List<Pedido> pedido;
	
	public String getNombreCerveceria() {
		return nombreCerveceria;
	}
	public void setNombreCerveceria(String nombreCerveceria) {
		this.nombreCerveceria = nombreCerveceria;
	}
	public void generarPedido(Pedido p)
	{
		
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<GustoProduccion> getGustos() {
		return gustos;
	}
	public void setGustos(List<GustoProduccion> gustos) {
		this.gustos = gustos;
	}
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public List<Pedido> getPedido() {
		return pedido;
	}
	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	
	
}

package edu.cerveapp.entities;

import java.util.List;

public class Cerveceria {

	private List<GustoProduccion> gustos;
	private String nombreCerveceria;
	private List<Pedido> pedido;
	private List<Sucursal> sucursales;
	private List<Usuario> usuarios;

	public void generarPedido(Pedido p) {

	}

	public List<GustoProduccion> getGustos() {
		return gustos;
	}

	public String getNombreCerveceria() {
		return nombreCerveceria;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setGustos(List<GustoProduccion> gustos) {
		this.gustos = gustos;
	}

	public void setNombreCerveceria(String nombreCerveceria) {
		this.nombreCerveceria = nombreCerveceria;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}

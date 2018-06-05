package edu.cerveapp.model;

import java.util.List;

public class Pedido {
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Gusto> getGustos() {
		return gustosPedido;
	}
	public void setGustos(List<Gusto> gustos) {
		this.gustosPedido = gustos;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public ePedido getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(ePedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	private Usuario usuario;
	private List<Gusto> gustosPedido;
	private double monto;
	
	private ePedido estadoPedido;

}

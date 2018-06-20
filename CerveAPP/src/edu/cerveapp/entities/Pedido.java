package edu.cerveapp.entities;

import java.util.List;

public class Pedido {
	
	private Usuario usuario;
	private List<GustoPedido> gustosPedido;
	private double monto;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<GustoPedido> getGustos() {
		return gustosPedido;
	}
	public void setGustos(List<GustoPedido> gustos) {
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

	public List<GustoPedido> getGustosPedido() {
		return gustosPedido;
	}
	public void setGustosPedido(List<GustoPedido> gustosPedido) {
		this.gustosPedido = gustosPedido;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	private int numero;
	
	private ePedido estadoPedido;

}

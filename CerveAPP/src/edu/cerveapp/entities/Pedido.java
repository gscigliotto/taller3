package edu.cerveapp.entities;

import java.util.List;

public class Pedido {
	

	private int id;
	private Usuario usuario;
	private List<GustoPedido> gustosPedido;
	private double monto;
	private int numero;

	
	public Pedido(int id, int numero, double monto, List<GustoPedido> gustospedidos,String estado,Usuario usuario) {
		setId(id);
		setNumero(numero);
		setMonto(monto);
		setGustos(gustospedidos);
		setEstado(estado);
		setUsuario(usuario);
		
	}
	public Pedido(int id, int numero, double monto,String estado) {
		setId(id);
		setNumero(numero);
		setMonto(monto);
		setEstado(estado);
	
		
	}
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	private String estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private ePedido estadoPedido;
	
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
	


}

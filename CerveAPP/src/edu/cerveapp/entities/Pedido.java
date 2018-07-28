package edu.cerveapp.entities;

import java.util.List;


public class Pedido {
	private ePedido estadoPedido;
	private List<GustoPedido> gustosPedido;
	private int id;

	private double monto;

	private Usuario usuario;

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(int id, double monto, List<GustoPedido> gustospedidos, ePedido estado, Usuario usuario) {
		setId(id);


		setMonto(monto);
		setGustos(gustospedidos);
		setEstadoPedido(estado);
		setUsuario(usuario);
	}

	public Pedido(int id, int numero, double monto, ePedido estado) {
		setId(id);
		setNumero(numero);
		setMonto(monto);
		setEstadoPedido(estado);

	}

	public Pedido(int id, String idraw, double monto, List<GustoPedido> gustospedidos, ePedido estado,
			Usuario usuario) {
		setId(id);


		setMonto(monto);
		setGustos(gustospedidos);
		setEstadoPedido(estado);
		setUsuario(usuario);

	}



	public ePedido getEstadoPedido() {
		return estadoPedido;
	}

	public List<GustoPedido> getGustos() {
		return gustosPedido;
	}

	public List<GustoPedido> getGustosPedido() {
		return gustosPedido;
	}

	public int getId() {
		return id;
	}



	public double getMonto() {
		return monto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setEstadoPedido(ePedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public void setGustos(List<GustoPedido> gustos) {
		this.gustosPedido = gustos;
	}

	public void setGustosPedido(List<GustoPedido> gustosPedido) {
		this.gustosPedido = gustosPedido;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}

	public void setNumero(int num) {
		// this.numero = GUID.randomUUID().toString();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

package edu.cerveapp.model;

import java.util.List;

public class Sucursal {
	private List<GustoStock> gustoStock;
	private List<Pedido> pedidos;
	public List<GustoStock> getGusctoStock() {
		return gustoStock;
	}

	public void setGusctoStock(List<GustoStock> gusctoStock) {
		this.gustoStock = gusctoStock;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

}

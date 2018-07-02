package edu.cerveapp.entities;

public class GustoPedido extends Gusto{
	
	public double getPreciolitro() {
		return preciolitro;
	}

	public void setPreciolitro(double preciolitro) {
		this.preciolitro = preciolitro;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_gusto() {
		return id_gusto;
	}

	public void setId_gusto(int id_gusto) {
		this.id_gusto = id_gusto;
	}

	public void setCantidadPedida(double cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	private double cantidadPedida;
	private double preciolitro;
	private int id_pedido;
	private int id_gusto;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;


	public GustoPedido(int id, int id_pedido, int id_gusto, double cantidad_pedida, double precio_litro,String gusto) {
		setId(id);
		setId_pedido(id_pedido);
		setId_gusto(id_gusto);
		setCantidadPedida(cantidad_pedida);
		setPreciolitro(precio_litro);
		setNomnbre(gusto);
	}

	public GustoPedido() {
		// TODO Auto-generated constructor stub
	}



	public double getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

}

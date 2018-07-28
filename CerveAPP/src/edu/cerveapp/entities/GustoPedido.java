package edu.cerveapp.entities;

public class GustoPedido extends Gusto {

	private double cantidadPedida;

	private int id;

	private int id_gusto;

	private int id_pedido;

	private double preciolitro;

	public GustoPedido() {
		// TODO Auto-generated constructor stub
	}

	public GustoPedido(int id, int idPedido, int id_gusto, double cantidad_pedida, double precio_litro,
			String gusto) {
		setId(id);
		setId_pedido(idPedido);
		setId_gusto(id_gusto);
		setCantidadPedida(cantidad_pedida);
		setPreciolitro(precio_litro);
		setNomnbre(gusto);
	}

	public double getCantidadPedida() {
		return cantidadPedida;
	}

	public int getId() {
		return id;
	}

	public int getId_gusto() {
		return id_gusto;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public double getPreciolitro() {
		return preciolitro;
	}

	public void setCantidadPedida(double cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_gusto(int id_gusto) {
		this.id_gusto = id_gusto;
	}

	public void setId_pedido(int idPedido) {
		this.id_pedido = idPedido;
	}

	public void setPreciolitro(double preciolitro) {
		this.preciolitro = preciolitro;
	}

}

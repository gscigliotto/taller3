package edu.cerveapp.entities;

public class GustoStock extends Gusto {
	private int id;
	private double precio;
	private double variedadDisp;

	public GustoStock() {
		// TODO Auto-generated constructor stub
	}

	public GustoStock(int id, String gusto, double cantidad_disponible, double precio) {
		this.id = id;
		setVariedadDisp(cantidad_disponible);
		setNomnbre(gusto);
		setPrecio(precio);

	}

	public int getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	public double getVariedadDisp() {
		return variedadDisp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setVariedadDisp(double variedadDisp) {
		this.variedadDisp = variedadDisp;
	}

}

package edu.cerveapp.entities;

public class GustoStock extends Gusto{
	private double variedadDisp;
	private int id;
	private double precio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public GustoStock(int id,String gusto, double cantidad_disponible,double precio) {
		this.id=id;
		setVariedadDisp(cantidad_disponible);
		setNomnbre(gusto);
		setPrecio(precio);
		
				
		
	}
	public GustoStock() {
		// TODO Auto-generated constructor stub
	}
	public double getVariedadDisp() {
		return variedadDisp;
	}

	public void setVariedadDisp(double variedadDisp) {
		this.variedadDisp = variedadDisp;
	}
	

}

package edu.cerveapp.entities;

public class GustoStock extends Gusto{
	private double variedadDisp;
	private int id;

	
	public GustoStock(int id,String gusto, double cantidad_disponible) {
		this.id=id;
		setVariedadDisp(cantidad_disponible);
		setNomnbre(gusto);
		
				
		
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

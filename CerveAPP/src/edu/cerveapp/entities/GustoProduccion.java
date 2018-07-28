package edu.cerveapp.entities;

import java.util.List;

public class GustoProduccion extends Gusto {
	private List<String> ingredientes;

	public List<String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<String> ingredientes) {
		this.ingredientes = ingredientes;
	}
}

package entities;

import java.util.List;

public class GustoProduccion extends Gusto{
	public List<String> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<String> ingredientes) {
		this.ingredientes = ingredientes;
	}
	private List<String> ingredientes;
}

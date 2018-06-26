package edu.cerveapp.dao.repos.RepoList;

import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.dao.repos.IRepoGustoStock;
import edu.cerveapp.entities.GustoStock;

public class RepoListGustoStock implements IRepoGustoStock {
	
	private List<GustoStock> obtenerGustos() {
		List<GustoStock> gustosDisponibles= new ArrayList<GustoStock>();
		GustoStock gusto = new GustoStock();
		gusto.setNomnbre("IPA");
		
		gustosDisponibles.add(gusto);
		
		gusto = new GustoStock();
		gusto.setNomnbre("HONNEY");
		gustosDisponibles.add(gusto);
		
		gusto = new GustoStock();
		gusto.setNomnbre("POPTER");
		gustosDisponibles.add(gusto);
		
		return gustosDisponibles;
		
	}

	@Override
	public List<GustoStock> getAll() {
		// TODO Auto-generated method stub
		return obtenerGustos();
	}

	@Override
	public List<GustoStock> getByID(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GustoStock> getByCampoString(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GustoStock> getByCampoInteger(String campo, Integer valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GustoStock> getByCampoDouble(String campo, double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(GustoStock u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Insert(GustoStock u) {
		// TODO Auto-generated method stub

	}

}

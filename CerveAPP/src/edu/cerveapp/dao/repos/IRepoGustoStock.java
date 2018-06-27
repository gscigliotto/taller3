package edu.cerveapp.dao.repos;

import java.util.List;

import edu.cerveapp.entities.GustoStock;

public interface IRepoGustoStock {
	public List<GustoStock> getAll();
	public List<GustoStock> getByID(String campo, String valor);
	public List<GustoStock> getByCampoString(String campo, String valor);
	public List<GustoStock> getByCampoInteger(String campo, Integer valor);
	public List<GustoStock> getByCampoDouble(String campo, double valor);
	public void update(GustoStock u);
	public void Insert(GustoStock u);
}

package edu.cerveapp.dao.repos;

import java.util.List;

import edu.cerveapp.entities.Pedido;

public interface IRepoPedidos {

	public List<Pedido> getAll();
	public List<Pedido> getByID(String campo, String valor);
	public List<Pedido> getByCampoString(String campo, String valor);
	public List<Pedido> getByCampoInteger(String campo, Integer valor);
	public List<Pedido> getByCampoDouble(String campo, double valor);
	public void update(Pedido p) ;
	public void insert(Pedido p);

}

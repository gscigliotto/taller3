package edu.cerveapp.dao.repos.RepoList;

import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.dao.repos.IRepoPedidos;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.ePedido;

public class RepoListPedidos implements IRepoPedidos {
	private List<Pedido> pedidos=null;
	private int ultimo=0;
	
	public RepoListPedidos() {
		pedidos = new ArrayList<Pedido>();
		
		Pedido p = new Pedido();
		p.setEstadoPedido(ePedido.SOLICITADO);
		p.setNumero(1);
		List<GustoPedido> gustoPedido = new ArrayList<GustoPedido>();
		GustoPedido gusto = new GustoPedido();
		gusto.setNomnbre("IPA");
		gusto.setCantidadPedida(3);
		gustoPedido.add(gusto);
		p.setGustosPedido(gustoPedido);
		p.setMonto(100);
		
		pedidos.add(p);
		p = new Pedido();
		p.setEstadoPedido(ePedido.SOLICITADO);
		p.setNumero(2);
		gustoPedido = new ArrayList<GustoPedido>();
		gusto = new GustoPedido();
		gusto.setNomnbre("PORTER");
		gusto.setCantidadPedida(3);
		gustoPedido.add(gusto);
		p.setGustosPedido(gustoPedido);
		p.setMonto(100);
		
		pedidos.add(p);		
		p = new Pedido();
		p.setEstadoPedido(ePedido.SOLICITADO);
		p.setNumero(3);
		gustoPedido = new ArrayList<GustoPedido>();
			gusto = new GustoPedido();
			gusto.setNomnbre("PORTER");
			gusto.setCantidadPedida(3);
			gustoPedido.add(gusto);
			gusto = new GustoPedido();
			gusto.setNomnbre("IPA");
			gusto.setCantidadPedida(2);
			gustoPedido.add(gusto);

		
		
		
		p.setGustosPedido(gustoPedido);
		p.setMonto(100);
		
		pedidos.add(p);
		
		ultimo=3;
	}
	
	private List<Pedido> getListaPedidos(){
		return pedidos;
	}

	@Override
	public List<Pedido> getAll() {
		return this.getListaPedidos();
	}

	@Override
	public List<Pedido> getByID(String campo, String valor) {
		return null;
	}

	@Override
	public List<Pedido> getByCampoString(String campo, String valor) {
		return null;
	}

	@Override
	public List<Pedido> getByCampoInteger(String campo, Integer valor) {
		return null;
	}

	@Override
	public List<Pedido> getByCampoDouble(String campo, double valor) {
		return null;
	}

	@Override
	public void Update(Pedido p) {
	}

	@Override
	public void Insert(Pedido p) {
		ultimo++;
		p.setNumero(ultimo);
		
		pedidos.add(p);
	}

}

package edu.cerveapp.Repo.RepoList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cerveapp.IRepo.IRepo;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.ePedido;

public class RepoList implements IRepo {
	
	
	
	private static int id_pedido;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Pedido> pedidos =  new ArrayList<Pedido>();
	private List<GustoStock> gustosDisponibles= new ArrayList<GustoStock>();
	
	public RepoList() {
		llenarUsuarios();
		llenarPedidos();
		llenarGustos();
	}

	private void llenarGustos() {
		
		GustoStock gusto = new GustoStock();
		gusto.setNomnbre("IPA");
		
		gustosDisponibles.add(gusto);
		
		gusto = new GustoStock();
		gusto.setNomnbre("HONNEY");
		gustosDisponibles.add(gusto);
		
		gusto = new GustoStock();
		gusto.setNomnbre("POPTER");
		gustosDisponibles.add(gusto);
		
	}
	private void llenarUsuarios() {
		Usuario u = new Usuario();
		
		u.setApellido("Scigliotto");
		u.setDireccion("Corrientes 4258");
		u.setDni("30333280");
		u.setNombre("Guillermo");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		usuarios.add(u);
		
		
		u = new Usuario();
		u.setApellido("ApellidoTEST1");
		u.setDireccion("DireccionTEST1");
		u.setDni("CUIT TEST1");
		u.setNombre("NOMBRE TEST1");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		usuarios.add(u);

		
		u = new Usuario();
		u.setApellido("ApellidoTEST2");
		u.setDireccion("DireccionTEST2");
		u.setDni("CUIT TEST2");
		u.setNombre("NOMBRE TEST2");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		usuarios.add(u);
		
		
		
		u = new Usuario();
		u.setApellido("ApellidoTEST3");
		u.setDireccion("DireccionTEST3");
		u.setDni("CUIT TEST3");
		u.setNombre("NOMBRE TEST3");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		usuarios.add(u);

	}
	
	private void llenarPedidos() {

		Usuario usuario = obtenerUsuarios().get(1);
		
		
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
		p.setUsuario(usuario);

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
		p.setUsuario(usuario);
		
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

		
		
		p.setUsuario(usuario);
		p.setGustosPedido(gustoPedido);
		p.setMonto(100);
		
		pedidos.add(p);
		
		id_pedido=3;

		
	}
	
	
	private List<Usuario> getListaUsuarios() {
		

		return usuarios;
	}

	

	@Override
	public List<GustoStock> obtenerGustosStock() {
		return gustosDisponibles;
	}

	@Override
	public List<Pedido> obtenerPedidos() {
			
		return pedidos;

	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return this.getListaUsuarios();
	}

	@Override
	public void actualizarUsuario(Usuario u) {
		
	}

	@Override
	public void actualizarPedido(Pedido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarGustoStock(GustoStock g) {
	}



	@Override
	public Usuario buscarUsuario(String dato) {
		boolean encontre=false;
		Iterator<Usuario> it = getListaUsuarios().iterator();
		Usuario u = null;
		while(it.hasNext()&&!encontre)
		{
			u = it.next();
			if(u.getDni().equals(dato)) {
				encontre=true;
				
			}
		}
		if (encontre==false) u=null;
		return u;
		
	}



	@Override
	public void insertarPedido(Pedido p) {
		
		id_pedido++;
		p.setNumero(id_pedido);
		
		pedidos.add(p);
	}


}

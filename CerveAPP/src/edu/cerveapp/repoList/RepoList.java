package edu.cerveapp.repoList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.ePedido;

public class RepoList implements IRepo {

	private static int id_pedido;
	private List<GustoStock> gustosDisponibles = new ArrayList<GustoStock>();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public RepoList() {
		llenarUsuarios();
		llenarPedidos();
		llenarGustos();
	}

	@Override
	public void actualizarGustoStock(GustoStock g) {
	}

	@Override
	public void actualizarPedido(Pedido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarUsuario(Usuario u) {

	}

	@Override
	public Usuario buscarUsuario(String dato) {
		boolean encontre = false;
		Iterator<Usuario> it = getListaUsuarios().iterator();
		Usuario usuario = null;
		while (it.hasNext() && !encontre) {
			usuario = it.next();
			if (usuario.getDni().equals(dato)) {
				encontre = true;
			}
		}
		if (encontre == false)
			usuario = null;
		return usuario;

	}

	@Override
	public GustoStock getGustoByNombre(String gustonNombre) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Usuario> getListaUsuarios() {

		return usuarios;
	}

	@Override
	public Usuario getUsuarioByIdExterno(String IdExterno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarPedido(Pedido p) {

		id_pedido++;
		p.setNumero(id_pedido);

		pedidos.add(p);
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

	private void llenarPedidos() {

		Usuario usuario = obtenerUsuarios().get(1);

		Pedido pedido = new Pedido();
		pedido.setEstadoPedido(ePedido.SOLICITADO);
		pedido.setNumero(1);

		List<GustoPedido> gustoPedido = new ArrayList<GustoPedido>();
		GustoPedido gusto = new GustoPedido();
		gusto.setNomnbre("IPA");
		gusto.setCantidadPedida(3);
		gustoPedido.add(gusto);
		pedido.setGustosPedido(gustoPedido);
		pedido.setMonto(100);
		pedido.setUsuario(usuario);

		pedidos.add(pedido);
		pedido = new Pedido();
		pedido.setEstadoPedido(ePedido.SOLICITADO);
		pedido.setNumero(2);
		gustoPedido = new ArrayList<GustoPedido>();
		gusto = new GustoPedido();
		gusto.setNomnbre("PORTER");
		gusto.setCantidadPedida(3);
		gustoPedido.add(gusto);
		pedido.setGustosPedido(gustoPedido);
		pedido.setMonto(100);
		pedido.setUsuario(usuario);

		pedidos.add(pedido);
		pedido = new Pedido();
		pedido.setEstadoPedido(ePedido.SOLICITADO);
		pedido.setNumero(3);
		gustoPedido = new ArrayList<GustoPedido>();
		gusto = new GustoPedido();
		gusto.setNomnbre("PORTER");
		gusto.setCantidadPedida(3);
		gustoPedido.add(gusto);
		gusto = new GustoPedido();
		gusto.setNomnbre("IPA");
		gusto.setCantidadPedida(2);
		gustoPedido.add(gusto);

		pedido.setUsuario(usuario);
		pedido.setGustosPedido(gustoPedido);
		pedido.setMonto(100);

		pedidos.add(pedido);

		id_pedido = 3;

	}

	private void llenarUsuarios() {
		Usuario usuario = new Usuario();

		usuario.setApellido("Scigliotto");
		usuario.setDireccion("Corrientes 4258");
		usuario.setDni("30333280");
		usuario.setNombre("Guillermo");
		usuario.setMail("gscigliotto@gmail.com");
		usuario.setPaas("123456");

		usuarios.add(usuario);

		usuario = new Usuario();
		usuario.setApellido("ApellidoTEST1");
		usuario.setDireccion("DireccionTEST1");
		usuario.setDni("CUIT TEST1");
		usuario.setNombre("NOMBRE TEST1");
		usuario.setMail("gscigliotto@gmail.com");
		usuario.setPaas("123456");

		usuarios.add(usuario);

		usuario = new Usuario();
		usuario.setApellido("ApellidoTEST2");
		usuario.setDireccion("DireccionTEST2");
		usuario.setDni("CUIT TEST2");
		usuario.setNombre("NOMBRE TEST2");
		usuario.setMail("gscigliotto@gmail.com");
		usuario.setPaas("123456");

		usuarios.add(usuario);

		usuario = new Usuario();
		usuario.setApellido("ApellidoTEST3");
		usuario.setDireccion("DireccionTEST3");
		usuario.setDni("CUIT TEST3");
		usuario.setNombre("NOMBRE TEST3");
		usuario.setMail("gscigliotto@gmail.com");
		usuario.setPaas("123456");

		usuarios.add(usuario);

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
	public List<GustoPedido> obtenerGustoPedido(int id) throws OperationalCRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}

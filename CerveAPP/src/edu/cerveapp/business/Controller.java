package edu.cerveapp.business;


import java.util.List;

import edu.cerveapp.dao.repos.IRepo;
import edu.cerveapp.dao.repos.IRepoGustoStock;
import edu.cerveapp.dao.repos.IRepoPedidos;
import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;

public class Controller {

	private IRepoUsuarios repoUsuario;
	private IRepoPedidos repoPedidos;
	private IRepoGustoStock repoGustoStock;
	private IviewCerveApp view;

	public Controller(IRepo repo, IviewCerveApp view) {
		crearPedidoRepo(repo);
		crearUsuarioRepo(repo);
		crearGustoStockRepo(repo);
		this.view = view;
	}

	private void crearGustoStockRepo(IRepo repo) {
		setRepoGustoStock(repo.createGustoStockRepo());
	}

	private void crearPedidoRepo(IRepo repo) {
		setRepoPedidos(repo.createPedidoRepo());
	}

	private void crearUsuarioRepo(IRepo repo) {
		setRepoUsuario(repo.createUsuarioRepo());
	}

	private IRepoUsuarios getRepoUsuario() {
		return repoUsuario;
	}

	private void setRepoUsuario(IRepoUsuarios repoUsuario) {
		this.repoUsuario = repoUsuario;
	}

	private IRepoPedidos getRepoPedidos() {
		return repoPedidos;
	}

	private void setRepoPedidos(IRepoPedidos repoPedidos) {
		this.repoPedidos = repoPedidos;
	}

	private IRepoGustoStock getRepoGustoStock() {
		return repoGustoStock;
	}

	private void setRepoGustoStock(IRepoGustoStock repoGustoStock) {
		this.repoGustoStock = repoGustoStock;
	}

	private void cambiarEstadoPedido(Pedido pedido) {

	}

	public void startApp()  {
		String opcion;
		Usuario u = login();
		do {
			opcion = view.mostrarMenu(u.getNombre());

			switch (opcion) {
				case "1":
					view.listarPedidos(getRepoPedidos().getAll());
					break;
				case "2":
					Pedido p = view.crearPedido(this);
					getRepoPedidos().insert(p);
					break;
				case "exit":
					break;
			}
		}while(opcion!="exit");

	}

	public List<GustoStock> obtenerGustos() {
		

		return getRepoGustoStock().getAll();
	}




	
	
	public Usuario buscarUsuario(String dni) throws UsuarioInvalidoException {
		Usuario u = getRepoUsuario().getByID("", dni);
		if (u == null)
			throw new UsuarioInvalidoException();
		return u;
	}

	private Usuario login() {
		Usuario u;
		Usuario guardado = null;
		boolean usuarioOK=false;
		do{
		try {
			u = this.view.loginUsuario();
			guardado = buscarUsuario(u.getDni());
			guardado.validarUsuario(u.getPaas());
			usuarioOK=true;
		} catch (UsuarioInvalidoException e) {
			
		}
		}while(!usuarioOK);
		
		return guardado;
	}

}

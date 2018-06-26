package edu.cerveapp.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cerveapp.dao.repos.IRepo;
import edu.cerveapp.dao.repos.IRepoGustoStock;
import edu.cerveapp.dao.repos.IRepoPedidos;
import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.dao.repos.RepoBD.RepoUsuarios;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.cerveapp.entities.ePedido;

public class Controller {

	private IRepoUsuarios repoUsuario = null;
	private IRepoPedidos repoPedidos = null;
	private IRepoGustoStock repoGustoStock = null;
	private IviewCerveApp view = null;

	public Controller(IRepo repo, IviewCerveApp view) {
		this.CrearPedidoRepo(repo);
		this.CrearUsuarioRepo(repo);
		this.CrearGustoStockRepo(repo);
		this.view = view;
	}

	private void CrearGustoStockRepo(IRepo repo) {
		this.setRepoGustoStock(repo.createGustoStockRepo());
	}

	private void CrearPedidoRepo(IRepo repo) {
		this.setRepoPedidos(repo.createPedidoRepo());
	}

	private void CrearUsuarioRepo(IRepo repo) {
		this.setRepoUsuario(repo.createUsuarioRepo());
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

	private void CambiarEstadoPedido(Pedido pedido) {

	}

	public void startApp() throws UsuarioInvalidoException {
		String opcion;
		Usuario u = login();
		do {
			opcion = view.mostrarMenu(u.getNombre());

			switch (opcion) {
				case "1":
					view.listar_pedidos(getRepoPedidos().getAll());
					break;
				case "2":
					Pedido p = view.CrearPedido(this);
					getRepoPedidos().Insert(p);
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

	private Usuario login() throws UsuarioInvalidoException {
		Usuario u = this.view.loginUsuario();
		Usuario guardado = buscarUsuario(u.getDni());
		guardado.validarUsuario(u.getPaas());
		return guardado;
	}

}

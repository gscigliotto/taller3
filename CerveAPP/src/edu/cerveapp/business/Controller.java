package edu.cerveapp.business;

import java.util.List;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;

import org.json.JSONException;

public class Controller {

	private IviewCerveApp view;
	private IRepo repo;
	private IniManager configuracion;

	public Controller(IRepo repo, IviewCerveApp view, IniManager configuracion) throws IllegalArgumentException {
		if (repo == null || view == null || configuracion == null) {
			throw new IllegalArgumentException("Alguno de los parametros no esta instanciado.");
		}
		this.view = view;
		this.repo = repo;
		this.configuracion = configuracion;
	}

	public void startApp() throws JSONException, InvalidConfigurationException {

		String opcion = null;
		Usuario u = login();
		do {
			try {
				opcion = view.mostrarMenu(u.getNombre());

				switch (opcion) {
				case "1":
					view.listarPedidos(repo.obtenerPedidos(), this);
					break;
				case "2":
					Pedido p = view.crearPedido(this);
					repo.insertarPedido(p);
					break;
				case "3":
					try {
						buscarPedidos();
					} catch (NotFoundSeccionExeption e) {
						// TODO Auto-generated catch block
						view.mostrarMsg("Hay un problema de configuracion." + e.getMessage());
					}
					view.mostrarMsg("Se cargaron todos los pedidos de la PaginaWeb!!");
					break;
				case "exit":

					;
					break;
				}
			} catch (OperationalCRUDException e) {
				view.mostrarMsg("Error de conexión con la BD.");

			}
		} while (!opcion.equals("exit"));

	}

	public void actualilzarPedido(Pedido p) throws OperationalCRUDException {
		repo.actualizarPedido(p);
	}

	public List<GustoStock> obtenerGustos() throws OperationalCRUDException {

		return repo.obtenerGustosStock();
	}

	private void buscarPedidos()
			throws NotFoundSeccionExeption, JSONException, InvalidConfigurationException, OperationalCRUDException {

		PedidosWeb pedidosWeb = new PedidosWeb(configuracion.getSeccion("ENTORNO").getItems().get("url_pedidos"), repo);
		List<Pedido> pedidosweb = pedidosWeb.procesar();

	}

	public Usuario buscarUsuario(String dni) throws UsuarioInvalidoException, OperationalCRUDException {
		Usuario u = repo.buscarUsuario(dni);
		if (u == null)
			throw new UsuarioInvalidoException();
		return u;
	}

	private Usuario login() {
		Usuario u;
		Usuario guardado = null;
		boolean usuarioOK = false;
		do {
			try {
				u = this.view.loginUsuario();
				guardado = buscarUsuario(u.getDni());
				guardado.validarUsuario(u.getPaas());
				usuarioOK = true;
			} catch (UsuarioInvalidoException e) {
				view.mostrarMsg("Usuario o contraseña invalida.");
			} catch (OperationalCRUDException e) {
				view.mostrarMsg("Error de conexión con la BD.");
			}
		} while (!usuarioOK);

		return guardado;
	}

}

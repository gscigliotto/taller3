package edu.cerveapp.business;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import edu.cerveapp.entities.Configuracion;
import edu.cerveapp.entities.Credenciales;
import edu.cerveapp.entities.GustoStock;

import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.utils.GeneratePDFFileIText;
import edu.cerveapp.vista.View;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;

public class Controller {

	private Configuracion configuracion;
	private View view;

	public Controller(Configuracion configuracion) throws InvalidConfigurationException {
		if (configuracion.getRepo() == null || configuracion.getInimanager() == null) {
			throw new InvalidConfigurationException(
					"La configuracion no es correcta corrobore que el archivo de configuracion tenga todos los parametros necesarios!.");
		} else {
			view = new View();
			this.configuracion = configuracion;
		}

		this.configuracion = configuracion;
	}

	public void actualilzarPedido(Pedido p) {
		configuracion.getRepo().actualizarPedido(p);
	}

	private void buscarPedidos() {

		PedidosWeb pedidosWeb = null;
		try {

			pedidosWeb = new PedidosWeb(configuracion);
			pedidosWeb.procesar();
		} catch (InvalidConfigurationException | OperationalCRUDException e) {
			view.mostrarMsg("Hubo un error procesando los pedidos!!" + e.getMessage());

		}

		view.mostrarMsg("Se cargaron todos los pedidos de la PaginaWeb!!");
	}

	public Usuario buscarUsuario(String dni) {
		return configuracion.getRepo().buscarUsuario(dni);
	}

	private Usuario login() {
		Credenciales credenciales;
		Usuario usuario = null;
		boolean usuarioOK = false;

		do {
			credenciales = this.view.loginUsuario();
			usuario = buscarUsuario(credenciales.getUsuario());
			if (usuario != null) {
				usuarioOK = usuario.validarUsuario(credenciales.getPassword());
			} else {
				view.mostrarMsg("Usuario o contraseña invalidos");
			}
		} while (!usuarioOK);
		return usuario;
	}

	public List<GustoStock> obtenerGustos(){
		return configuracion.getRepo().obtenerGustosStock();
	}

	public void startApp() {

		String opcion = null;
		Usuario usuario = login();
		do {
			try {
				opcion = view.mostrarMenu(usuario.getNombre());

				switch (opcion) {
				case "1":
					listarPedido();
					break;
				case "2":
					crearPedido();
					break;
				case "3":
					buscarPedidos();
					break;
				case "4":
					altaUsuario();
					break;
				case "5":
					listarUsuarios();
					break;
				default:
					break;
				}
			} catch (OperationalCRUDException e) {
				view.mostrarMsg("Error de conexión con la BD." + e.getMessage());

			}
		} while (!opcion.equals("exit"));

	}

	private void listarUsuarios() {
		view.listarUsuarios(configuracion.getRepo().obtenerUsuarios());

	}

	private void listarPedido() {
		view.listarPedidos(configuracion.getRepo().obtenerPedidos(), this);
	}

	private void crearPedido() {
		Pedido pedido = view.crearPedido(this);
		configuracion.getRepo().insertarPedido(pedido);

	}

	private void altaUsuario() {
		Usuario usuario = view.crearUsuario();
		configuracion.getRepo().insertarUsuario(usuario);

	}

	public void generarRemito(Pedido pedido) {
		try {
			GeneratePDFFileIText pdf = new GeneratePDFFileIText();
			pdf.createPDF(pedido);
			view.mostrarMsg("Se genero correctamente el Remito para el pedido " + String.valueOf(pedido.getId()));
		} catch (InvalidConfigurationException | DocumentException e) {
			File archivo = new File(configuracion.getSeccion().getValorClave("remitos_path")+String.valueOf(pedido.getId())+".pdf");
			if(archivo.exists())
				archivo.delete();
			view.mostrarMsg("Error generando el PDF: " + e.getMessage());
		}

	}

}

package edu.cerveapp.business;



import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import org.json.JSONException;
public class Controller {


	private IviewCerveApp view;
	private IRepo repo;
	public Controller(IRepo repo, IviewCerveApp view) {
		this.view = view;
		this.repo = repo;
	}

	private void cambiarEstadoPedido(Pedido pedido) {

	}

	public void startApp()  {
		buscarPedidos();
		String opcion;
		Usuario u = login();
		do {
			opcion = view.mostrarMenu(u.getNombre());

			switch (opcion) {
				case "1":
					view.listarPedidos(repo.obtenerPedidos());
					break;
				case "2":
					Pedido p = view.crearPedido(this);
					repo.insertarPedido(p);
					break;

				case "exit":
					break;
			}
		}while(opcion!="exit");

	}

	public List<GustoStock> obtenerGustos() {
		

		return repo.obtenerGustosStock();
	}


	private void buscarPedidos() {
		try {
			
			PedidosWeb pedidosWeb = new PedidosWeb("https://labbo.co/guille/salida_plana.txt",repo);
			List<Pedido>pedidosweb=pedidosWeb.procesar();
			

			
		} catch (JSONException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public Usuario buscarUsuario(String dni) throws UsuarioInvalidoException {
		Usuario u = repo.buscarUsuario(dni);
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

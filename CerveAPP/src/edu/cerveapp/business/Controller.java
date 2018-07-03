package edu.cerveapp.business;




import java.util.List;



import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.IviewCerveApp;
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
	public Controller(IRepo repo, IviewCerveApp view,IniManager configuracion) {
		this.view = view;
		this.repo = repo;
		this.configuracion=configuracion;
	}



	public void startApp()  {

		String opcion;
		Usuario u = login();
		do {
			opcion = view.mostrarMenu(u.getNombre());

			switch (opcion) {
				case "1":
					view.listarPedidos(repo.obtenerPedidos(),this);
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
					view.mostrarMsg("Hay un problema de configuracion."+e.getMessage());
				}
					view.mostrarMsg("Se cargaron todos los pedidos de la PaginaWeb!!");
					break;
				case "exit":
					break;
			}
		}while(opcion!="exit");

	}
	public void actualilzarPedido(Pedido p) {
		repo.actualizarPedido(p);
	}
	public List<GustoStock> obtenerGustos() {
		

		return repo.obtenerGustosStock();
	}


	private void buscarPedidos() throws NotFoundSeccionExeption {
		try {
			
			
			
			PedidosWeb pedidosWeb = new PedidosWeb(configuracion.getSeccion("ENTORNO").getItems().get("url_pedidos"),repo);
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

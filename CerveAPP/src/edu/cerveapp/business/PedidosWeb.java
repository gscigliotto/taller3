package edu.cerveapp.business;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import edu.cerveapp.entities.Configuracion;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.ePedido;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;






public class PedidosWeb {

	private static final int COL_USUARIO=0;
	private static final int COL_GUSTO=2;
	private static final int COL_CANTIDAD=3;
	private IRepo repo;
	private String url;
	
	private Usuario buscarUsuario(String idExt) {
		return repo.getUsuarioByIdExterno(idExt);
	}

	private GustoStock getGustosStock(String nombre) throws OperationalCRUDException {
		return repo.getGustoByNombre(nombre);
	}

	public IRepo getRepo() {
		return repo;
	}

	public String getUrl() {
		return url;
	}
	public void setRepo(IRepo repo) {
		this.repo = repo;
	}

	public void setUrl(String url) {
		this.url = url;

	}
	
	public PedidosWeb(String url, IRepo repo) throws InvalidConfigurationException {
		if (url.isEmpty() || repo == null)
			throw new InvalidConfigurationException("La URL no puede estar vacia");
		setUrl(url);
		setRepo(repo);
	}

	public PedidosWeb(Configuracion configuracion) throws InvalidConfigurationException {
	
		String url;
		try {
			url = configuracion.getInimanager().getSeccion("ENTORNO").getValorClave("url_pedidos");
			if (url.isEmpty()||configuracion.getRepo() == null)
				throw new InvalidConfigurationException("La URL no puede estar vacia o el repositorio no esta configurado");
			
			setUrl(url);
			setRepo(configuracion.getRepo());
			
		} catch (NotFoundSeccionExeption e) {
			throw new InvalidConfigurationException(e.getMessage());
		}
		

	}



	public void procesar() throws OperationalCRUDException {

		InputStream archivoExterno = null;
		try {
			archivoExterno = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(archivoExterno, Charset.forName("UTF-8")));
			String cadena;
			Pedido pedido;

			while ((cadena = rd.readLine()) != null) {

				String[] pedidoLinea = cadena.split(";");
				Usuario usuario = buscarUsuario(pedidoLinea[COL_USUARIO]);
				List<GustoPedido> gustosPedido = new ArrayList<GustoPedido>();
				GustoStock gustoStock = getGustosStock(pedidoLinea[COL_GUSTO]);

				pedido = new Pedido(0, (gustoStock.getPrecio() * Integer.parseInt(pedidoLinea[COL_CANTIDAD])), null, ePedido.SOLICITADO,usuario);
				gustosPedido.add(new GustoPedido(0, pedido.getId(), gustoStock.getId(), Double.parseDouble(pedidoLinea[COL_CANTIDAD]),gustoStock.getPrecio(), gustoStock.getNomnbre()));
				pedido.setGustosPedido(gustosPedido);

				repo.insertarPedido(pedido);

			}
			archivoExterno.close();
		} catch (IOException e) {
			throw new OperationalCRUDException(e.getMessage()) ;
		}
	}




}

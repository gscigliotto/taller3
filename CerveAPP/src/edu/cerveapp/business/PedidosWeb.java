package edu.cerveapp.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.ePedido;

public class PedidosWeb {
	public IRepo getRepo() {
		return repo;
	}

	public void setRepo(IRepo repo) {
		this.repo = repo;
	}

	private IRepo repo;

	public String getUrl() {
		return url;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public void setUrl(String url) {
		this.url = url;

	}

	private String url;

	public PedidosWeb(String url, IRepo repo) throws InvalidConfigurationException {
		if (url.isEmpty() || repo == null)
			throw new InvalidConfigurationException("La URL no puede estar vacia");
		setUrl(url);
		setRepo(repo);

	}

	private Usuario buscarUsuario(String idExt) {
		return repo.getUsuarioByIdExterno(idExt);

	}

	private GustoStock getGustosStock(String nombre) throws OperationalCRUDException {
		return repo.getGustoByNombre(nombre);
	}

	public List<Pedido> procesar() throws OperationalCRUDException {

		InputStream is = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String cadena;
			Pedido pedido;

			while ((cadena = rd.readLine()) != null) {

				String[] str = cadena.split(";");
				Usuario u = buscarUsuario(str[0]);
				List<GustoPedido> gustosPedido = new ArrayList<GustoPedido>();
				GustoStock gustoStock = getGustosStock(str[2]);

				pedido = new Pedido(0, (gustoStock.getPrecio() * Integer.parseInt(str[3])), null, ePedido.SOLICITADO, u);
				gustosPedido.add(new GustoPedido(0, pedido.getIdRaw(), gustoStock.getId(), Double.parseDouble(str[3]),
						gustoStock.getPrecio(), gustoStock.getNomnbre()));
				pedido.setGustosPedido(gustosPedido);

				repo.insertarPedido(pedido);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}

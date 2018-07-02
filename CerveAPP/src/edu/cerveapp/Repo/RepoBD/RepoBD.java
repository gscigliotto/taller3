package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import tc3.tp3.utils.db.DBConfig;
import tc3.tp3.utils.db.DBManager;

public class RepoBD implements IRepo {

	private GustoStockManager gustoStockMng;
	private PedidoManager pedidoMng;
	private UsuarioManager usuarioMng;
	private GustosPedidoManager gustoPedidoMng;
	private boolean recrear;
	private Connection conn;

	public void cargarURL(String connectionFile) throws NotFoundSeccionExeption, InvalidConfigurationException {
		if (connectionFile.isEmpty())
			throw new InvalidConfigurationException("La ruta de la configuracion de bd no puede estar vacia");

		DBConfig config = new DBConfig(connectionFile);
		DBManager manager = new DBManager(config);
		try {
			conn = manager.getNewConnection(config.getURL());
			gustoStockMng = new GustoStockManager(conn);
			pedidoMng = new PedidoManager(conn);
			usuarioMng = new UsuarioManager(conn);
			gustoPedidoMng= new GustosPedidoManager(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public RepoBD(String connectionFile, boolean recrear)
			throws NotFoundSeccionExeption, InvalidConfigurationException {
		cargarURL(connectionFile);
		this.recrear = recrear;
		if (recrear && estructuraCreada()) {
			borrarEstructura();
			crearEstructura();
		} else if (!estructuraCreada()) {
			crearEstructura();
		}
	}

	private void crearEstructura() {
		usuarioMng.crearTabla();
		gustoStockMng.crearTabla();
		pedidoMng.crearTabla();
		gustoPedidoMng.crearTabla();

	}

	private void borrarEstructura() {
		usuarioMng.borrarTabla();
		gustoStockMng.borrarTabla();
		pedidoMng.borrarTabla();
		gustoPedidoMng.borrarTabla();
	}

	private boolean estructuraCreada() {
		String query = "select count(name) cantidad from sys.tables where name in('usuarios','gustos','pedidos')";
		boolean ret = false;

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			rs.next();
			int ktablas = rs.getInt("cantidad");
			if (ktablas > 0)
				ret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<GustoStock> obtenerGustosStock() {
		// TODO Auto-generated method stub
		return gustoStockMng.obtenerGustos();
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		return pedidoMng.obtenerPedidos();
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioMng.obtenerUsuarios();
	}

	@Override
	public void actualizarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario buscarUsuario(String dato) {
		return usuarioMng.obtenerUsuarios(dato);
	}

	@Override
	public void actualizarPedido(Pedido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertarPedido(Pedido p) {
		pedidoMng.insertarPedido(p);

	}

	@Override
	public void actualizarGustoStock(GustoStock g) {
		// TODO Auto-generated method stub

	}

}

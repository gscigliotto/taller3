package edu.cerveapp.repoDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import tc3.tp3.utils.db.DBConfig;
import tc3.tp3.utils.db.DBManager;

public class RepoBD implements IRepo {

	private Connection conn;
	private GustosPedidoManager gustoPedidoMng;
	private GustoStockManager gustoStockMng;
	private PedidoManager pedidoMng;
	private boolean recrear;
	private UsuarioManager usuarioMng;

	public RepoBD(String connectionFile, boolean recrear) throws InvalidConfigurationException,
			IllegalArgumentException, OperationalCRUDException {
		this.recrear = recrear;

		cargarURL(connectionFile);


		if (recrear && estructuraCreada()) {

			borrarEstructura();
			crearEstructura();

		} else if (!estructuraCreada()) {
			crearEstructura();
		}
	}

	@Override
	public void actualizarGustoStock(GustoStock g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarPedido(Pedido p) throws OperationalCRUDException {

		pedidoMng.ActualizarPedido(p);

	}

	@Override
	public void actualizarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	private void borrarEstructura() throws OperationalCRUDException {
		usuarioMng.borrarTabla();
		gustoStockMng.borrarTabla();
		gustoPedidoMng.borrarTabla();
		pedidoMng.borrarTabla();
		
	}

	@Override
	public Usuario buscarUsuario(String dato) throws OperationalCRUDException {
		return usuarioMng.obtenerUsuariosByDNI(dato);
	}

	public void cargarURL(String connectionFile) throws  InvalidConfigurationException {
		if (connectionFile.isEmpty())
			throw new InvalidConfigurationException("La ruta de la configuracion de bd no puede estar vacia");


		try {
			DBConfig config = new DBConfig(connectionFile);
			DBManager manager = new DBManager(config);
			conn = manager.getNewConnection(config.getURL());
			gustoStockMng = new GustoStockManager(conn);
			pedidoMng = new PedidoManager(conn, this);
			usuarioMng = new UsuarioManager(conn);
			gustoPedidoMng = new GustosPedidoManager(conn);

		} catch (IllegalArgumentException |NotFoundSeccionExeption e) {
			throw new InvalidConfigurationException(e.getMessage());
		}catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}

	private void crearEstructura() throws OperationalCRUDException {
		usuarioMng.crearTabla();
		gustoStockMng.crearTabla();
		pedidoMng.crearTabla();
		gustoPedidoMng.crearTabla();

	}

	private boolean estructuraCreada() throws OperationalCRUDException  {
		String query = "select count(name) cantidad from sys.tables where name in('usuarios','gustos','pedidos')";
		boolean ret = false;

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			rs.next();
			int ktablas = rs.getInt("cantidad");
			if (ktablas > 0)
				ret = true;
		} catch (SQLException e) {
			new OperationalCRUDException(e.getMessage()); 
		}

		return ret;
	}

	@Override
	public GustoStock getGustoByNombre(String gustonNombre) throws OperationalCRUDException {
		GustoStock gustoStock = null;
		gustoStock = gustoStockMng.obtenerGustos(gustonNombre);

		return gustoStock;
	}

	@Override
	public Usuario getUsuarioByIdExterno(String IdExterno) throws OperationalCRUDException  {
		return usuarioMng.obtenerUsuariosByIdExt(IdExterno);
	}

	@Override
	public void insertarPedido(Pedido p) throws OperationalCRUDException {

		pedidoMng.insertarPedido(p);

	}

	@Override
	public List<GustoPedido> obtenerGustoPedido(int id) throws OperationalCRUDException {
		return gustoPedidoMng.obtenerGustos(id);
	}

	@Override
	public List<GustoStock> obtenerGustosStock() throws OperationalCRUDException {
		// TODO Auto-generated method stub
		List<GustoStock> gustoStock = null;
		gustoStock = gustoStockMng.obtenerGustos();
		return gustoStock;
	}

	@Override
	public List<Pedido> obtenerPedidos() throws OperationalCRUDException {

		List<Pedido> pedidos = null;
		pedidos = pedidoMng.obtenerPedidos();
		return pedidos;
	}

	@Override
	public List<Usuario> obtenerUsuarios() throws OperationalCRUDException {
		return usuarioMng.obtenerUsuarios();
	}

	@Override
	public void insertarUsuario(Usuario usuario) throws OperationalCRUDException  {
		usuarioMng.insertarUsuario(usuario);
		
	}

}

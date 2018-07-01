package edu.cerveapp.Repo.RepoBD;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import tc3.tp3.utils.db.DBConfig;
import tc3.tp3.utils.db.DBManager;


public class RepoBD implements IRepo{
	public void cargarURL(String connectionFile) throws NotFoundSeccionExeption, InvalidConfigurationException {
		if(connectionFile.isEmpty())
			throw new InvalidConfigurationException("La ruta de la configuracion de bd no puede estar vacia");
		
		DBConfig config = new DBConfig(connectionFile);
		DBManager manager = new DBManager(config);
		try {
			manager.getNewConnection(config.getURL());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public RepoBD(String connectionFile,boolean recrear) throws NotFoundSeccionExeption, InvalidConfigurationException {
		cargarURL(connectionFile);
		if(recrear) {
			crearEstructura();
		}
	}

	


	private void crearEstructura() {
		
	}



	@Override
	public List<GustoStock> obtenerGustosStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario buscarUsuario(String dato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarPedido(Pedido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarPedido(Pedido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarGustoStock(GustoStock g) {
		// TODO Auto-generated method stub
		
	}

}

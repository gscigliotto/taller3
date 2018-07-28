package edu.cerveapp.repoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.ePedido;

public class PedidoManager {
	private Connection conn;
	private IRepo repo;

	public PedidoManager(Connection conn, IRepo repo) {

		this.conn = conn;
		this.repo = repo;
	}

	public void ActualizarPedido(Pedido pedido) throws OperationalCRUDException {
		String query = "UPDATE PEDIDOS SET ESTADO=? WHERE ID=? ";

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, pedido.getEstadoPedido().toString());
			statement.setInt(2, pedido.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}

	public void borrarTabla() throws OperationalCRUDException {
		String query = "DROP TABLE  pedidos;";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}

	public void crearTabla() throws OperationalCRUDException {
		String query = "CREATE TABLE  pedidos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, id_usuario int, estado VARCHAR(10),monto DECIMAL(8,2))";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}

	public void insertarPedido(Pedido pedido) throws OperationalCRUDException {
		String query = "INSERT INTO pedidos (id_usuario,estado,monto)  OUTPUT INSERTED.id VALUES (?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, pedido.getUsuario().getId());
	

			statement.setString(2, pedido.getEstadoPedido().toString());
			statement.setFloat(3, (float) pedido.getMonto());
			
	
			statement.execute();
			ResultSet identities= statement.getGeneratedKeys();
			identities.next();
			int idPedido =  identities.getInt(1);  
			 
 
			//System.out.println(identities.getInt(1));
			Iterator<GustoPedido> it = pedido.getGustosPedido().iterator();
			while (it.hasNext()) {
				GustoPedido gusto = it.next();
				try {

					query = "INSERT INTO gustosPedidos (idpedido,id_gusto,cantidad_pedida,precio_litro) VALUES (?,?,?,?)";
					statement = conn.prepareStatement(query);
					statement.setInt(1, idPedido);
					statement.setInt(2, gusto.getId_gusto());
					statement.setFloat(3, (float) gusto.getCantidadPedida());
					statement.setFloat(4, (float) gusto.getPreciolitro());
					statement.execute();
					

				} catch (SQLException e) {
					throw new OperationalCRUDException(e.getMessage());
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new OperationalCRUDException(e.getMessage());
		}
	}

	public List<Pedido> obtenerPedidos() throws OperationalCRUDException {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String query = "Select p.id id,monto, id_usuario,p.estado estado,monto, dni from pedidos p, usuarios u where p.id_usuario=u.id  ";

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				Usuario u = repo.buscarUsuario(rs.getString("dni"));
				List<GustoPedido> gustos = repo.obtenerGustoPedido(rs.getInt("id"));

				pedidos.add(new Pedido(rs.getInt("id"), rs.getFloat("monto"), gustos,
						ePedido.valueOf(rs.getString("estado")), u));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new OperationalCRUDException(e.getMessage());
		}
		return pedidos;

	}
}

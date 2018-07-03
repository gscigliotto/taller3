package edu.cerveapp.Repo.RepoBD;

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

	public void borrarTabla() {
		String query = "DROP TABLE  pedidos;";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crearTabla() {
		String query = "CREATE TABLE  pedidos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, id_usuario int, estado VARCHAR(10),monto DECIMAL(8,2),idRaw VARCHAR(100))";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void ActualizarPedido(Pedido p) {
		String query = "UPDATE PEDIDOS SET ESTADO=? WHERE IDRAW=? ";

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1,p.getEstadoPedido().toString());
			statement.setString(2,p.getIdRaw().toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertarPedido(Pedido pedido) {
		String query = "INSERT INTO pedidos (id_usuario,idRaw,estado,monto) VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, pedido.getUsuario().getId());
			statement.setString(2, pedido.getIdRaw());

			statement.setString(3, pedido.getEstadoPedido().toString());
			statement.setFloat(4, (float) pedido.getMonto());
			statement.execute();
			Iterator <GustoPedido> it =pedido.getGustosPedido().iterator();
			while(it.hasNext()) {
				GustoPedido gusto= it.next();
				try {
	
						query = "INSERT INTO gustosPedidos (id_pedidoRaw,id_gusto,cantidad_pedida,precio_litro) VALUES (?,?,?,?)";
						statement = conn.prepareStatement(query);
						statement.setString(1, pedido.getIdRaw());
						statement.setInt(2, gusto.getId_gusto());
						statement.setFloat(3, (float) gusto.getCantidadPedida());
						statement.setFloat(4, (float) gusto.getPreciolitro());
						statement.execute();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Pedido> obtenerPedidos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String query = "Select p.id,monto, id_usuario, estado,monto,idRaw, dni from pedidos p, usuarios u where p.id_usuario=u.id  ";

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				Usuario u = repo.buscarUsuario(rs.getString("dni"));
				List<GustoPedido> gustos = repo.obtenerGustoPedido(rs.getString("idRaw"));

				pedidos.add(new Pedido(rs.getInt("id"),rs.getString("idRaw"), rs.getFloat("monto"), gustos,
						ePedido.valueOf(rs.getString("estado")), u));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;

	}
}

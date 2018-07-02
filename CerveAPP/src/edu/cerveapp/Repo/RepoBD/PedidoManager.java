package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;

public class PedidoManager {
	private Connection conn;

	public PedidoManager(Connection conn) {

		this.conn = conn;
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
		String query = "CREATE TABLE  pedidos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, id_usuario int, nropedido int, estado VARCHAR(1),monto DECIMAL(8,2),responseId VARCHAR(50))";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertarPedido(Pedido pedido) {
		String query = "INSERT INTO pedidos (id_usuario,nropedido,estado,monto) VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, pedido.getUsuario().getId());
			statement.setInt(2, pedido.getNumero());

			statement.setString(3, pedido.getEstado());
			statement.setFloat(4, (float) pedido.getMonto());
			statement.execute();
			// ResultSet rs = statement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Pedido> obtenerPedidos() {
		//String query = "select * from pedidos pe inner join usuarios us	on pe.id_usuario=us.id inner join gustospedidos gp on pe.id=gp.id_pedido inner join gustos gu on gp.id_gusto=gu.id ";

		String query = "select pe.id id_pedido,pe.monto,pe.estado estado,pe.nropedido nropedido,us.id id_usuario,gp.id idgustoPedido,gp.id_gusto id_Gusto,";
		query += " gp.cantidad_pedida cantidad_pedida,gp.precio_litro precio_litro,gu.id id_gusto, gu.nombre_gusto gusto, ";
		query += " us.dni dni, us.apellido apellido,us.mail mail,us.nombre nombre_usuario,us.telefono telefono,us.pass pass ";
		query += " from pedidos pe ";
		query += " inner join usuarios us	on pe.id_usuario=us.id ";
		query += " inner join gustospedidos gp on pe.id=gp.id_pedido ";
		query += " inner join gustos gu on gp.id_gusto=gu.id";
		
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getInt("id_pedido"), rs.getInt("nropedido"), rs.getDouble("monto"),null, rs.getString("estado"),new Usuario(rs.getInt("id_pedido"),rs.getString("dni"),rs.getString("nombre_usuario"),rs.getString("apellido"),rs.getString("telefono"),rs.getString("mail"),rs.getString("pass")));
				List<GustoPedido> gustos= new ArrayList<GustoPedido>();
				gustos.add(new GustoPedido(rs.getInt("idgustoPedido"),rs.getInt("id_pedido"), rs.getInt("id_gusto"),  rs.getDouble("cantidad_pedida"), rs.getDouble("precio_litro"),rs.getString("gusto")));
				
				while (rs.next()) {
					gustos.add(new GustoPedido(rs.getInt("idgustoPedido"),rs.getInt("id_pedido"), rs.getInt("id_gusto"),  rs.getDouble("cantidad_pedida"), rs.getDouble("precio_litro"),rs.getString("gusto")));
				}
				pedido.setGustosPedido(gustos);
				pedidos.add(pedido);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;

	}

}

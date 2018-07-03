package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.OperationalCRUDException;

public class GustosPedidoManager {
	private Connection conn;

	public GustosPedidoManager(Connection conn) {
		this.conn = conn;
	}
	
	public void borrarTabla() throws OperationalCRUDException {
		String query ="DROP TABLE  gustosPedidos;";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}
	
	public void crearTabla() throws OperationalCRUDException {
		String query ="CREATE TABLE  gustosPedidos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, id_pedidoRaw VARCHAR(50),id_gusto int, cantidad_pedida DECIMAL(8,2),precio_litro DECIMAL(8,2))";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}
	
	public List<GustoPedido> obtenerGustos(int id_pedido) throws OperationalCRUDException {
		String query = "SELECT id id_gusto,id_pedido,id_gusto,cantidad_pedida, precio_litro, g.nombre_gusto nombre_gusto from gustosPedidos gp, gustos g where g.id=gp.id_gusto and id_pedido=?";
		List<GustoPedido> gustos = new ArrayList<GustoPedido>();

		try {
			PreparedStatement preparedstatement=conn.prepareStatement(query);
			preparedstatement.setInt(1, id_pedido);
			ResultSet rs =preparedstatement.executeQuery();
			while (rs.next()) {
				gustos.add(new GustoPedido(rs.getInt("id_gusto"), rs.getString("id_pedido"),rs.getInt("id_gusto"),rs.getDouble("cantidad_pedida"), rs.getDouble("precio_litro"),rs.getString("nombre_gusto")));
			}
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}
		return gustos;
	}
	
	public List<GustoPedido> obtenerGustos(String idRaw) throws OperationalCRUDException {
		String query = "  SELECT g.id, id_gusto,p.idRaw id_pedido,gp.id_gusto,gp.cantidad_pedida, g.precio_litro, g.nombre_gusto nombre_gusto from gustosPedidos gp, gustos g,pedidos p where p.idraw=gp.id_pedidoRaw and p.idraw=? and  g.id=gp.id_gusto  ";
		List<GustoPedido> gustos = new ArrayList<GustoPedido>();

		try {
			PreparedStatement preparedstatement=conn.prepareStatement(query);
			preparedstatement.setString(1, idRaw);
			ResultSet rs =preparedstatement.executeQuery();
			while (rs.next()) {
				gustos.add(new GustoPedido(rs.getInt("id_gusto"), rs.getString("id_pedido"),rs.getInt("id_gusto"),rs.getDouble("cantidad_pedida"), rs.getDouble("precio_litro"),rs.getString("nombre_gusto")));
			}
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}
		return gustos;
	}


}

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

public class GustosPedidoManager {
	private Connection conn;

	public GustosPedidoManager(Connection conn) {
		this.conn = conn;
	}
	
	public void borrarTabla() {
		String query ="DROP TABLE  gustosPedidos;";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void crearTabla() {
		String query ="CREATE TABLE  gustosPedidos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, id_pedido int,id_gusto int, cantidad_pedida DECIMAL(8,2),precio_litro DECIMAL(8,2))";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<GustoPedido> obtenerGustos(int id_pedido) {
		String query = "SELECT id id_gusto,id_pedido,id_gusto,cantidad_pedida, precio_litro, g.nombre_gusto nombre_gusto from gustosPedidos gp, gustos g where g.id=gp.id_gusto and id_pedido=?";
		List<GustoPedido> gustos = new ArrayList<GustoPedido>();

		try {
			PreparedStatement preparedstatement=conn.prepareStatement(query);
			preparedstatement.setInt(1, id_pedido);
			ResultSet rs =preparedstatement.executeQuery();
			while (rs.next()) {
				gustos.add(new GustoPedido(rs.getInt("id_gusto"), rs.getInt("id_pedido"),rs.getInt("id_gusto"),rs.getDouble("cantidad_pedida"), rs.getDouble("precio_litro"),rs.getString("nombre_gusto")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gustos;
	}

}

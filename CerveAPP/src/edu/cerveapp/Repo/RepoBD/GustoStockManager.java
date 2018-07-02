package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.GustoStock;


public class GustoStockManager {
	private Connection conn;

	public GustoStockManager(Connection conn) {
		this.conn = conn;
	}
	
	public void borrarTabla() {
		String query ="DROP TABLE  gustos;";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void crearTabla() {
		String query ="CREATE TABLE  gustos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, nombre_gusto VARCHAR(20), cantidad_disponible DECIMAL(8,2),precio_litro DECIMAL(8,2))";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<GustoStock> obtenerGustos() {
		String query = "SELECT id,nombre_gusto,cantidad_disponible from GUSTOS";
		List<GustoStock> gustos = new ArrayList<GustoStock>();

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				gustos.add(new GustoStock(rs.getInt("id"), rs.getString("nombre_gusto"), rs.getDouble("cantidad_disponible")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gustos;
	}


}

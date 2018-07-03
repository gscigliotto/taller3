package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.OperationalCRUDException;


public class GustoStockManager {
	private Connection conn;

	public GustoStockManager(Connection conn) throws IllegalArgumentException{
		if(conn==null)throw new IllegalArgumentException("Parametro conn nulo");
		this.conn = conn;
	}
	
	public void borrarTabla() throws OperationalCRUDException {
		String query ="DROP TABLE  gustos;";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

	}
	
	public void crearTabla() throws  OperationalCRUDException {
		String query ="CREATE TABLE  gustos (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, nombre_gusto VARCHAR(20), cantidad_disponible DECIMAL(8,2),precio_litro DECIMAL(8,2))";
		
		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new OperationalCRUDException(e.getMessage());
		}

	}
	
	public List<GustoStock> obtenerGustos() throws OperationalCRUDException {
		String query = "SELECT id,nombre_gusto,cantidad_disponible,precio_litro from GUSTOS";
		List<GustoStock> gustos = new ArrayList<GustoStock>();

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				gustos.add(new GustoStock(rs.getInt("id"), rs.getString("nombre_gusto"), rs.getDouble("cantidad_disponible"), rs.getDouble("precio_litro")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new OperationalCRUDException(e.getMessage());
		}
		return gustos;
	}

	public GustoStock obtenerGustos(String nombre) throws OperationalCRUDException {
		String query = "SELECT id,nombre_gusto,cantidad_disponible,precio_litro from GUSTOS where upper(nombre_gusto) like ?";
		GustoStock gusto = null;

		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, nombre.toUpperCase());
			ResultSet rs = statement.executeQuery();
			

			while (rs.next()) {
				gusto=new GustoStock(rs.getInt("id"), rs.getString("nombre_gusto"), rs.getDouble("cantidad_disponible"),rs.getDouble("precio_litro"));
			}
		} catch (SQLException e) {
			throw new OperationalCRUDException(e.getMessage());
		}

		return gusto;
	}

}

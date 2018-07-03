package edu.cerveapp.Repo.RepoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.Usuario;

public class UsuarioManager {
	private Connection conn;

	public UsuarioManager(Connection conn) {
		this.conn = conn;
	}

	public void borrarTabla() {
		String query = "DROP TABLE  usuarios;";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crearTabla() {
		String query = "CREATE TABLE  usuarios (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, dni VARCHAR(10), nombre VARCHAR(50), apellido "
				+ "VARCHAR(50), telefono VARCHAR(30), mail VARCHAR(40),pass VARCHAR(20),userId VARCHAR(100))";

		try {
			Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Usuario> obtenerUsuarios() {
		String query = "SELECT id,dni,nombre,apellido,telefono,mail, pass from USUARIOS";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("telefono"), rs.getString("mail"),
						rs.getString("pass")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;

	}

	public Usuario obtenerUsuarios(String dni) {
		String query ="SELECT id,dni,nombre,apellido,telefono,mail, pass from USUARIOS where dni=?";
		Usuario usuario = null;


		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, dni);
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()) {
				usuario=new Usuario(rs.getInt("id"),rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("telefono"),rs.getString("mail"),rs.getString("pass"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;

	}







	public Usuario obtenerUsuariosById(String id) {
		String query ="SELECT id,dni,nombre,apellido,telefono,mail, pass from USUARIOS where id=?";
		Usuario usuario = null;


		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()) {
				usuario=new Usuario(rs.getInt("id"),rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("telefono"),rs.getString("mail"),rs.getString("pass"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;

	}


	public Usuario obtenerUsuariosByIdExt(String id) {
		String query ="SELECT id,dni,nombre,apellido,telefono,mail, pass from USUARIOS where userId=?";
		Usuario usuario = null;


		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()) {
				usuario=new Usuario(rs.getInt("id"),rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("telefono"),rs.getString("mail"),rs.getString("pass"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;

	}

}

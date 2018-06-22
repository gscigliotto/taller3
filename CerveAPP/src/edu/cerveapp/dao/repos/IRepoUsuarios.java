package edu.cerveapp.dao.repos;

import java.util.List;

import edu.cerveapp.entities.Usuario;

public interface IRepoUsuarios {
	public List<Usuario> getAll();
	public Usuario getByID(String campo, String valor);
	public List<Usuario> getByCampoString(String campo, String valor);
	public List<Usuario> getByCampoInteger(String campo, Integer valor);
	public List<Usuario> getByCampoDouble(String campo, double valor);
	public void Update(Usuario u);
	public void Insert(Usuario u);

}

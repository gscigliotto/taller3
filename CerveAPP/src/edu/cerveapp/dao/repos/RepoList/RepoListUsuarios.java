package edu.cerveapp.dao.repos.RepoList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.entities.Usuario;

public class RepoListUsuarios implements IRepoUsuarios {
	
	private List<Usuario> getListaUsuarios() {
		List<Usuario> a = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		
		u.setApellido("Scigliotto");
		u.setDireccion("Corrientes 4258");
		u.setDni("30333280");
		u.setNombre("Guillermo");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		a.add(u);
		
		
		u = new Usuario();
		u.setApellido("ApellidoTEST1");
		u.setDireccion("DireccionTEST1");
		u.setDni("CUIT TEST1");
		u.setNombre("NOMBRE TEST1");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		a.add(u);

		
		u = new Usuario();
		u.setApellido("ApellidoTEST2");
		u.setDireccion("DireccionTEST2");
		u.setDni("CUIT TEST2");
		u.setNombre("NOMBRE TEST2");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		a.add(u);
		
		
		
		u = new Usuario();
		u.setApellido("ApellidoTEST3");
		u.setDireccion("DireccionTEST3");
		u.setDni("CUIT TEST3");
		u.setNombre("NOMBRE TEST3");
		u.setMail("gscigliotto@gmail.com");
		u.setPaas("123456");
		
		a.add(u);

		return a;
	}

	@Override
	public List<Usuario> getAll() {
		return this.getListaUsuarios();
	}

	@Override
	public Usuario getByID(String campo, String valor) {
		boolean encontre=false;
		Iterator<Usuario> it = getListaUsuarios().iterator();
		Usuario u = null;
		while(it.hasNext()&&!encontre)
		{
			u = it.next();
				if(u.getDni().equals(valor)) encontre=false;
		}
		return u;
	}

	@Override
	public List<Usuario> getByCampoString(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getByCampoInteger(String campo, Integer valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getByCampoDouble(String campo, double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Usuario u) {
		// TODO Auto-generated method stub
		//this.getByID("DNI", u.getDni());

	}

	@Override
	public void Insert(Usuario u) {
		// TODO Auto-generated method stub

	}

}

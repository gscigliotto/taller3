package edu.cerveapp.vista;

import edu.cerveapp.model.Cerveceria;
import edu.cerveapp.model.IGestion;

public class view implements IGestion {

	private Cerveceria cerve;

	
	view() {
		cerve = new Cerveceria();
	}

	@Override
	public void LoginUsuario(String usuario, String password) {

		// TODO Auto-generated method stub

	}

	@Override
	public void ListarPedidos() {
		// TODO Auto-generated method stub

	}

}

package edu.cerveapp.dao.repos.RepoBD;

import edu.cerveapp.dao.repos.IRepo;

public class RepoBD implements IRepo{

	@Override
	public RepoDBPedidos createPedidoRepo() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		System.out.println("imprimir");
		return null;
	}

	@Override
	public RepoUsuarios createUsuarioRepo() {
		System.out.println("imprimir");
		RepoUsuarios usuario = new RepoUsuarios();
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}

	@Override
	public RepoDBGustoStock createGustoStockRepo() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}

}

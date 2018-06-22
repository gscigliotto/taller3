package edu.cerveapp.dao.repos.RepoBD;

import edu.cerveapp.dao.repos.IRepo;

public class RepoBD implements IRepo{

	@Override
	public RepoDBPedidos createPedidoRepo() {
		// TODO Apéndice de método generado automáticamente
		System.out.println("imprimir");
		return null;
	}

	@Override
	public RepoUsuarios createUsuarioRepo() {
		System.out.println("imprimir");
		RepoUsuarios usuario = new RepoUsuarios();
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public RepoDBGustoStock createGustoStockRepo() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}

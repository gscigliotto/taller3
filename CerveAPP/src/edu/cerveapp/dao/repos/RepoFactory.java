package edu.cerveapp.dao.repos;

public abstract class RepoFactory {
	public abstract RepoPedidos createPedidoRepo();
	public abstract RepoUsuarios createUsuarioRepo();
	public abstract RepoGustoStock createGustoStockRepo();
}

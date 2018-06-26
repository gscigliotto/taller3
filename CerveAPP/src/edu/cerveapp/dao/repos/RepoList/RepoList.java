package edu.cerveapp.dao.repos.RepoList;

import edu.cerveapp.dao.repos.IRepo;
import edu.cerveapp.dao.repos.IRepoGustoStock;
import edu.cerveapp.dao.repos.IRepoPedidos;
import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.dao.repos.RepoBD.RepoDBGustoStock;
import edu.cerveapp.dao.repos.RepoBD.RepoDBPedidos;

public class RepoList implements IRepo {

	@Override
	public IRepoPedidos createPedidoRepo() {
		IRepoPedidos repo;
		repo = new RepoListPedidos();
		return (IRepoPedidos) repo;

	}



	@Override
	public IRepoGustoStock createGustoStockRepo() {
		IRepoGustoStock repo;
		repo = new RepoListGustoStock();
		return (IRepoGustoStock) repo;
	}



	@Override
	public IRepoUsuarios createUsuarioRepo() {
		IRepoUsuarios repo;
		repo = new RepoListUsuarios();
		return (IRepoUsuarios) repo;
	
	}

}

package edu.cerveapp.dao.repos.RepoList;

import edu.cerveapp.dao.repos.IRepo;
import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.dao.repos.RepoBD.RepoDBGustoStock;
import edu.cerveapp.dao.repos.RepoBD.RepoDBPedidos;

public class RepoList implements IRepo {

	@Override
	public RepoDBPedidos createPedidoRepo() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public RepoDBGustoStock createGustoStockRepo() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IRepoUsuarios createUsuarioRepo() {
		IRepoUsuarios repo;
		repo = new RepoListUsuarios();
		return (IRepoUsuarios) repo;
	
	}

}

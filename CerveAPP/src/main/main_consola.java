package main;


import edu.cerveapp.Repo.RepoList.RepoList;
import edu.cerveapp.business.Controller;

import edu.cerveapp.vista.View;
public class main_consola {
	
	
	

	

	public static void main(String[] args)  {
	
		RepoList db = new RepoList();
		View v = new View();
		Controller negocio = new Controller(db,v);
		negocio.startApp();

		
	

	}



}

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import edu.cerveapp.business.Negocio;
import edu.cerveapp.dao.repos.RepoBD.RepoBD;
import edu.cerveapp.dao.repos.RepoList.RepoList;
import edu.cerveapp.entities.*;
import edu.cerveapp.vista.View;
public class main_consola {
	
	
	

	

	public static void main(String[] args) throws UsuarioInvalidoException {
	//RepoBD db = new RepoBD();
		RepoList db = new RepoList();
		View v = new View();
		Negocio negocio = new Negocio(db,v);
		negocio.login();

		
	

	}



}

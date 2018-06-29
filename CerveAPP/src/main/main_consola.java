package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.Repo.RepoBD.RepoBD;
import edu.cerveapp.Repo.RepoList.RepoList;
import edu.cerveapp.business.Controller;
import edu.cerveapp.entities.*;
import edu.cerveapp.vista.View;
public class main_consola {
	
	
	

	

	public static void main(String[] args)  {
	
		RepoList db = new RepoList();
		View v = new View();
		Controller negocio = new Controller(db,v);
		negocio.startApp();

		
	

	}



}

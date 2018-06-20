package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.business.Negocio;
import edu.cerveapp.entities.*;
public class main_consola {
	
	
	

	

	public static void main(String[] args) throws UsuarioInvalidoException {
		String opcion = "I";
		Negocio negocio= new Negocio();	
		Cerveceria c = new Cerveceria();
		c.setNombreCerveceria("CerveApp 0.1");
	//	String usuario=login_usuario(negocio);
		
		while(opcion!="exit"){
			//opcion=mostrarMenu(usuario);
			
			switch(opcion){
			case "1":
				//listar_pedidos(negocio);
				break;
			case "2":
				break;
			}
			
		}
		

		
	

	}



}

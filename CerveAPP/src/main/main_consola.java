package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.busniess.Negocio;
import entities.*;
public class main_consola {
	
	
	

	
	private static String Login_usuario(Negocio negocio) throws UsuarioInvalidoException{
		Scanner s = new Scanner(System.in);
		String usuario = null;
		String password=null;
		System.out.println("****************************************************");
		System.out.println("****************************************************");		
		System.out.println("Bienvenido al sistema de gestion de pedidos CerveApp");
		System.out.println("****************************************************");		
		System.out.println("****************************************************");
		
		System.out.println("Ingrese Usuario:");
		usuario = s.nextLine();
		System.out.println("Ingrese Password:");
		password = s.nextLine();

		negocio.ValidarUsuario(usuario, password);
		return usuario;
		
			
	}

	public static void main(String[] args) throws UsuarioInvalidoException {
		String opcion = "I";
		Negocio negocio= new Negocio();	
		Cerveceria c = new Cerveceria();
		c.setNombreCerveceria("CerveApp 0.1");
		String usuario=Login_usuario(negocio);
		
		while(opcion!="exit"){
			opcion=MostrarMenu(usuario);
			
			switch(opcion){
			case "1":
				listar_pedidos(negocio);
				break;
			case "2":
				break;
			}
			
		}
		

		
	

	}

	private static void listar_pedidos(Negocio negocio){
		Scanner s = new Scanner(System.in);
		String op="";
		boolean encontre=false;
		
		
		while(op!="exit")
		{
			List<Pedido> pedidos = negocio.ObtenerPedidos();
			Iterator<Pedido> it= pedidos.iterator();
			
	
			System.out.println("****************************************************");
			while(it.hasNext())
			{
				Pedido p=(Pedido) it.next();
				System.out.println("Pedido N: "+p.getNumero());
			}
			System.out.println("****************************************************");
			System.out.println("****************************************************");
			System.out.println("Para Ver un pedido ingrese el numero, Para salir escriba 'exit'");
			op = s.nextLine();
			if(op!="exit")
			{
				it= pedidos.iterator();
				while(it.hasNext()&&!encontre)
				{
					Pedido p=(Pedido) it.next();
					if(String.valueOf(p.getNumero()).equals(op)) {
						encontre=true;
						//verpedido
					}
				}
				
			}
		}	
		
	}
	private static String MostrarMenu(String usuario) {
		Scanner s = new Scanner(System.in);
		

		System.out.println("****************************************************");
		System.out.println("Listar Pedidos OP: 1");
		System.out.println("Crear Pedido OP: 2");
		System.out.println("Salir del Sistema: exit");
		System.out.println("****************************************************");		
		String op = s.nextLine();
		return op;
	}

}

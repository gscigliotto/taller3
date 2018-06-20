package edu.cerveapp.vista;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.business.Negocio;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.UsuarioInvalidoException;

public class view implements IviewCerveApp{
	
	public String login_usuario(Negocio negocio){
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

		try {
			negocio.ValidarUsuario(usuario, password);
		} catch (UsuarioInvalidoException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return usuario;
	}

	
	public  void listar_pedidos(Negocio negocio){
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
						verPedido(p);
					}
				}
				
			}
		}	
		
	}
	public void verPedido(Pedido pedido){
		
		Scanner s = new Scanner(System.in);
		String gustos="";
		GustoPedido gusto;
	
		System.out.println("****************************************************");
		System.out.println("Numero de Pedido: "+pedido.getNumero());
		System.out.println("Nombre: "+pedido.getUsuario().getNombre()+" Apellido: "+pedido.getUsuario().getApellido());
		System.out.println("Dirección: "+pedido.getUsuario().getDireccion());
		Iterator<GustoPedido> it=pedido.getGustosPedido().iterator();
		while(it.hasNext()){
			gusto=it.next();
			gustos+=gusto.getNomnbre()+" Cantidad: "+String.valueOf(gusto.getCantidadPedida());
		}

		System.out.println("****************************************************");		
		String op = s.nextLine();
		//return op;
		
		
	}


	public String mostrarMenu(String usuario) {
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

package edu.cerveapp.vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.business.Controller;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.cerveapp.entities.ePedido;

public class View implements IviewCerveApp {

	private String obtenerTeclaString() {

		String cadena;
		boolean datoOk = false;
		Scanner s = new Scanner(System.in);
		do {
			cadena = s.nextLine();
			
			if (cadena.length() > 0) {
				datoOk = true;
			} else {
				System.out.println("Ingrese un dato valido");
			}
		} while (!datoOk);
	
		return cadena;

	}

	private int obtenerTeclaInt() {
		String cadena;
		int numeroRet;
		boolean datoOk = false;
		Scanner s = new Scanner(System.in);
		do {
			cadena = s.nextLine();
			try {
				numeroRet = Integer.parseInt(cadena);
			} catch (Exception e) {
				numeroRet = -1;
			}

			if (numeroRet >= 0) {
				datoOk = true;
			} else {
				System.out.println("Ingrese un dato valido");
			}

		} while (!datoOk);
		
		return numeroRet;

	}

	@Override
	public Usuario loginUsuario() {
		Usuario u = new Usuario();

		String usuario = null;
		String password = null;
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("Bienvenido al sistema de gestion de pedidos CerveApp");
		System.out.println("****************************************************");

		System.out.println("Ingrese Usuario:");
		usuario = obtenerTeclaString();
		System.out.println("Ingrese Password:");
		password = obtenerTeclaString();

		u.setDni(usuario);
		u.setPaas(password);
		return u;
	}


	public void listarPedidos(List<Pedido> pedidos) {

		String op = "";
		boolean encontre = false;

		while (!op.equals("0")) {

			Iterator<Pedido> it = pedidos.iterator();
			System.out.println("****************************************************");
			System.out.println("LISTADO DE PEDIDOS");

			System.out.println("****************************************************");
			while (it.hasNext()) {
				Pedido p = (Pedido) it.next();
				System.out.println("Pedido N: " + p.getNumero());
			}
			System.out.println("****************************************************");
			System.out.println("****************************************************");
			System.out.println("Para Ver un pedido ingrese el numero, Para salir presione '0' y enter");
			op = String.valueOf(obtenerTeclaInt());
			if (!op.equals("0")) {
				it = pedidos.iterator();
				while (it.hasNext() && !encontre) {
					Pedido p = (Pedido) it.next();
					if (String.valueOf(p.getNumero()).equals(op)) {
						encontre = true;
						verPedido(p);
					}
				}

			}
		}

	}

	public int verPedido(Pedido pedido) {
		int op;
		String gustos = "";
		GustoPedido gusto;

		System.out.println("****************************************************");
		System.out.println("Numero de Pedido: " + pedido.getNumero());
		System.out.println("Nombre: " + pedido.getUsuario().getNombre());
		System.out.println("Apellido: " + pedido.getUsuario().getApellido());
		System.out.println("Dirección: " + pedido.getUsuario().getDireccion());
		Iterator<GustoPedido> it = pedido.getGustosPedido().iterator();
		while (it.hasNext()) {
			gusto = it.next();
			gustos = gusto.getNomnbre() + " Cantidad: " + String.valueOf(gusto.getCantidadPedida());
			System.out.println(gustos);
		}

		System.out.println("1-Volver al listado de pedidos 2-Cambiar de Estado Pedido. -2*****************P***********************************");
		op=obtenerTeclaInt();
		 return op;

	}

	public String mostrarMenu(String usuario) {

		System.out.println("***********Bienvenido " + usuario + "****************");
		System.out.println("********************* MENU DE OPCIONES**************");
		System.out.println("Listar Pedidos Presione: 1");
		System.out.println("Crear Pedido Presione: 2");
		System.out.println("Salir del Sistema escriba: exit");
		System.out.println("****************************************************");
		System.out.println("****************************************************");

		String op = obtenerTeclaString();
		return op;
	}

	private boolean validarNumero(int numero) {
		boolean ok = false;
		if (numero > 0) {
			ok = true;
		}
		return ok;
	}

	@Override
	public Pedido crearPedido(Controller ctx) {

		boolean dnivalido = false;
		List<GustoPedido> gusto = new ArrayList<GustoPedido>();
		Usuario usuario = null;
		Pedido pedido = new Pedido();
		int litros;

		System.out.println("****************ALTA DE PEDIDO************************************");

		do {
			System.out.println("Ingrese el DNI del cliente:");
			String dni = obtenerTeclaString();
			try {
				usuario = ctx.buscarUsuario(dni);
				dnivalido = true;
			} catch (UsuarioInvalidoException e) {
				System.out.println("DNI de usuario invalido presione cualquier tecla para continuar");

			}
		} while (!dnivalido);

		pedido.setUsuario(usuario);
		System.out.println("****************************************************************");
		System.out.println("agregue la cantidad para el gusto en caso de no querer colocar 0");
		System.out.println("****************************************************************");

		Iterator<GustoStock> it = ctx.obtenerGustos().iterator();
		GustoPedido gustoPedido;
		while (it.hasNext()) {
			GustoStock g = it.next();
			System.out.println("****************************************************************");
			System.out.println("Ingresa la cantidad de Litros de la mas rica cerveza " + g.getNomnbre() + ":");
			System.out.println("****************************************************************");

			litros = obtenerTeclaInt();
			gustoPedido = new GustoPedido();
			if (litros > 0) {

				gustoPedido.setNomnbre(g.getNomnbre());
				gustoPedido.setCantidadPedida(litros);

				gusto.add(gustoPedido);

			}

		}
		pedido.setEstadoPedido(ePedido.SOLICITADO);
		pedido.setGustosPedido(gusto);
		System.out.println("****************************************************");
		String op = obtenerTeclaString();
		// return op;

		return pedido;
	}

	@Override
	public void mostrarMsg(String msg) {
		// TODO Auto-generated method stub
		
	}

}

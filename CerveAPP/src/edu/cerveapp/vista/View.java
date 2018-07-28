package edu.cerveapp.vista;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.cerveapp.business.Controller;
import edu.cerveapp.entities.Credenciales;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.cerveapp.entities.ePedido;

public class View implements IviewCerveApp {

	@Override
	public Pedido crearPedido(Controller ctx) throws OperationalCRUDException {

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

		Iterator<GustoStock> iterator = ctx.obtenerGustos().iterator();
		GustoPedido gustoPedido;

		while (iterator.hasNext()) {
			GustoStock gustoStock = iterator.next();
			System.out.println("****************************************************************************");
			System.out.println("Ingresa la cantidad de Litros de la mas rica cerveza " + gustoStock.getNomnbre() + ":");
			System.out.println("****************************************************************************");

			litros = obtenerTeclaInt();
			gustoPedido = new GustoPedido();
			if (litros > 0) {
				gustoPedido.setId_pedido(pedido.getId());
				gustoPedido.setNomnbre(gustoStock.getNomnbre());
				gustoPedido.setCantidadPedida(litros);
				gustoPedido.setId_gusto(gustoStock.getId());
				gustoPedido.setPreciolitro(gustoStock.getPrecio());
				pedido.setMonto(pedido.getMonto()+gustoStock.getPrecio()*litros);

				gusto.add(gustoPedido);

			}

		}
		pedido.setEstadoPedido(ePedido.SOLICITADO);
		pedido.setGustosPedido(gusto);

		System.out.println("*****************VUELVE A MENU***********************************");



		return pedido;
	}

	public void listarPedidos(List<Pedido> pedidos, Controller ctx) throws OperationalCRUDException {

		String opcion = "";

		while (!opcion.equals("exit")) {

			Iterator<Pedido> iterator = pedidos.iterator();
			System.out.println("****************************************************");
			System.out.println("LISTADO DE PEDIDOS");

			System.out.println("****************************************************");
			while (iterator.hasNext()) {
				Pedido pedido = (Pedido) iterator.next();
				System.out.println("Pedido N: " + pedido.getId() + " Estado: " + pedido.getEstadoPedido() + " Usuario: "
						+ pedido.getUsuario().getNombre() + " " + pedido.getUsuario().getApellido());
			}
			System.out.println("****************************************************");
			System.out.println("****************************************************");
			System.out.println(
					"Para Ver un pedido ingrese el numero, Para volver al menu principal escriba la palabra 'exit' y enter");
			opcion = obtenerTeclaString();
			if (!opcion.equals("exit")) {
				iterator = pedidos.iterator();
				boolean encontre = false;
				while (iterator.hasNext() && !encontre) {
					Pedido pedido = (Pedido) iterator.next();
					if (String.valueOf(pedido.getId()).equals(opcion)) {
						encontre = true;
						int retop = verPedido(pedido);
						switch(retop) {
						case 2:
							System.out.println("  ********************CAMBIAR DE ESTADO PEDIDO********************");
							System.out.println("*********************************************************************");
							System.out.println("E-ENTREGADO||F-FACTURADO||S-SOLICITADO||C-CANCELAR MODIDFICACION");
							System.out.println("    ****************************************************");
							System.out.println("     **************************************************");
							opcion = obtenerTeclaString();
							switch (opcion) {
							case "E":
								pedido.setEstadoPedido(ePedido.ENTREGADO);
								ctx.actualilzarPedido(pedido);
								break;
							case "F":
								pedido.setEstadoPedido(ePedido.FACTURADO);
								ctx.actualilzarPedido(pedido);
								break;
							case "S":
								pedido.setEstadoPedido(ePedido.SOLICITADO);
								ctx.actualilzarPedido(pedido);
								break;
							}
							mostrarMsg("El Pedido: " + pedido.getId() + " Fue cambiado al estado "
									+ pedido.getEstadoPedido().toString());


							break;
						case 3:
							
							ctx.gerarRemito(pedido);
							break;
							
							
						}
					
					
					}
				}

			}
		}

	}

	@Override
	public Credenciales loginUsuario() {
		Credenciales credenciales = new Credenciales();

		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("Bienvenido al sistema de gestion de pedidos CerveApp");
		System.out.println("****************************************************");

		System.out.println("Ingrese Usuario:");
		credenciales.setUsuario(obtenerTeclaString());

		System.out.println("Ingrese Password:");
		credenciales.setPassword(obtenerTeclaString());

		return credenciales;
	}

	public String mostrarMenu(String usuario) {

		System.out.println("***********Bienvenido " + usuario + "****************");
		System.out.println("********************* MENU DE OPCIONES**************");
		System.out.println("Listar Pedidos Presione: 1");
		System.out.println("Crear Pedido Presione: 2");
		System.out.println("Actualizar pedidos nuevos desde la WEB: 3");
		System.out.println("Dar de alta un Usuario: 4");
		System.out.println("Listar Usuarios: 5");
		System.out.println("Salir del Sistema escriba: exit");
		System.out.println("****************************************************");
		System.out.println("****************************************************");

		String op = obtenerTeclaString();
		return op;
	}

	@Override
	public void mostrarMsg(String msg) {
		System.out.println("*****************¡¡¡ATENCION!!!!!***********************************");
		System.out.println(msg);
		System.out.println("********************************************************************");

	}

	private int obtenerTeclaInt() {
		String cadena;
		int numeroRet;
		boolean datoOk = false;
		Scanner scanner = new Scanner(System.in);
		do {
			cadena = scanner.nextLine();
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

	private String obtenerTeclaString() {

		String cadena;
		Scanner scanner = new Scanner(System.in);

		cadena = scanner.next();
		while (cadena.isEmpty()) {
			System.out.println("Ingrese un dato valido");
			cadena = scanner.nextLine();

		}

		return cadena;

	}

	private boolean validarNumero(int numero) {
		boolean ok = false;
		if (numero > 0) {
			ok = true;
		}
		return ok;
	}

	
	public Usuario crearUsuario() {
		Usuario usuario = new Usuario();
		System.out.println("****************************************************");
		System.out.println("Ingrese nombre del usuario");
		usuario.setNombre(obtenerTeclaString());
		System.out.println("Ingrese apellido");
		usuario.setApellido(obtenerTeclaString());
		System.out.println("Ingrese Direción");
		usuario.setDireccion(obtenerTeclaString());
		System.out.println("Ingrese DNI");
		usuario.setDni(obtenerTeclaString());
		System.out.println("Ingrese mail");
		usuario.setMail(obtenerTeclaString());
		System.out.println("Ingrese id de sistema externo");
		usuario.setIdExt(obtenerTeclaString());
		System.out.println("Ingrese pass");
		usuario.setPass(obtenerTeclaString());
		System.out.println("Ingrese telefono");
		usuario.setTelefono(obtenerTeclaString());
		System.out.println("****************************************************");
		return usuario;
		

	}
	public int verPedido(Pedido pedido) {
		int op;
		String gustos = "";
		GustoPedido gusto;

		System.out.println("****************************************************");
		System.out.println("Numero de Pedido: " + pedido.getId());
		System.out.println("Estado del pedido: " + pedido.getEstadoPedido());
		System.out.println("Nombre: " + pedido.getUsuario().getNombre());
		System.out.println("Apellido: " + pedido.getUsuario().getApellido());
		System.out.println("Dirección: " + pedido.getUsuario().getDireccion());
		Iterator<GustoPedido> it = pedido.getGustosPedido().iterator();
		double total = 0;
		while (it.hasNext()) {
			gusto = it.next();
			gustos = gusto.getNomnbre() + " Cantidad: " + String.valueOf(gusto.getCantidadPedida()+" subtotal: "+gusto.getCantidadPedida() * gusto.getPreciolitro());
			System.out.println(gustos);
			total = total + (gusto.getCantidadPedida() * gusto.getPreciolitro());

		}
		System.out.println("---------------------------------------------------");
		System.out.println("Importe Total: " + String.valueOf(total));
		System.out.println("---------------------------------------------------");
		System.out.println("****************************************************");
		System.out.println("1-Volver al listado de pedidos 2-Cambiar de Estado Pedido 3-Generar Remito.");
		op = obtenerTeclaInt();
		return op;

	}

	public void listarUsuarios(List<Usuario> list) {
		Iterator<Usuario> itUsuarios=list.iterator();
		while(itUsuarios.hasNext()) {
			Usuario usuario=itUsuarios.next();
			System.out.println("****************************************************");
			System.out.println("ID: "+usuario.getId());
			System.out.println("DNI: "+usuario.getDni());
			System.out.println("Nombre usuario: "+usuario.getNombre());
			System.out.println("Apellido usuario: "+usuario.getApellido());
			System.out.println("Mail usuario: "+usuario.getMail());
			System.out.println("Direccion usuario: "+usuario.getDireccion());
			System.out.println("Telefono usuario: "+usuario.getTelefono());
			System.out.println("****************************************************");
		}
		
	}

}

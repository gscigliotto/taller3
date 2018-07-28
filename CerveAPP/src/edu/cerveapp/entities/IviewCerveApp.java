package edu.cerveapp.entities;

import java.util.List;

import edu.cerveapp.business.Controller;

public interface IviewCerveApp {
	public Pedido crearPedido(Controller ctx) throws OperationalCRUDException;

	public void listarPedidos(List<Pedido> pedidos, Controller ctx) throws OperationalCRUDException;

	public Credenciales loginUsuario() throws UsuarioInvalidoException, OperationalCRUDException;

	public String mostrarMenu(String usuario) throws OperationalCRUDException;

	public void mostrarMsg(String msg);

	public int verPedido(Pedido pedido) throws OperationalCRUDException;
}

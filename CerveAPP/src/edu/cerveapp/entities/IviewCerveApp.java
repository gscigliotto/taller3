package edu.cerveapp.entities;

import java.util.List;

import edu.cerveapp.business.Controller;

public interface IviewCerveApp {
	public void listarPedidos(List<Pedido> pedidos);
	public Usuario loginUsuario() throws UsuarioInvalidoException;
	public String mostrarMenu(String usuario);
	public int verPedido(Pedido pedido);
	public void mostrarMsg(String msg);
	
	public Pedido crearPedido(Controller ctx);
}

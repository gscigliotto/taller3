package edu.cerveapp.entities;

import java.util.List;

import edu.cerveapp.business.Controller;

public interface IviewCerveApp {
	public void listar_pedidos(List<Pedido> pedidos);
	public Usuario loginUsuario() throws UsuarioInvalidoException;
	public String mostrarMenu(String usuario);
	public void verPedido(Pedido pedido);
}

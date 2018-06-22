package edu.cerveapp.entities;

import edu.cerveapp.business.Negocio;

public interface IviewCerveApp {
	public void listar_pedidos(Negocio negocio);
	public Usuario loginUsuario() throws UsuarioInvalidoException;
	public String mostrarMenu(String usuario);
	public void verPedido(Pedido pedido);

}

package edu.cerveapp.vista;

import edu.cerveapp.business.Negocio;
import edu.cerveapp.entities.Pedido;

public interface IviewCerveApp {
	public void listar_pedidos(Negocio negocio);
	public String login_usuario(Negocio negocio);
	public String mostrarMenu(String usuario);
	public void verPedido(Pedido pedido);

}

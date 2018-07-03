package edu.cerveapp.entities;

import java.util.List;

public interface IRepo {
	public List<GustoStock> obtenerGustosStock();
	public List<Pedido> obtenerPedidos();
	public List<Usuario> obtenerUsuarios();
	
	public void actualizarUsuario(Usuario u);
	public Usuario buscarUsuario(String dato);
	public void actualizarPedido(Pedido p);
	public void insertarPedido(Pedido p);
	
	public void actualizarGustoStock(GustoStock g);
	public Usuario getUsuarioByIdExterno(String IdExterno);
	public GustoStock getGustoByNombre(String gustonNombre);
	
	
}

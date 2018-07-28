package edu.cerveapp.entities;

import java.util.List;

public interface IRepo {
	public void actualizarGustoStock(GustoStock g);

	public void actualizarPedido(Pedido p) throws OperationalCRUDException;

	public void actualizarUsuario(Usuario u);

	public Usuario buscarUsuario(String dato) throws OperationalCRUDException;

	public GustoStock getGustoByNombre(String gustonNombre) throws OperationalCRUDException;

	public Usuario getUsuarioByIdExterno(String IdExterno);

	public void insertarPedido(Pedido p) throws OperationalCRUDException;

	//public List<GustoPedido> obtenerGustoPedido(String idRaw) throws OperationalCRUDException;

	public List<GustoStock> obtenerGustosStock() throws OperationalCRUDException;

	public List<Pedido> obtenerPedidos() throws OperationalCRUDException;

	public List<Usuario> obtenerUsuarios() throws OperationalCRUDException;

	List<GustoPedido> obtenerGustoPedido(int id) throws OperationalCRUDException;

	public void insertarUsuario(Usuario usuario);

}

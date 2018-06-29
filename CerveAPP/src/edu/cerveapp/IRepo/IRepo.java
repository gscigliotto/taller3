package edu.cerveapp.IRepo;

import java.util.List;

import edu.cerveapp.entities.GustoStock;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;

public interface IRepo {
	public List<GustoStock> obtenerGustosStock();
	public List<Pedido> obtenerPedidos();
	public List<Usuario> obtenerUsuarios();
	
	public void actualizarUsuario(Usuario u);
	public Usuario buscarUsuario(String dato);
	public void actualizarPedido(Pedido p);
	public void insertarPedido(Pedido p);
	
	public void actualizarGustoStock(GustoStock g);
	
	
}

package edu.cerveapp.business;

import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.entities.Gusto;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.cerveapp.entities.ePedido;

public class Negocio {
	public void CrearPedido(Pedido pedido){

		
	}
	public void CambiarEstadoPedido(Pedido pedido){

		
	}
	public String ValidarUsuario(String usu,String pass) throws UsuarioInvalidoException{
		if(usu==pass) {
			throw new UsuarioInvalidoException();
		}
		return usu;
		
	}
	
	public List<Pedido> ObtenerPedidos(){
		List<Pedido> retPedidos = new ArrayList<Pedido>();
		List<GustoPedido> gustos = new ArrayList<GustoPedido>();
		
		Pedido p;
		GustoPedido g;
		Usuario u = new Usuario();
		u.setApellido("Apellido");
		u.setNombre("Nombre");
		u.setDireccion("Avenida siempre viva 742");;
		for(int i=0;i<4;i++){
			p = new Pedido();
			p.setEstadoPedido(ePedido.SOLICITADO);
			p.setNumero(i);
			p.setUsuario(u);
			
			for(int j=0;j<3;j++){
				
				g=new GustoPedido();
				g.setNomnbre("IPA");
				gustos.add(g);
			}
			p.setGustos(gustos);
			retPedidos.add(p);
			
			
		}
		return retPedidos;
		
	}
}

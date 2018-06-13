package edu.cerveapp.busniess;

import java.util.ArrayList;
import java.util.List;

import entities.Gusto;
import entities.Pedido;
import entities.UsuarioInvalidoException;
import entities.ePedido;

public class Negocio {

	
	public String ValidarUsuario(String usu,String pass) throws UsuarioInvalidoException{
		if(usu==pass) {
			throw new UsuarioInvalidoException();
		}
		return usu;
		
	}
	
	public List<Pedido> ObtenerPedidos(){
		List<Pedido> retPedidos = new ArrayList<Pedido>();
		List<Gusto> gustos = new ArrayList<Gusto>();
		
		Pedido p;
		Gusto g;
		for(int i=0;i<4;i++){
			p = new Pedido();
			p.setEstadoPedido(ePedido.SOLICITADO);
			p.setNumero(i);
			
			for(int j=0;j<3;j++){
				
				g=new Gusto();
				g.setNomnbre("IPA");
				gustos.add(g);
			}
			p.setGustos(gustos);
			retPedidos.add(p);
			
			
		}
		return retPedidos;
		
	}
}

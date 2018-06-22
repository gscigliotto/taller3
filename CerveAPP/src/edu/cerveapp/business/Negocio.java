package edu.cerveapp.business;

import java.util.ArrayList;
import java.util.List;

import edu.cerveapp.dao.repos.IRepo;
import edu.cerveapp.dao.repos.IRepoUsuarios;
import edu.cerveapp.dao.repos.RepoBD.RepoUsuarios;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.IviewCerveApp;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.entities.UsuarioInvalidoException;
import edu.cerveapp.entities.ePedido;

public class Negocio {
	
	private IRepo repo=null;
	private IviewCerveApp view=null;
	public Negocio(IRepo repo, IviewCerveApp view){
		repo.createPedidoRepo();
		this.repo=repo;
		this.view=view;

		
	}
	public void CrearPedido(Pedido pedido){

		
	}
	public void CambiarEstadoPedido(Pedido pedido){

		
	}
	public Usuario login() throws UsuarioInvalidoException{
		Usuario u = this.view.loginUsuario();
		IRepoUsuarios repoUsuario=(IRepoUsuarios) this.repo.createUsuarioRepo();
		Usuario guardado=repoUsuario.getByID("",u.getDni() );
		guardado.validarUsuario(u.getPaas());
		return guardado;
		
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

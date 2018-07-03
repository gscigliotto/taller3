package edu.cerveapp.entities;

import java.util.List;
import java.util.UUID;

public class Pedido {
	private String idRaw;
	public String getIdRaw() {
		return idRaw;
	}
	public void setIdRaw(String idRaw) {
		this.idRaw = idRaw;
	}
	public void generateRaw() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
		this.idRaw = randomUUIDString;
	}
	private int id;
	private Usuario usuario;
	private List<GustoPedido> gustosPedido;
	private double monto;
	
	public Pedido(int id ,double monto, List<GustoPedido> gustospedidos,ePedido estado,Usuario usuario)  {
		setId(id);
		generateRaw();
	
		setMonto(monto);
		setGustos(gustospedidos);
		setEstadoPedido(estado);
		setUsuario(usuario);
	}

	
	public Pedido(int id,String idraw ,double monto, List<GustoPedido> gustospedidos,ePedido estado,Usuario usuario) {
		setId(id);
		setIdRaw(idraw);
	
		setMonto(monto);
		setGustos(gustospedidos);
		setEstadoPedido(estado);
		setUsuario(usuario);
		
	}
	public Pedido(int id, int numero, double monto,ePedido estado) {
		setId(id);
		setNumero(numero);
		setMonto(monto);
		setEstadoPedido(estado);

	}
	public Pedido() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private ePedido estadoPedido;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<GustoPedido> getGustos() {
		return gustosPedido;
	}
	public void setGustos(List<GustoPedido> gustos) {
		this.gustosPedido = gustos;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public ePedido getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(ePedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public List<GustoPedido> getGustosPedido() {
		return gustosPedido;
	}
	public void setGustosPedido(List<GustoPedido> gustosPedido) {
		this.gustosPedido = gustosPedido;
	}

	public void setNumero(int num) {
		//this.numero = GUID.randomUUID().toString();
	}
	


}

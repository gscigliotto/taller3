package edu.cerveapp.entities;

public class Usuario {
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPaas() {
		return pass;
	}
	public void setPaas(String paas) {
		this.pass = paas;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void validarUsuario(String password) throws UsuarioInvalidoException {
		if(!this.getPaas().equals(password)) throw new UsuarioInvalidoException();
	}
	public Usuario(int id, String dni,String nombre,String apellido,String telefono,String mail, String pass) {
		setId(id);
		setApellido(apellido);
		setDni(dni);
		setNombre(nombre);
		setTelefono(telefono);
		setMail(mail);
		setPaas(pass);
		
	}
	public Usuario() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private String mail;
	private String pass;
	private String telefono;
	private String direccion;
	
}

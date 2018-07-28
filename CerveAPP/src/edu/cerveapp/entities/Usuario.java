package edu.cerveapp.entities;

public class Usuario {
	private String apellido;
	private String direccion;
	private String dni;
	private int id;
	private String idExt;
	private String mail;
	private String nombre;
	private String pass;
	private String telefono;

	public Usuario() {

	}

	public Usuario(int id, String dni, String nombre, String apellido, String telefono, String mail, String pass,
			String direccion, String userId) {
		setId(id);
		setApellido(apellido);
		setDni(dni);
		setNombre(nombre);
		setTelefono(telefono);
		setMail(mail);
		setDireccion(direccion);
		setPaas(pass);
		setIdExt(userId);

	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDni() {
		return dni;
	}

	public int getId() {
		return id;
	}

	public String getIdExt() {
		return idExt;
	}

	public String getMail() {
		return mail;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPaas() {
		return pass;
	}

	public String getPass() {
		return pass;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdExt(String idExt) {
		this.idExt = idExt;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPaas(String paas) {
		this.pass = paas;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean validarUsuario(String password) throws UsuarioInvalidoException {
		return (this.getPaas().equals(password));
			
			//throw new UsuarioInvalidoException("Usuario o contraseña incorrectos");
	}
	

}

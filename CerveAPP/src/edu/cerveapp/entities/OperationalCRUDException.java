package edu.cerveapp.entities;

public class OperationalCRUDException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modulo;
	
	public String getModulo() {
		return modulo;
	}
	public OperationalCRUDException(String message) {
		super(message);
	}
	public OperationalCRUDException(String message, String module) {
		super(message);
		this.modulo = module;
	}
	

}

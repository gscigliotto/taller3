package com.gs.archivohandler;

import java.io.BufferedWriter;
import java.util.HashMap;

public class Seccion {
	private String nombre;
	private HashMap<String, String> items;

	Seccion(String str) {
		this.setNombre(str);
		items = new HashMap<String, String>();

	}

	public HashMap<String, String> getItems() {
		return items;
	}

	public void setItems(HashMap<String, String> items) {
		this.items = items;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static boolean esSeccion(String str) {
		boolean ret = false;
		if (str.substring(0, 1).equals("["))
			return true;

		return ret;
	}

	public static boolean esItem(String str) {
		boolean ret = false;
		if (!str.substring(0, 1).equals("[") && !str.substring(0, 1).equals(";"))
			return true;

		return ret;
	}

	public static boolean esComentario(String str) {
		boolean ret = false;
		return ret;
	}

	public void setItem(String str) {
	}

	public void setItem(String str, String str2) {
		items.put(str, str2);
	}

	public void list() {
	}

	public void save(BufferedWriter bw) {
	}

	public String getNombre() {
		return this.nombre;
	}
}

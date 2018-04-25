package edu.gscigliotto.conf.inifiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

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
		return str.substring(0, 1).equals(";");
	}

	public void setItem(String str) {
	}

	public void setItem(String str, String str2) {
		items.put(str, str2);
	}

	public void list() {
	}

	public void save(FileWriter fw) {
		
		try {
			fw.write("["+this.getNombre()+"]"+System.lineSeparator());
			
			Iterator<String> itkeys = this.getItems().keySet().iterator();
			while (itkeys.hasNext()) {
					String key;
					key = (String) itkeys.next();
					fw.write(key + "=" + this.getItems().get(key)+System.lineSeparator());
					
				}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	bw.write("[" + se.getNombre() + "]");
		//	bw.newLine();
		//	Iterator<String> itkeys = se.getItems().keySet().iterator();

		//	while (itkeys.hasNext()) {
		//		String key;
		//		key = (String) itkeys.next();
		//		bw.write(key + "=" + se.getItems().get(key));
		//		bw.newLine();
		//	}
	}

	public String getNombre() {
		return this.nombre;
	}
}

package edu.gscigliotto.conf.inifiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;


import com.gs.archivo.main;

public class IniManager {
	private String ruta;

	private List<Seccion> secciones;

	public List<Seccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<Seccion> secciones) {
		this.secciones = secciones;
	}

	final static Logger log = Logger.getLogger(main.class);

	public String getRuta() {
		return ruta;
	}

	private void setRuta(String ruta) {
		this.ruta = ruta;
	}

	IniManager() {

	}

	public IniManager(String paht) throws FileNotFoundException {

		setRuta(paht);
		File f = new File(this.getRuta());
		this.secciones = new ArrayList<Seccion>();

		if (!f.exists()) {

			throw new FileNotFoundException();

		}

	}

	public boolean load(String paht) {
		boolean ret = false;

		return ret;

	}

	public boolean load() throws IOException, FileNotFoundException {

		boolean ret = false;
		FileReader fr = null;
		try {
			fr = new FileReader(this.getRuta());
			BufferedReader br = new BufferedReader(fr);
			leer(new Scanner(br));

		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			if (fr != null) {
				fr.close();
			}
		}

		return ret;

	}

	private void leer(Scanner sc) {
		String linea;

		sc.useDelimiter("\n");

		while (sc.hasNext()) {
			linea = this.getLinea(sc);

			while (sc.hasNext() && Seccion.esSeccion(linea)) {
				Seccion sec = new Seccion(linea.replace("[", "").replaceAll("]", "").replace("\r", ""));

				secciones.add(sec);
				log.info("seccion: " + linea.replace("[", "").replaceAll("]", ""));

				linea = this.getLinea(sc);

				while (sc.hasNext() && Seccion.esItem(linea)) {

					sec.setItem(linea.split("=")[0], linea.split("=")[1]);
					log.info("propiedad: " + linea.split("=")[0] + " " + linea.split("=")[1]);

					linea = this.getLinea(sc);
					if (!sc.hasNext() && Seccion.esItem(linea)) {
						sec.setItem(linea.split("=")[0], linea.split("=")[1]);
						log.info("propiedad: " + linea.split("=")[0] + " " + linea.split("=")[1]);

					}

				}

			}

			// linea=this.getLinea(sc);
		}

	}

	private String getLinea(Scanner sc) {
		return sc.next();

	}

	public Seccion getSeccion(String nombre) {

		return this.buscarSeccion(nombre);

	}

	private Seccion buscarSeccion(String str) {
		Iterator<Seccion> it = secciones.iterator();
		Seccion sc = null;
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			sc = it.next();
			if (sc.getNombre().equals(str))
				encontre = true;

		}

		return sc;

	}

	public void list() {

	}

	public void setItem(String sec, String clave, String valor) {
		Seccion seccion = this.getSeccion(sec);
		if (seccion == null)
			seccion = new Seccion(sec);
		seccion.setItem(clave, valor);
	}

	public void save() {
		try (FileWriter filew = new FileWriter(this.getRuta())) {
			BufferedWriter bw = new BufferedWriter(filew);
			Iterator<Seccion> it = this.getSecciones().iterator();
			Seccion se = null;
			while (it.hasNext()) {
				se = (Seccion) it.next();
				bw.write("["+se.getNombre()+"]");
				bw.newLine();
				Iterator<String> itkeys = se.getItems().keySet().iterator();

				while (itkeys.hasNext()) {
					String key;
					key=(String) itkeys.next();
					bw.write(key+"=" +se.getItems().get(key));
					bw.newLine();
				}
			}
			bw.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}

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

import edu.gscigliotto.interfaces.TextReader;
import edu.gscigliotto.interfaces.TextWriter;

public class IniManager implements TextReader, TextWriter {
	private String ruta;
	FileReader fr = null;
	Scanner sc = null;
	BufferedReader br;
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
		fr = new FileReader(this.getRuta());
		br = new BufferedReader(fr);

		sc = new Scanner(br);

		this.secciones = new ArrayList<Seccion>();

		if (!f.exists()) {

			throw new FileNotFoundException();

		}

	}

	public boolean load(String paht) {
		boolean ret = false;

		return ret;

	}

	public boolean isReady() {
		boolean ret = false;
		if (fr != null) {
			ret = true;
		}
		return ret;
	}

	public String readLine() {
		String linea = "";

		if (!this.isReady()) {
			try {

				linea = this.getLinea(sc);

			} catch (Exception e) {
				log.error(e.getMessage() + " sefue");
			}

		} else {
			linea = this.getLinea(sc);

		}
		return linea;
	}

	public boolean load() throws IOException, FileNotFoundException {

		boolean ret = false;

		try {

			leer(new Scanner(br));

		} finally {
			if (fr != null) {
				fr.close();
			}
		}

		return ret;

	}

	private void leer(Scanner sc) {
		String linea = this.readLine();
		while (linea != "" && Seccion.esSeccion(linea)) {
			Seccion sec = new Seccion(linea.replace("[", "").replaceAll("]", "").replace("\r", ""));
			secciones.add(sec);

			linea = this.readLine();
			while (linea != "" && Seccion.esItem(linea)) {
				sec.setItem(linea.split("=")[0], linea.split("=")[1]);
				linea = this.readLine();




			}

		}

	}

	private String getLinea(Scanner sc) {
		String ret = "";
		if (sc.hasNext())
			ret = sc.nextLine();
		return ret;

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
		
		if(!encontre) throw new RuntimeException();

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
			//BufferedWriter bw = new BufferedWriter(filew);
			Iterator<Seccion> it = this.getSecciones().iterator();
			Seccion se = null;
			while (it.hasNext()) {
				se = (Seccion) it.next();
				se.save(filew);
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
			//bw.close();


		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public String writeLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
}

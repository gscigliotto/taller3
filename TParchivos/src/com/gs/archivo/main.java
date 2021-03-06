package com.gs.archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.*;

import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.Seccion;
import tp1.utils.textfiles.BufferedTextReader;
import tp1.utils.textfiles.BufferedTextWriter;

public class main {

	static String dir_rel = "\\configuraciones\\setup.ini";

	final static Logger log = Logger.getLogger(main.class);

	public static void main(String[] args) throws Exception {

		Path Ruta = Paths.get(""); // obtengo la ruta desde donde esta ejecutandose la aplicacion.

		// Configuración de logn4j
		BasicConfigurator.configure();

		/*
		 * EJERCICIO N1
		 */

		BufferedTextReader br = null;
		try {
			br = new BufferedTextReader(Ruta.toAbsolutePath().toString() + "\\src\\com\\gs\\archivo\\main.java");

		} catch (Exception e) {

		}

		BufferedTextWriter bw = null;

		try {
			bw = new BufferedTextWriter(Ruta.toAbsolutePath().toString() + "\\main.txt");
		} catch (Exception e) {

		}

		log.error(Ruta.toAbsolutePath().toString());
		String txt = "";
		if (br.isReady() && bw.isReady()) {
			while (txt != null) {
				txt = br.readLine();
				if (txt != null)
					bw.writeLine(txt);
				log.error(txt);
			}

		}

		br.close();
		bw.close();

		/*
		 * FIN EJERCICIO 1
		 */

		// log.setLevel(args[0]);

		dir_rel = Ruta.toAbsolutePath().toString() + "" + dir_rel; // concateno directorio
		IniManager im = new IniManager(dir_rel);
		log.info("INICIA EL PROGRAMA " + dir_rel);

		try {

			im.load();
			Seccion seccion = im.getSeccion("Startup");
			log.info(seccion.getValorClave("AppName"));

			seccion.setItem("clave nuevooo2", "valornuevo");
			im.save();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testEscribir() throws IOException {

		String msg = "Hola InMundo";
		BufferedWriter writer = new BufferedWriter(new FileWriter(dir_rel, true));

		PrintWriter pw = new PrintWriter(writer);

		pw.println(msg);

		pw.close();

		writer.close();
	}

	public static void LeerArchivoScanner() {
		FileReader fr;
		/*
		 * Scanner sc = new Scanner()
		 */
	}

	public static void LeerArchivoBr() {
		String linea;
		List<String> lineas = new ArrayList<>();

		FileReader fr = null;
		try {
			fr = new FileReader(dir_rel);
			BufferedReader br = new BufferedReader(fr);
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);

			}
			for (String lin : lineas) {
				System.out.println(lin);
			}

		} catch (Exception e) {
			throw new RuntimeException("Fallo al leer archivo: " + e.getMessage());

		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception e) {
					throw new RuntimeException("Fallo al cerrar el archvio: " + e.getMessage());
				}

			}

		}

	}

}

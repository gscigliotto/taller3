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

public class main {

	static String dir_rel = "\\configuraciones\\setup.ini";

	final static Logger log = Logger.getLogger(main.class);

	public static void main(String[] args) throws Exception {
		// Configuración de logn4j
		BasicConfigurator.configure();
		
		// log.setLevel(args[0]);
		Path Ruta = Paths.get(""); // obtengo la ruta desde donde esta ejecutandose la aplicacion.

		dir_rel = Ruta.toAbsolutePath().toString() + "" + dir_rel; // concateno directorio
		IniManager im = new IniManager(dir_rel);
		log.info("INICIA EL PROGRAMA "+dir_rel);

		try {

			im.load();
			Seccion s= im.getSeccion("Startup");
			log.info(s.getItems().get("AppName"));

			s.setItem("clave nuevo", "valornuevo");
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

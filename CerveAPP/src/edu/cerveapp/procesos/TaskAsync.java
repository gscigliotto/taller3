package edu.cerveapp.procesos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import edu.cerveapp.Repo.RepoBD.RepoBD;
import edu.cerveapp.Repo.RepoList.RepoList;

import edu.cerveapp.business.PedidosWeb;
import edu.cerveapp.entities.IRepo;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import edu.gscigliotto.conf.inifiles.Seccion;

public class TaskAsync {
	private static final String APP_DIR = Paths.get("").toAbsolutePath().toString();
	private static final String CONF_DIRECTORY = "\\conf\\";
	private static final String CONF_FILE = "setup.ini";

	public static IniManager getIniManager() throws InvalidConfigurationException {

		// obtengo la ruta desde donde esta ejecutandose la aplicacion.
		String dir_rel = APP_DIR + CONF_DIRECTORY + CONF_FILE;
		IniManager iniManager;
		try {
			iniManager = new IniManager(dir_rel);
			iniManager.load();
		} catch (FileNotFoundException e) {
			throw new InvalidConfigurationException("Problema de acceso con el directorio: " + dir_rel);
		} catch (IOException e) {
			throw new InvalidConfigurationException("Problema de lectura o sctritura sobre el archivo: " + dir_rel);
		}

		return iniManager;

	}

	public static void main(String[] args) {
		IRepo db = null;
		try {
			IniManager iniMngr = getIniManager();

			Seccion seccion = iniMngr.getSeccion("ENTORNO");

			switch (seccion.getItems().get("persistence")) {
			case "list":
				db = new RepoList();
				break;

			case "bd":

				db = new RepoBD(APP_DIR + CONF_DIRECTORY + seccion.getItems().get("dbconfig_file"),
						Boolean.valueOf(seccion.getItems().get("recreate_all").equals("true")));

				break;
			}

			PedidosWeb pedidoWeb = new PedidosWeb(iniMngr.getSeccion("ENTORNO").getItems().get("url_pedidos"), db);
			pedidoWeb.procesar();

		} catch (InvalidConfigurationException e) {

		} catch (NotFoundSeccionExeption e) {

		} catch (OperationalCRUDException e) {

		} catch (SQLException e) {
	
		}
	}

}

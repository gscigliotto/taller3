package edu.cerveapp.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import edu.cerveapp.repoDB.RepoBD;
import edu.cerveapp.repoList.RepoList;
import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import edu.gscigliotto.conf.inifiles.Seccion;

public class Configuracion {

	private static Configuracion conf;
	private IRepo repo = null;
	private IniManager inimanager;
	private static final String APP_DIR = Paths.get("").toAbsolutePath().toString();
	private static final String CONF_DIRECTORY = "\\conf\\";
	private static final String CONF_FILE = "setup.ini";
	private Seccion seccion;
	private String keystorFullPath;
	private String keyStorePass;
	private String keyStoreAlias;
	private File folder_cert;
	private File cert;
	
	
	
	public static Configuracion getInstance() throws InvalidConfigurationException {
		if(conf==null) {
			conf= new Configuracion();
		}
		return conf;
	}
	public String getConfiguracion(String clave) {
		
		return seccion.getValorClave(clave);
	}
	private Configuracion() throws InvalidConfigurationException {
		// obtengo la ruta desde donde esta ejecutandose la aplicacion.
		String dir_rel = APP_DIR + CONF_DIRECTORY + CONF_FILE;
		IniManager iniManager;
		

		try {
			iniManager = new IniManager(dir_rel);
			iniManager.load();
			setInimanager(iniManager);
			seccion = iniManager.getSeccion("ENTORNO");
			//Configuración del keystore
			keystorFullPath = seccion.getValorClave("keystore_path") + seccion.getValorClave("keystore_name");
			keyStorePass = seccion.getValorClave("cert_pass");
			keyStoreAlias = seccion.getValorClave("cert_alias");
			folder_cert = new File(seccion.getValorClave("keystore_path"));
			cert = new File(keystorFullPath);

			
			
			creaBD(seccion.getValorClave("persistence"),seccion.getValorClave("recreate_all"));
			creaDirectoriosSoporte();
			creaAlmacenClaves();
		} catch (FileNotFoundException e) {
			throw new InvalidConfigurationException("Problema de acceso con el directorio: " + e.getMessage() + dir_rel);
		} catch (IOException e) {
			throw new InvalidConfigurationException("Problema de lectura o sctritura: " + e.getMessage());
		} catch (NotFoundSeccionExeption e) {
			throw new InvalidConfigurationException("Seccion no encontrada en archivo de configuracion: " + e.getMessage());
		} catch (SQLException e) {
			throw new InvalidConfigurationException("Error de BD: " + e.getMessage());
		} catch (InterruptedException e) {
			throw new InvalidConfigurationException("Error generando el certificado de aplicacion: "+e.getMessage());
		}

	}
	

	private void creaAlmacenClaves() throws IOException, InterruptedException {

		String comando = seccion.getValorClave("certifacate_command");


		String comandoexec = String.format(comando, keystorFullPath, keyStorePass, keyStoreAlias, "=", "=", "=", "=",
				"=", "=", keystorFullPath);
		if (!folder_cert.exists()) {
			folder_cert.mkdirs();
			ejecutarComando(comandoexec);
			Runtime.getRuntime().exec(String.format(comando, keystorFullPath, keyStorePass, keyStoreAlias, "=", "=",
					"=", "=", "=", "=", keystorFullPath));
		} else {
			if (!cert.exists()) {
				ejecutarComando(comandoexec);

			}
		}
	}



	private void ejecutarComando(String comandoexec) throws IOException, InterruptedException{

		Process p = Runtime.getRuntime().exec(comandoexec);
		p.waitFor();
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = input.readLine()) != null)
			System.out.println(line);

	}

	private void creaDirectoriosSoporte() {

		File folder_remitos = new File(seccion.getValorClave("remitos_path"));
		if (!folder_remitos.exists()) {
			folder_remitos.mkdirs();
		}

	}

	private void creaBD(String persistencia, String strRecrea)
			throws NotFoundSeccionExeption, InvalidConfigurationException, SQLException {

		switch (persistencia) {
		case "list":
			setRepo(new RepoList());
			break;

		case "bd":
			setRepo(new RepoBD(APP_DIR + CONF_DIRECTORY + seccion.getValorClave("dbconfig_file"),Boolean.valueOf(strRecrea)));
			break;

		default:
			throw new InvalidConfigurationException("Debe definir el tipo de persistencia.");

		}

	}
	
	public IRepo getRepo() {
		return repo;
	}

	private void setRepo(IRepo repo) {
		this.repo = repo;
	}

	public IniManager getInimanager() {
		return inimanager;
	}

	private void setInimanager(IniManager inimanager) {
		this.inimanager = inimanager;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public String getKeyStorePass() {
		return keyStorePass;
	}

	public String getKeyStoreAlias() {
		return keyStoreAlias;
	}

	public File getFolder_cert() {
		return folder_cert;
	}


	public String getKeystorFullPath() {
		return keystorFullPath;
	}

	public void setKeystorFullPath(String keystorFullPath) {
		this.keystorFullPath = keystorFullPath;
	}
}

package tc3.tp3.utils.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.Seccion;
import tc3.tp3.utils.ini.IniPersistent;

public class DBConfig {
	static final String ROOT_SECTION="DBCONFIG";
	static final String ROOT_SECTION_ITEM="DBEnvironment";
	static final String LOCAL_ENVIRONMENT="LOCAL";
	static final String ERR_DEFAULT_ENVIRONMENT="Error: ambiente inexistente";
	private String defaultEnvironment;
	private IniManager im;

	public void setDefaultEnvironment(String defaultEnvironment) {
		this.defaultEnvironment = defaultEnvironment;
	}
	public DBConfig() {
	
	}
	public DBConfig(String folder, String filename) {
		
		try {
			im = new IniManager(folder+"\\"+filename);
			
		} catch (FileNotFoundException e) {

		}
		try {
			im.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Seccion s= im.getSeccion(this.ROOT_SECTION);
		
	}
	
	public void setDefaultEnvieronment(String environmentName)throws RuntimeException {
		
	}
	public void setValueOf(String environmentName, String attribute, String value)
	{
		
	}
	public String getDefaultEnvironment() {
		return defaultEnvironment;
	}
	
	public ArrayList<String> getAllEnvironmentNames()
	{
		return null;
		
	}
	public String getURL()
	{
		return null;
		
	}
	public String getURL(String environmentName)throws RuntimeException
	{
		return null;
		
	}
	public boolean hasEnvironment(String environmentName) {
		return false;
		
	}
	public void createEnvironment(String name, String driver, String server, String port,
			String database, String username, String password) {
	
	}
	public void save()
	{
		
	}
	private void updateIniSection(IniPersistent dbenv)
	{
		
	}
	
	private void createDefaultEnvironment() {
		
	}

}

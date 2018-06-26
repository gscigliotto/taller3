package tc3.tp3.utils.db;

import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.Seccion;
import tc3.tp3.utils.ini.IniPersistent;

public class IniPersDBEnvironment extends DBEnvironment implements IniPersistent {





	public IniPersDBEnvironment(String environmentName)
	{
		super(environmentName);
		
	}
	
	public IniPersDBEnvironment(String environmentName, IniManager im)
	{
		super(environmentName);
		this.loadFromIni(im, environmentName);
	}

	public IniPersDBEnvironment(String nombre, String driver, String server, String port,
			String base, String usrname, String pwd)
	{
		super(nombre);
    	this.setValues(driver, server, port, base, usrname, pwd);
	}
	
	@Override
	public void updateIni(IniManager iniManager) {
	
		Seccion s= iniManager.getSeccion(this.getEnvironmentName());
		s.setItem(K_DRIVER, this.getDriver());
		s.setItem(K_SERVER, this.getServer());
		s.setItem(K_PORT, this.getPort());
		s.setItem(K_DATABASE, this.getDatabase());
		s.setItem(K_USERNAME, this.getUsername());
		s.setItem(K_PASSWORD, this.getPassword());
		iniManager.save();
		
		
	}
	@Override
	public void loadFromIni(IniManager iniManager, String sectionName) {
		Seccion s =iniManager.getSeccion(sectionName);
		this.setDriver(s.getItems().get(K_DRIVER));
		this.setServer(s.getItems().get(K_SERVER));
		this.setPort(s.getItems().get(K_PORT));
		this.setDatabase(s.getItems().get(K_DATABASE));
		this.setUsername(s.getItems().get(K_USERNAME));
		this.setPassword(s.getItems().get(K_PASSWORD));
		
		
	}

}

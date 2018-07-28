package tc3.tp3.utils.db;

import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;
import edu.gscigliotto.conf.inifiles.Seccion;
import tc3.tp3.utils.ini.IniPersistent;

public class IniPersDBEnvironment extends DBEnvironment implements IniPersistent {





	public IniPersDBEnvironment(String environmentName)
	{
		super(environmentName);
		
	}
	
	public IniPersDBEnvironment(String environmentName, IniManager im) throws NotFoundSeccionExeption
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
	public void updateIni(IniManager iniManager) throws NotFoundSeccionExeption {
	
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
	public void loadFromIni(IniManager iniManager, String sectionName) throws NotFoundSeccionExeption {
		Seccion seccion =iniManager.getSeccion(sectionName);
		this.setDriver(seccion.getValorClave(K_DRIVER));
		this.setServer(seccion.getValorClave(K_SERVER));
		this.setPort(seccion.getValorClave(K_PORT));
		this.setDatabase(seccion.getValorClave(K_DATABASE));
		this.setUsername(seccion.getValorClave(K_USERNAME));
		this.setPassword(seccion.getValorClave(K_PASSWORD));
		
		
	}

}

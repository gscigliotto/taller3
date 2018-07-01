package tc3.tp3.utils.db;

import java.nio.file.Paths;

import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBEnvironment db = new DBEnvironment("DESA","sqlserver","A-SRV-BDINST","1433","BD21A04","BD21A04","BD21A04");
		System.out.println(db.getURL());
		DBConfig c = null;
		try {
			c = new DBConfig(Paths.get("").toAbsolutePath().toString()+"\\configuraciones","DBConfig.ini");
		} catch (NotFoundSeccionExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.getURL());
	} 

}

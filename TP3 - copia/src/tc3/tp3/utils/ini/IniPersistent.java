package tc3.tp3.utils.ini;

import edu.gscigliotto.conf.inifiles.IniManager;
import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;

public interface IniPersistent {
	void updateIni(IniManager iniManager) throws NotFoundSeccionExeption;
	void loadFromIni(IniManager iniManager, String sectionName) throws NotFoundSeccionExeption;


}

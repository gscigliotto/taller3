package tc3.tp3.utils.ini;

import edu.gscigliotto.conf.inifiles.IniManager;

public interface IniPersistent {
	void updateIni(IniManager iniManager);
	void loadFromIni(IniManager iniManager, String sectionName);


}

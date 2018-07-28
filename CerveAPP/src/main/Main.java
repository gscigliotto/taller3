package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.lowagie.text.DocumentException;

import edu.cerveapp.business.Controller;
import edu.cerveapp.entities.Configuracion;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.OperationalCRUDException;
import edu.cerveapp.entities.Pedido;
import edu.cerveapp.entities.Usuario;
import edu.cerveapp.utils.GeneratePDFFileIText;

import edu.gscigliotto.conf.inifiles.NotFoundSeccionExeption;


public class Main {




	public static void main(String[] args) {

			Configuracion configuracion = null;
			try {
				configuracion = Configuracion.getInstance();
				//View vista = new View();
				Controller negocio = new Controller(configuracion);

				negocio.startApp();
			} catch (IllegalArgumentException | OperationalCRUDException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




	}

}

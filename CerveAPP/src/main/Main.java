package main;


import edu.cerveapp.business.Controller;
import edu.cerveapp.entities.Configuracion;
import edu.cerveapp.entities.InvalidConfigurationException;



public class Main {




	public static void main(String[] args) {

			Configuracion configuracion = null;
				try {
					configuracion = Configuracion.getInstance();

					Controller negocio = new Controller(configuracion);

					negocio.startApp();
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					System.out.println("Problema de configuracion la aplicacion no se puede iniciar."+e.getMessage());
				}





	}

}

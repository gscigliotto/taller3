package com.gs.archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.gs.archivohandler.IniManager;
import org.apache.log4j.*;






public class Main {
	
	
	static String dir_rel="\\configuraciones\\setup.ini";
	
	final static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
	//Configuracion de logn4j
	BasicConfigurator.configure();

	log.info("INICIA EL PROGRAMA");

	try
	{
		
		Path Ruta = Paths.get(""); //obtengo la ruta desde donde esta ejecutandose la aplicacion.
		
		dir_rel=Ruta.toAbsolutePath().toString()+""+dir_rel;		//concateno la ruta de la app a el directorio
		IniManager im = new IniManager(dir_rel);
		
		im.Load();
		log.info(im.getSeccion("Startup").getItems().get("AppName"));
		
	}catch(FileNotFoundException e){
		System.out.println(e.getMessage());
	}
				
	}
	
	
	

	
	public static void testEscribir()throws IOException{

		String msg ="Hola InMundo";
		BufferedWriter writer = new BufferedWriter(new FileWriter(dir_rel,true));

		PrintWriter pw = new PrintWriter(writer);
		
		pw.println(msg);

		pw.close();

		writer.close();
	}


	
	
	public static void LeerArchivoScanner(){
		FileReader fr;
		/*
		Scanner sc = new Scanner()
		*/
	}
	
	public static void LeerArchivoBr(){
		String linea;
		List<String> lineas = new ArrayList<>();

		FileReader fr=null;
		try{
			fr= new FileReader(dir_rel);
			BufferedReader br = new BufferedReader(fr);
			while((linea=br.readLine())!=null){
				lineas.add(linea);
				
			}
			for(String lin:lineas){
				System.out.println(lin);
			}
			
		}catch(Exception e)
		{
			throw new RuntimeException("Fallo al leer archivo: "+e.getMessage());
			
		}finally
		{
			if(fr!=null){
				try{
					fr.close();
				}catch(Exception e){
					throw new RuntimeException("Fallo al cerrar el archvio: "+e.getMessage());
				}
				
			}
			
		}
		
		
	}
	

}

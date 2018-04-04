package com.gs.archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gs.archivohandler.IniManager;

public class main {
	
	
	
	
	
	static String ruta="C:\\trabajo\\file.ini";
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		try
		{
			IniManager im = new IniManager(ruta);
			im.Load();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		/*
		 * try{
		
			testEscribir();
		}catch(IOException e){
			
		}
		*/
		
		
		
		
		
	}
	
	
	
	
	
	public static void testEscribir()throws IOException{

		String msg ="Hola InMundo";
		BufferedWriter writer = new BufferedWriter(new FileWriter(ruta,true));

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
			fr= new FileReader(ruta);
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

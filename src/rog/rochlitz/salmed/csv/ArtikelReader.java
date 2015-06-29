package rog.rochlitz.salmed.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rog.rochlitz.salmed.objects.Artikel;


public class ArtikelReader {

	   private List<Artikel> artikels;
		private   final String COMMA_DELIMITER = ",";
		private   final int SPALTE_1 = 0;
		private   final int SPALTE_2 = 1;
		private   final int SPALTE_3 = 2;
		private   final int SPALTE_4 = 3;

		  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		public void readCsvFile(String fileName) {
			BufferedReader fileReader = null;
	        try {

	        	artikels = new ArrayList<Artikel>();
	            String line = "";
	            fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
	                String[] tokens = line.split(COMMA_DELIMITER);
	                if (tokens.length > 0) {
	                	if(tokens.length<4){ 
	                		Artikel artikel = new Artikel( Integer.parseInt( tokens[SPALTE_1].trim()), tokens[SPALTE_2],   tokens[SPALTE_3]  );
		                	artikels.add(artikel);
	                	}else{
	                		Artikel artikel = new Artikel( Integer.parseInt( tokens[SPALTE_1].trim() ), tokens[SPALTE_2],  tokens[SPALTE_3],  tokens[SPALTE_4] );
	                		artikels.add(artikel);
	                	}
					}
	            }
	        } 
	        catch (Exception e) {
	        	System.out.println("Fehler beim einlesen von " + fileName);
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Fehler beim einlesen von " + fileName);
	                e.printStackTrace();
	            }
	        }

		}
		
		public List<Artikel> getArtikels() {
			return artikels;
		}

		public void setArtikels(List<Artikel> artikele) {
			this.artikels = artikele;
		}

		public static void main(String[] args){
			ArtikelReader l = new ArtikelReader();
			l.readCsvFile(args[0] );
			
		}
		
		public void print(){
			  
            for (Artikel artikel : artikels) {
				System.out.println(artikel.toString());
			}
		}
	
	
}

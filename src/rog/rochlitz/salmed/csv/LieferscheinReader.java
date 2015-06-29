package rog.rochlitz.salmed.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rog.rochlitz.salmed.objects.Lieferschein;


public class LieferscheinReader {

	   private List<Lieferschein> lieferscheine;
		private   final String COMMA_DELIMITER = ",";
		private   final int SPALTE_1 = 0;
		private   final int SPALTE_2 = 1;
		private   final int SPALTE_3 = 2;

		  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		public void readCsvFile(String fileName) {
			BufferedReader fileReader = null;
	        try {

	        	lieferscheine = new ArrayList<Lieferschein>();
	            String line = "";
	            fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
	                String[] tokens = line.split(COMMA_DELIMITER);
	                if (tokens.length > 0) {
	                	Lieferschein lieferschein = new Lieferschein(  tokens[SPALTE_1], Integer.parseInt( tokens[SPALTE_2].trim() ), sdf.parse(tokens[SPALTE_3]) );
	                	lieferscheine.add(lieferschein);
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
		
		public List<Lieferschein> getLieferscheine() {
			return lieferscheine;
		}

		public void setLieferscheine(List<Lieferschein> lieferscheine) {
			this.lieferscheine = lieferscheine;
		}

		public static void main(String[] args){
			LieferscheinReader l = new LieferscheinReader();
			l.readCsvFile(args[0] );
			
		}
		
		public void print(){
			  
            for (Lieferschein lieferschein : lieferscheine) {
				System.out.println(lieferschein.toString());
			}
		}
	
	
}

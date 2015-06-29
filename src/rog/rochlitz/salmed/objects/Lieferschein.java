package rog.rochlitz.salmed.objects;

import java.util.Date;

public class Lieferschein {
	
	String versichertennummer;
	int artikelID;
	Date datum;
	
	public Lieferschein(String versichertennummer, int artikelID, Date datum) {
		super();
		this.versichertennummer = versichertennummer.trim();
		this.artikelID = artikelID;
		this.datum = datum;
	}
	
	
	
	public String getVersichertennummer() {
		return versichertennummer;
	}
	public void setVersichertennummer(String versichertennummer) {
		this.versichertennummer = versichertennummer;
	}
	public int getArtikelID() {
		return artikelID;
	}
	public void setArtikelID(int artikelID) {
		this.artikelID = artikelID;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	
	
	public String toString(){
		return versichertennummer + " : " + artikelID + " : " + datum.toString();
		
	}
	

}

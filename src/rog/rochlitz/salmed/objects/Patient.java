package rog.rochlitz.salmed.objects;

public class Patient {
	
	public Patient(String versichertennummer, String vorname, String nachname,String str) {
		super();
		this.versichertennummer = versichertennummer.trim();
		this.vorname = vorname.trim();
		this.nachname = nachname.trim();
		this.strasse = str.trim();
	}
	

	String versichertennummer;
	String vorname;
	String nachname;
	String strasse;
	
	
	public String getVersichertennummer() {
		return versichertennummer;
	}
	public void setVersichertennummer(String versichertennummer) {
		this.versichertennummer = versichertennummer;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String toString(){
		return versichertennummer + " : " + vorname + " : " + nachname;
		
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	

}

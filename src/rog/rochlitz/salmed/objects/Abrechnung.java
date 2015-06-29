package rog.rochlitz.salmed.objects;

import java.util.Date;

public class Abrechnung {
	

 
	public Abrechnung(String versichertennummer, String artikelname,
			Date datum, String vk, String patientname, String patientstr) {
		super();
		this.versichertennummer = versichertennummer;
		this.artikelname = artikelname;
		this.datum = datum;
		this.vk = vk;
		this.patientname = patientname;
		this.patientstr = patientstr;
	}
	
	String versichertennummer;
	String artikelname;
	Date datum;
	String vk;//verkaufspreis
	String patientname; 
	String patientstr; 
	
	public String getVersichertennummer() {
		return versichertennummer;
	}
	public void setVersichertennummer(String versichertennummer) {
		this.versichertennummer = versichertennummer;
	}
	public String getArtikel() {
		return artikelname;
	}
	public void setArtikel(String artikel) {
		this.artikelname = artikel;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public String getArtikelname() {
		return artikelname;
	}
	public void setArtikelname(String artikelname) {
		this.artikelname = artikelname;
	}
	public String getVk() {
		return vk;
	}
	public void setVk(String vk) {
		this.vk = vk;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String toString(){
		return datum.toString() + " : " + versichertennummer + " : " + artikelname + " : " + vk + " : " + patientname;
		
	}
	public String getPatientstr() {
		return patientstr;
	}
	public void setPatientstr(String patientstr) {
		this.patientstr = patientstr;
	}

}

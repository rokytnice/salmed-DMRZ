package rog.rochlitz.salmed.dmrz;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rog.rochlitz.salmed.csv.ArtikelReader;
import rog.rochlitz.salmed.csv.LieferscheinReader;
import rog.rochlitz.salmed.csv.PatientenReader;
import rog.rochlitz.salmed.objects.Abrechnung;
import rog.rochlitz.salmed.objects.Artikel;
import rog.rochlitz.salmed.objects.Lieferschein;
import rog.rochlitz.salmed.objects.Patient;

public class AbrechnungGenerator {
	// TODO readers static

	public static void main(String[] args) {

		LieferscheinReader l = new LieferscheinReader();
		l.readCsvFile(args[0]);

		PatientenReader p = new PatientenReader();
		p.readCsvFile(args[1]);

		ArtikelReader a = new ArtikelReader();
		a.readCsvFile(args[2]);

		Iterator<Lieferschein> liter = l.getLieferscheine().iterator();
		List<Abrechnung> abrechnungen = new ArrayList<Abrechnung>();

		while (liter.hasNext()) {

			Lieferschein liefer = liter.next();
			Patient pat = getPatent(p.getPatienten(),
					liefer.getVersichertennummer());
			if (pat == null) {
				System.out.println("Patien mit versichertenummer : "
						+ liefer.getVersichertennummer() + " in Datei "
						+ args[1] + " nicht gefunden. ");
				continue;
			}

			//TODO mehrer lieferscheine pro patient kann vorkommen > mergen
			// TODO mehrere boxen können gleiche artikel enthalten > mergen eine position
			List<Artikel> artikelList = getArtikel(a.getArtikels(),
					liefer.getArtikelID());

			if (artikelList == null) {
				System.out.println("Artikel mit id : " + liefer.getArtikelID()
						+ " in Datei " + args[2] + " nicht gefunden. ");
				continue;
			}
			Iterator<Artikel> artIter = artikelList.iterator();
			while (artIter.hasNext()) {
				Artikel artikelAbzurechnen = artIter.next();
				Abrechnung abr = new Abrechnung(liefer.getVersichertennummer(),
						artikelAbzurechnen.getArtikelname(),
						liefer.getDatum(),
						artikelAbzurechnen.getVk(),
						pat.getNachname(),
						pat.getStrasse()
						);
				abrechnungen.add(abr);
			}
		}

		print(abrechnungen);

	}

	private static Patient getPatent(List<Patient> patienten,
			String versichertennummer) {

		Iterator<Patient> iter = patienten.iterator();
		List<Abrechnung> abrechnungen = new ArrayList<Abrechnung>();

		while (iter.hasNext()) {
			Patient pat = iter.next();
			if (0 <= pat.getVersichertennummer().compareToIgnoreCase(
					versichertennummer)) {
				return pat;
			}
		}
		return null;
	}

	private static List<Artikel> getArtikel(List<Artikel> artikelStamm,
			int artikelID) {

		Iterator<Artikel> iter = artikelStamm.iterator();
		List<Artikel> resultArtikel = new ArrayList<Artikel>();

		while (iter.hasNext()) {
			Artikel art = iter.next();
			if (art.getArtikelId() ==  artikelID) { // finde artikel
				if (art.getSubartikel() == null) {// keine Box
					resultArtikel.add(art);
					return resultArtikel;

				} else {// box > hole alle artikel
					for (int subArtID : art.getSubartikel()) {
						List<Artikel> artikelRekRes = getArtikel(artikelStamm,
								subArtID);
						resultArtikel.addAll(artikelRekRes);
					}
				}
			}
		}

		return resultArtikel;
	}
	
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "Belegnummer;Belegdatum;F_lligkeit;Verordnungs Datum;Genehigungs Datum;Genehmigt durch;Genehmigungs Kennzeichen;Versicherter ID;Versicherter Vorname;Versicherter Nachname;Versicherter Strasse;Versicherter PLZ;Versicherter Ort;Versicherter Geb. Datum;Versicherten Nummer;Versicherungs Status;Kassen IK;Vertragsarzt Nummer;Verordner Vorname;Verordner Name;Verordner Strasse;Verordner PLZ;Verordner Ort;Rechnungs Positions ID;Liefertermin;Versorgung Ab;Versorgung Bis;Hilfsmittel Kennzeichen;Inventar Nummer;Menge;Einzelpreis;MwSt. %;Zuzahlungs Ident.-Nr.;Zuzahlungsbetrag;Betrag Eigenanteil;Artikel ID;Abrg. Pos. Nr. ;Artikel Beschreibung;GMG Kategorie;Artikel Kurzbeschreibung;Verordner Betriebsst_ttennummer;Verordnung Unfallkennzeichen;Abholort Strasse/Notfallort;Abholort PLZ;Abholort Name;Zielort Strasse;Zielort PLZ;Zielort Name;Freifeld;Freifeld;Indikations- SchlÙssel;Verordnungsart;Gefahrene km;RezeptgebÙhr;Tarifkennzeichen;Sammelrechungs nummer;Abrechungscode;DiagnoseschlÙssel 1;Diagnosetext 1;DiagnoseschlÙssel 2;Diagnosetext 2;DiagnoseschlÙssel 3;Diagnosetext 3;DiagnoseschlÙssel 4 ;Diagnosetext 4;SchlÙssel Diagnose 5 ;Diagnosetext 5;GenehmigungsArt ;Herkunft ;Organisations- einheit (OE) ;Pflegestufe ;PEA ";

	public static void print(List<Abrechnung> abr) {
		 
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter("abrechungDRMZ.csv");

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (Abrechnung ebrechn : abr) {
				fileWriter.append(String.valueOf(ebrechn.getArtikel() ));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(ebrechn.getArtikelname());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(ebrechn.getPatientname());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(ebrechn.getPatientstr());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(ebrechn.getVersichertennummer() ) );
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}

}

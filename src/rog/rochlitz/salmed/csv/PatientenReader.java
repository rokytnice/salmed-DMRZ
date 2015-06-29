package rog.rochlitz.salmed.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rog.rochlitz.salmed.objects.Patient;

public class PatientenReader {

	private final String COMMA_DELIMITER = ",";

	private final int SPALTE_1 = 0;//gesperrt
	private final int SPALTE_2 = 1;//matchcode
	private final int SPALTE_3 = 2;//versichertenr
	private final int SPALTE_4 = 3;//vorname
	private final int SPALTE_5 = 5;//nachname
	private final int versichertennr = 2;//nachname
	private final int name=3;
	private final int vname=4;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private List<Patient> patienten;

	public void readCsvFile(String fileName) {
		BufferedReader fileReader = null;
		try {

			patienten = new ArrayList<Patient>();
			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					
					Patient patient = new Patient(
							tokens[versichertennr],
							tokens[name], 
							 tokens[vname],
							 tokens[5]);
					
					patienten.add(patient);
				}
			}

			for (Patient patient : patienten) {
				System.out.println(patient.toString());
			}
		} catch (Exception e) {
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

	public List<Patient> getPatienten() {
		return patienten;
	}

	public void setPatienten(List<Patient> patiente) {
		this.patienten = patiente;
	}

	public static void main(String[] args) {
		PatientenReader l = new PatientenReader();
		l.readCsvFile(args[0]);

	}

}

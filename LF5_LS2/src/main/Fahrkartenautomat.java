package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

class Fahrkartenautomat {
	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);
			
		begruessung();
		
		double zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
		double eingezahlterGesamtbetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
		
		fahrkarteAusgeben();
		
		rueckgeldAusgeben(eingezahlterGesamtbetrag, zuZahlenderBetrag);
		
		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");
		
		tastatur.close();
	}
	
	boolean test;
	
	public static void begruessung() {
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter df;
	    df = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	        
		System.out.println("Herzlich Willkommen!						" + date.format(df));
		System.out.println("");
		System.out.println("");
		
	}
	
	public static double fahrkartenbestellungErfassen(Scanner tastatur) {
		boolean repeat = true;
		boolean repeat2 = true;
		int fahrscheinEingabe = 0;
		double zuZahlen = 0.0;
		int anzahlTickets = 0;
		
		HashMap<String, Double> Fahrkarten = new LinkedHashMap<String, Double>();
		Fahrkarten.put("Einzelfahrschein Berlin AB", 3.0);
		Fahrkarten.put("Einzelfahrschein Berlin BC", 3.5);
		Fahrkarten.put("Einzelfahrschein Berlin AC", 3.8);
		Fahrkarten.put("Kurzstrecke AB", 2.0);
		Fahrkarten.put("Tageskarte AB", 8.6);
		Fahrkarten.put("Tageskarte BC", 9.2);
		Fahrkarten.put("Tageskarte ABC", 10.0);
		Fahrkarten.put("4-Farhten-Karte-AB", 9.4);
		Fahrkarten.put("4-Farhten-Karte-BC", 12.6);
		Fahrkarten.put("4-Farhten-Karte-ABC", 13.8);
		Fahrkarten.put("Kleingruppen-Tageskarte AB", 25.5);
		Fahrkarten.put("Kleingruppen-Tageskarte BC", 26.0);
		Fahrkarten.put("Kleingruppen-Tageskarte ABC", 26.5);
		
		double[] fahrkartenPreise = new double[13];
		fahrkartenPreise[0] = 3.0;
		fahrkartenPreise[1] = 3.5;
		fahrkartenPreise[2] = 3.8;
		fahrkartenPreise[3] = 2.0;
		fahrkartenPreise[4] = 8.6;
		fahrkartenPreise[5] = 9.2;
		fahrkartenPreise[6] = 10.0;
		fahrkartenPreise[7] = 9.4;
		fahrkartenPreise[8] = 12.6;
		fahrkartenPreise[9] = 13.8;
		fahrkartenPreise[10] = 25.5;
		fahrkartenPreise[11] = 26.0;
		fahrkartenPreise[12] = 26.5;
		
		String[] fahrkartenBezeichnung = new String[13];
		fahrkartenBezeichnung[0] = "Einzelfahrschein AB";
		fahrkartenBezeichnung[1] = "Einzelfahrschein BC";
		fahrkartenBezeichnung[2] = "Einzelfahrschein AC";
		fahrkartenBezeichnung[3] = "Kurzstrecke AB";
		fahrkartenBezeichnung[4] = "Tageskarte AB";
		fahrkartenBezeichnung[5] = "Tageskarte BC";
		fahrkartenBezeichnung[6] = "Tageskarte ABC";
		fahrkartenBezeichnung[7] = "4-Farhten-Karte-AB";
		fahrkartenBezeichnung[8] = "4-Fahrten-Karte BC";
		fahrkartenBezeichnung[9] = "4-Fahrten-Karte ABC";
		fahrkartenBezeichnung[10] = "Kleingruppen-Tageskarte AB";
		fahrkartenBezeichnung[11] = "Kleingruppen-Tageskarte BC";
		fahrkartenBezeichnung[12] = "Kleingruppen-Tageskarte ABC";
		
		int zaehler = 0;
		
		while (repeat) {
			if (anzahlTickets != 0) {
				zuZahlen = zuZahlen + (fahrkartenPreise[fahrscheinEingabe-1] * anzahlTickets);
			}
			repeat2 = true;
			System.out.println("Bitte wählen sie den Fahrschein aus oder beenden sie die Auswahl über fertig: ");
			fahrscheinEingabe = tastatur.nextInt();
			switch (fahrscheinEingabe) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
				anzahlTickets = fahrscheinBerechnungEingabe(fahrscheinEingabe, fahrkartenBezeichnung, fahrkartenPreise);
				break;
			case 0:
				repeat = false;
				break;
			default:
				System.out.println(">> Falsche Eingabe <<");
				break;
			}
		}
				
		return zuZahlen;
		
	}
	
	public static double fahrkartenBezahlen(Scanner tastatur, double zuBezahlen) {
		// Geldeinwurf
		double eingezahlt;
		double eingeworfeneMuenze;
		double nochZuZahlen;
		eingezahlt = 0.00;
		nochZuZahlen = 0.00;
		while (eingezahlt < zuBezahlen) {
			nochZuZahlen = zuBezahlen - eingezahlt;
			System.out.printf("%s%.2f%s\n","Noch zu zahlen: ", nochZuZahlen, " Euro");
			System.out.print("Eingabe (mind. 5 Cent, höchstens 20 Euro): ");
			eingeworfeneMuenze = tastatur.nextDouble();
			
			String test = "" + eingeworfeneMuenze;
			
			if (test.equals("0.05") || test.equals("0.1") || test.equals("0.2") || test.equals("0.5") || test.equals("1.0") || test.equals("2.0") || test.equals("5.0") || test.equals("10.0") || test.equals("20.0")) {}
			else {
				eingeworfeneMuenze = 0.0;
				System.out.println(">> Kein gültiges Zahlungsmittel");
			}
			
			eingezahlt = eingezahlt + eingeworfeneMuenze;
		}
		return eingezahlt;
	}
	
	public static void fahrkarteAusgeben() {
		// Fahrscheinausgabe
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(200);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
	}
	
	public static void rueckgeldAusgeben(double eingezahlt, double zuZahlen) {
		// Rückgeldberechnung und -ausgabe
		double rueckgabebetrag = eingezahlt - zuZahlen;
		rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
		System.out.println(rueckgabebetrag);
		if (rueckgabebetrag > 0.00) {
			System.out.printf("%s%.2f%s\n","Der Rückgabebetrag in Höhe von ", rueckgabebetrag, " Euro");
			System.out.println("wird in folgenden Münzen ausgezahlt:");
			
			while (rueckgabebetrag >= 2.00) { // 2-Euro-Münzen
				System.out.println("2 Euro");
				rueckgabebetrag = rueckgabebetrag - 2.0;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
			while (rueckgabebetrag >= 1.00) { // 1-Euro-Münzen
				System.out.println("1 Euro");
				rueckgabebetrag = rueckgabebetrag - 1.0;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
			while (rueckgabebetrag >= 0.50) { // 50-Cent-Münzen
				System.out.println("50 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.5;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
			while (rueckgabebetrag >= 0.20) { // 20-Cent-Münzen
				System.out.println("20 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.2;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
			while (rueckgabebetrag >= 0.10) { // 10-Cent-Münzen
				System.out.println("10 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.1;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
			while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
				System.out.println("5 Cent");
				rueckgabebetrag = rueckgabebetrag - 0.05;
				rueckgabebetrag = Math.round(rueckgabebetrag* 100.0) / 100.0;
			}
		}
	}
	
	public static int fahrscheinBerechnungEingabe(int fahrschein, String[] bezeichnungen, double[] preiseKarten) {
		System.out.println("Sie haben " + bezeichnungen[fahrschein-1] + " ausgewählt!");
		System.out.println("Preis pro Karte: " + preiseKarten[fahrschein-1] + " Euro.");
		boolean repeat2 = true;
		Scanner tastatur = new Scanner(System.in);
		int anzahlT = 0;
		while (repeat2) {
			System.out.print("Anzahl der Tickets: ");
			anzahlT = tastatur.nextInt();
			System.out.println();
			if (anzahlT < 1.0 || anzahlT > 10.0) {
				System.out.println(">> Wählen sie bitte eine Fahrkartenmenge zwischen 1 und 10 <<");
			} else {repeat2 = false; return anzahlT;}
		}
		return 0;
	}
}
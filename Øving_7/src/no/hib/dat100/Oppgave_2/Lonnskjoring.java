package no.hib.dat100.Oppgave_2;

import java.util.Scanner;

public class Lonnskjoring {
	private String firmanamn;
	private Arbeidstaker[] at;
	private int antalTilsette;

	/* Standard konstrukt�r */
	public Lonnskjoring() {
		at = new Arbeidstaker[antalTilsette];
		firmanamn = "";
	} // konstrukt�r

	/* Konstrukt�r med parameter */
	public Lonnskjoring(String firmanamn, int MAXANTALL) {
		at = new Arbeidstaker[MAXANTALL];
		this.firmanamn = firmanamn;
	} // konstrukt�r

	/* Set- og get metoder */
	public String getFirmanamn() {
		return firmanamn;
	} // get

	public void setFirmanamn(String firmanamn) {
		this.firmanamn = firmanamn;
	} // set

	public int getAntalTilsette() {
		return antalTilsette;
	} // get

	public void setAntalTilsette(int antalTilsette) {
		this.antalTilsette = antalTilsette;
	} // set
	
	/* Leser inn ansatt fra klient */
	public void lesArbeidstakararEnkel(Arbeidstaker a) {
		at[antalTilsette] = a;
		antalTilsette++;
	} // metode

	/* Leser inn en ansatt om gangen */
	public void lesArbeidstakarar() {
		Scanner tast = new Scanner(System.in);
		System.out.print("Hvor mange arbeidstakarar skal leses inn? ");
		int lesInnAntall = tast.nextInt();
		
		for (int i = 0; i < lesInnAntall; i++) {
			Arbeidstaker p = new Arbeidstaker();
			p.lesArbeidtakar();
			at[antalTilsette] = p;
			antalTilsette++;
		}
		tast.close();
	} // metode

	/* Finner total overtid over de ansatte */
	public double finnTotalOvertid() {
		double sumOvertid = 0;
		for (int j = 0; j < antalTilsette; j++) {
			double totalOvertid = at[j].finnOvertidsTimar();
			sumOvertid += totalOvertid;
		} // for
		return sumOvertid;		
	} // metode

	/* Finner total bruttol�nn for de ansatte */
	public double finnTotalBruttol�n() {
		double sumBrutto = 0;
		for (int j = 0; j < antalTilsette; j++) {
			double totalBrutto = at[j].finnBruttolon();
			sumBrutto += totalBrutto;
		} // for
		return sumBrutto;
	} // metode
	
	/* Skriver ut den h�yeste bruttol�nnen til en av de ansatte */
	public double skrivHogasteBrutto() {
		double storste = 0;
		storste = at[0].finnBruttolon();
		for (int i = 1; i < antalTilsette; i++) {
			if (at[i].finnBruttolon() > storste) {
				storste = at[i].finnBruttolon();
			} // if
		} // for		
		return storste;
	} // metode

	/* Skriver ut l�nnsoversikt til de ansatte */
	public void skrivLonsoversikt() {
		for (int i = 0; i < antalTilsette; i++) {
			at[i].skrivUt();
		} // for
	} // metode


	/* Skriv inn firmanavn */
	public void skrivInnFirmaNamn()	{
		Scanner tast = new Scanner(System.in);
		System.out.print("Skriv inn firma namn: ");
		setFirmanamn(tast.nextLine());
		System.out.println();
		tast.close();		
	} // metode
	
	/* Skriv ut firmanavn */
	public void skrivUtFirmaNamn() {		
		System.out.println("Firmanamn: " + getFirmanamn() + "\n");
	} // metode
	
	/* Informasjon til de ansatte i plass "i" i l�nnskj�ring tabell */
	public void personInnhold(int i) {
		System.out.println();
		Arbeidstaker.tabellOverskrift();
		at[i].finnOvertidsTimar();
		at[i].finnBruttolon();
		at[i].finnSkatt();
		at[i].finnNettolon();
		at[i].skrivUt();
	} // metode

	/* S�k opp en ansatt i firmaet, og skriv ut informasjon om personen */
	public boolean finnOgSkrivPerson() {
		Scanner tast = new Scanner(System.in);
		System.out.print("S�k p� ansatt: ");

		/* Skriver inn navn p� ansatt */
		String namn = "";
		namn = tast.nextLine();
		namn = namn.toLowerCase();
		tast.close();

		if (sluttNamn(namn) == true) {
			return true;
		} else if (startNamn(namn) == true) {
			return true;
		}
		System.out.println("Fant ikke den ansatte du s�kte p�!");
		return false;
	} // metode

	/*
	 * Metoden skjekker om navnet du skrev inn starter p� fornamnet til de
	 * ansatte
	 */
	public boolean startNamn(String namn) {
		for (int i = 0; i < antalTilsette; i++) {
			String lowerCaseNamn = at[i].getNamn();
			lowerCaseNamn = lowerCaseNamn.toLowerCase();
			if (namn.toLowerCase().startsWith(lowerCaseNamn)) {
				personInnhold(i);
				return true;
			} // if
		} // for
		return false;
	} // metode

	/*
	 * Metoden skjekker om navnet du skrev inn slutter p� etternamnet til de
	 * ansatte
	 */
	public boolean sluttNamn(String namn) {
		for (int i = 0; i < antalTilsette; i++) {
			String lowerCaseEtternamn = at[i].getEtternamn();
			lowerCaseEtternamn = lowerCaseEtternamn.toLowerCase();
			if (namn.toLowerCase().endsWith(lowerCaseEtternamn)) {
				personInnhold(i);
				return true;
			} // if
		} // for
		return false;
	} // metode

	public void skrivTotalOversikt() {
		System.out.printf("%s%.2f%s%n", "Total Overtid\t\t : ", finnTotalOvertid(), " timer");
		System.out.printf("%s%.2f%s%n", "Total Bruttol�nn\t : ", finnTotalBruttol�n(), " kr");
		System.out.printf("%s%.2f%s%n", "H�yeste Bruttol�nn\t : ", skrivHogasteBrutto(), " kr");
	}
} // class

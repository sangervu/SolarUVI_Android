package com.e.solaruvi_android;

import java.util.Scanner;

import com.e.solaruvi_android.calculations.ElementsSun;
import com.e.solaruvi_android.calculations.SolarCalculations;
import com.e.solaruvi_android.calculations.SunPosition;
import com.e.solaruvi_android.common.Location;
import com.e.solaruvi_android.common.MyStellarCalendar;

public class MainActivityEclipse {

	public static void main(String[] args) {

		int jatka = 1;

		while (jatka == 1) {
			
			System.out.println("Jatka, paina 1 ");
			Scanner annaLuku = new Scanner(System.in);
			jatka = annaLuku.nextInt();

			if (jatka == 1) {
				System.out.println("1: UVI nyt (oletussijainnissa) 2: Anna uusi sijainti");
				Scanner input = new Scanner(System.in);
				int valinta = input.nextInt();
				switch (valinta) {
				
				case 1:
					Location.getCurrentLatitude(); // alustetaan sijainti (sijainnin voi my�s antaa k�sin)
					Location.getCurrentLongitude(); // alustetaan sijainti (sijainnin voi my�s antaa k�sin)
					MyStellarCalendar.MyDate(); // alustetaan pvm (pvm voi my�s antaa k�sin)
					MyStellarCalendar.Julian(); // alustetaan julian ja T
					MyStellarCalendar.StellarTime(); //alustetaan solaarinen aika pituuspiirin mukaan (asteet)
					ElementsSun.elementsSun(); //alustetaan maan epsilon ja auringon x, y, z koordinaatit
					SunPosition.sunPosition(); //alustetaan auringon koordinatit (atsimuutti ja korkeus)
					SolarCalculations.solarCalculations(); //alustetaan auringon sijaitiin liittyv�t laskut
					SolarCalculations.SolarPower(); //alustetaan auringon s�teilytehoon liittyv�t laskut
					PrintSolarCalculations.print();
					break;
					
				case 2:
					System.out.println("Anna pituuspiiri [asteina] ");
					Scanner lon = new Scanner(System.in);
					double longitude = lon.nextDouble();
					Location.setCurrentLongitude(longitude);

					System.out.println("Anna leveyspiiri [asteina] ");
					Scanner lat = new Scanner(System.in);
					double latitude = lat.nextDouble();
					Location.setCurrentLatitude(latitude);

					break;

				}
			}
		}
	}
}

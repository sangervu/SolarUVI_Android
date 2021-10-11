package com.e.solaruvi_android.calculations;

import com.e.solaruvi_android.common.Location;
import com.e.solaruvi_android.common.MathNew;
import com.e.solaruvi_android.common.MyStellarCalendar;
import com.e.solaruvi_android.common.TimeFormat;
import com.e.solaruvi_android.common.TrueDegree;

public class SolarCalculations {

//	static double thermalActivity;
//	static double thermalActivityStrong;
//	static double thermalActivityEnd;
//	static String thermalActivityEndString;
	public static double UvIndex;
	public static double UvIndexMax;
	public static double UvIndexOverThree;
	public static double UvIndexEnd;
	public static String UvIndexEndString;
	public static double maxSunRadiationPowerAnnual;
	public static double maxSunRadiationPowerDiurnal;
	public static double currentSunRadiationPower;
	public static String timeSouthString;
	private static double timeSouth;

	public static void south() {

		double alfaDeg = ElementsSun.alfaSunDeg;
		double timeStellarNoonDeg = MyStellarCalendar.stellarTimeNoonDeg;

		/* korkeimmillaan, eli etel�ss� */
		timeSouth = TrueDegree.minDegree(alfaDeg - timeStellarNoonDeg) * 24. / 360.;
		timeSouthString = TimeFormat.timeHour(timeSouth) + ":" + TimeFormat.timeMinute(timeSouth);
	}

	public static void solarCalculations() {
		
		SolarCalculations.south();
		double deltaSunDeg = ElementsSun.alfaSunDeg;
		double latitudeDeg = Location.latitude;
		double currentSunElevationDeg = SunPosition.currentElevationDeg;
		double maxSunAltitudeDiurnalDeg = SunPosition.maxElevationDeg;

		// elevation, UV
		double uvOverThreeLimitRad = Math.toRadians(90.0 - 48.0); // degrees in Sun elevation, UV

		double latitudeRad = Math.toRadians(latitudeDeg);
		double currentSunElevationRad = Math.toRadians(currentSunElevationDeg);

		double deltaSunRad = Math.toRadians(deltaSunDeg);

		double a = 2.696056, b = 5.474571, c = -0.09888, d = 0.040392;
		double m = 1. / Math.cos(Math.asin(6371. / 6393. * Math.sin((Math.PI / 2 - currentSunElevationRad))));

		double uvIndex = MathNew.roundDesimal_1(MathNew.pow(Math.cos(Math.PI / 2 - currentSunElevationRad), a)
				* MathNew.exp(b + c * m + d * m * m) / 25.);

		if (Double.isNaN(uvIndex)) {
			UvIndex = 0.;
		} else {

			UvIndex = uvIndex;
		}

		double maxSunAltitudeDiurnalRad = Math.toRadians(maxSunAltitudeDiurnalDeg);

		double mMax = 1. / Math.cos(Math.asin(6371. / 6393. * Math.sin((Math.PI / 2 - maxSunAltitudeDiurnalRad))));

		UvIndexMax = MathNew.roundDesimal_1(MathNew.pow(Math.cos(Math.PI / 2 - maxSunAltitudeDiurnalRad), a)
				* MathNew.exp(b + c * mMax + d * mMax * mMax) / 25.);

		double uvIndexOverThree = 2 * Math.acos(-Math.tan(deltaSunRad) * Math.tan(latitudeRad)
						+ Math.sin(uvOverThreeLimitRad) / (Math.cos(deltaSunRad) * Math.cos(latitudeRad))) / (2 * Math.PI) * 24;

		if (Double.isNaN(uvIndexOverThree)) {
			UvIndexOverThree = 0.;
		}
		else {
			UvIndexOverThree = uvIndexOverThree;
		}

		if (Double.isNaN(uvIndex) || uvIndexOverThree == 0) {
			UvIndexEnd = 0.;
		} else {
			UvIndexEnd = timeSouth + uvIndexOverThree / 2;
		}

		UvIndexEndString = (TimeFormat.timeHour(UvIndexEnd)) + ":" + (TimeFormat.timeMinute(UvIndexEnd));

	}

	public static void SolarPower() {

		double latitudeDeg = Location.latitude;

		double currentSunElevationDeg = SunPosition.currentElevationDeg;
		double maxSunElevationDeg = SunPosition.maxElevationDeg;

		// calculate max elevation of the sun in degrees
		double elevationMax;
		if (latitudeDeg < 23.5 & latitudeDeg > -23.5) {
			elevationMax = 90.;
		} else if (latitudeDeg > 23.5) {
			elevationMax = 90 - latitudeDeg + 23.5;
		} else {
			elevationMax = 90 + latitudeDeg + 23.5;
		}
		
		// Maximun solar power annual
		double maxSunAltitudeAnnualRad = Math.toRadians(elevationMax); // max Sun elevation in Rad
		double maxSunAltitudeDiurnalRad = Math.toRadians(maxSunElevationDeg);

		maxSunRadiationPowerAnnual = MathNew.roundDesimal_1(1350.0 * Math.sin(maxSunAltitudeAnnualRad)
				* MathNew.pow(0.78, (1 / Math.sin(maxSunAltitudeAnnualRad))));

		// Maximun solar power diurnal
		double sunPower = MathNew.roundDesimal_1(1350.0 * Math.sin(maxSunAltitudeDiurnalRad)
				* MathNew.pow(0.78, (1 / Math.sin(maxSunAltitudeDiurnalRad))));
		if (sunPower < 0) {
			maxSunRadiationPowerDiurnal = 0;
		} else {
			maxSunRadiationPowerDiurnal = sunPower;
		}

		// Current solar power
		double currentSunElevationRad = Math.toRadians(currentSunElevationDeg);
		sunPower = MathNew.roundDesimal_1(
				1350.0 * Math.sin(currentSunElevationRad) * MathNew.pow(0.78, (1 / Math.sin(currentSunElevationRad))));
		if (sunPower < 0) {
			currentSunRadiationPower = 0;
		} else {
			currentSunRadiationPower = sunPower;
		}
	}
}

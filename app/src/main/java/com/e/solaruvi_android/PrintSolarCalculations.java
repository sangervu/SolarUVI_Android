package com.e.solaruvi_android;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.solaruvi_android.calculations.SolarCalculations;
import com.e.solaruvi_android.calculations.SunPosition;

public class PrintSolarCalculations extends AppCompatActivity {
	
	 public void print() {

		 TextView dataText1;
		 TextView dataText2;
		 TextView dataText3;
		 TextView dataText4;
		 TextView dataText5;
		 TextView dataText6;
		 TextView dataText7;
		 TextView dataText8;
		 TextView dataText9;
		 TextView dataText10;
		 TextView dataText11;

		 dataText1 = (TextView) findViewById(R.id.sun_current_elevation);
		 dataText2 = (TextView) findViewById(R.id.sun_current_azimuth);
		 dataText3 = (TextView) findViewById(R.id.sun_max_elevation_today);
		 dataText4 = (TextView) findViewById(R.id.time_maximun_elevation);
		 dataText5 = (TextView) findViewById(R.id.current_solar_Power);
		 dataText6 = (TextView) findViewById(R.id.maximun_solar_Power_year);
		 dataText7 = (TextView) findViewById(R.id.maximum_solar_Power_today);
		 dataText8 = (TextView) findViewById(R.id.uvi_now);
		 dataText9 = (TextView) findViewById(R.id.uvi_max_today);
		 dataText10 = (TextView) findViewById(R.id.uvi_over_3);
		 dataText11 = (TextView) findViewById(R.id.uvi_end_time);

		 dataText1.setText(Double.toString(SunPosition.currentElevationDeg));
		 dataText2.setText(SunPosition.currentAzimuthString);
		 //dataText2.setText(Double.toString(SunPosition.currentAzimuthDeg));
		 dataText3.setText(Integer.toString((int) SunPosition.maxElevationDeg));
		 dataText4.setText(SolarCalculations.timeSouthString);
		 dataText5.setText(Double.toString(SolarCalculations.currentSunRadiationPower));
		 dataText6.setText(Double.toString(SolarCalculations.maxSunRadiationPowerAnnual));
		 dataText7.setText(Double.toString(SolarCalculations.maxSunRadiationPowerDiurnal));
		 dataText8.setText(Double.toString(SolarCalculations.UvIndex));
		 dataText9.setText(Double.toString(SolarCalculations.UvIndexMax));
		 dataText10.setText(Double.toString(SolarCalculations.UvIndexOverThree));
		 dataText11.setText(SolarCalculations.UvIndexEndString);
	    }

}

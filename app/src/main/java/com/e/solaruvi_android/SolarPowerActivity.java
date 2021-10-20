package com.e.solaruvi_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.e.solaruvi_android.calculations.SolarCalculations;
import com.e.solaruvi_android.calculations.SunPosition;

public class SolarPowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_power);
        TextView dataText5;
        TextView dataText6;
        TextView dataText7;
        dataText5 = (TextView) findViewById(R.id.current_solar_Power);
        dataText6 = (TextView) findViewById(R.id.maximun_solar_Power_year);
        dataText7 = (TextView) findViewById(R.id.maximum_solar_Power_today);
        dataText5.setText(Double.toString(SolarCalculations.currentSunRadiationPower));
        dataText6.setText(Double.toString(SolarCalculations.maxSunRadiationPowerAnnual));
        dataText7.setText(Double.toString(SolarCalculations.maxSunRadiationPowerDiurnal));
    }
}
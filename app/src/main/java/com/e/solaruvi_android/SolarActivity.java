package com.e.solaruvi_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.e.solaruvi_android.calculations.SolarCalculations;
import com.e.solaruvi_android.calculations.SunPosition;

public class SolarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar);

        TextView dataText1;
        TextView dataText2;
        TextView dataText3;
        TextView dataText4;

        dataText1 = (TextView) findViewById(R.id.sun_current_elevation);
        dataText2 = (TextView) findViewById(R.id.sun_current_azimuth);
        dataText3 = (TextView) findViewById(R.id.sun_max_elevation_today);
        dataText4 = (TextView) findViewById(R.id.time_maximun_elevation);

        dataText1.setText(Double.toString(SunPosition.currentElevationDeg));
        dataText2.setText(SunPosition.currentAzimuthString);
        //dataText2.setText(Double.toString(SunPosition.currentAzimuthDeg));
        dataText3.setText(Integer.toString((int) SunPosition.maxElevationDeg));
        dataText4.setText(SolarCalculations.timeSouthString);
    }
}
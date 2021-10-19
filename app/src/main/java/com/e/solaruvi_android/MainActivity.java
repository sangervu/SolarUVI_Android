package com.e.solaruvi_android;

import android.content.Intent;
import android.os.Bundle;

import com.e.solaruvi_android.calculations.ElementsSun;
import com.e.solaruvi_android.calculations.SolarCalculations;
import com.e.solaruvi_android.calculations.SunPosition;
import com.e.solaruvi_android.common.Location;
import com.e.solaruvi_android.common.MyStellarCalendar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.e.solaruvi_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Location.getCurrentLatitude(); // alustetaan sijainti (sijainnin voi my�s antaa k�sin)
        Location.getCurrentLongitude(); // alustetaan sijainti (sijainnin voi my�s antaa k�sin)
        MyStellarCalendar.MyDate(); // alustetaan pvm (pvm voi my�s antaa k�sin)
        MyStellarCalendar.Julian(); // alustetaan julian ja T
        MyStellarCalendar.StellarTime(); //alustetaan solaarinen aika pituuspiirin mukaan (asteet)
        ElementsSun.elementsSun(); //alustetaan maan epsilon ja auringon x, y, z koordinaatit
        SunPosition.sunPosition(); //alustetaan auringon koordinatit (atsimuutti ja korkeus)
        SolarCalculations.solarCalculations(); //alustetaan auringon sijaitiin liittyv�t laskut
        SolarCalculations.SolarPower(); //alustetaan auringon s�teilytehoon liittyv�t laskut

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

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.show_uvi) {

            Intent showUvi = new Intent(MainActivity.this, SolarPowerActivity.class);
            startActivity(showUvi);
            return true;
        }

        if (id == R.id.show_power) {

            Intent showPower = new Intent(MainActivity.this, UviActivity.class);
            startActivity(showPower);
            return true;
        }

        if (id == R.id.show_calendar) {

            Intent showCalendar = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(showCalendar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
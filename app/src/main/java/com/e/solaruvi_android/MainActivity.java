package com.e.solaruvi_android;

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

        PrintSolarCalculations print = new PrintSolarCalculations();
        print.print();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
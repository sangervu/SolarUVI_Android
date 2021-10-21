package com.e.solaruvi_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.e.solaruvi_android.calculations.SolarCalculations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in millisecond

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(selectedDate));

        TextView dataText1 = (TextView) findViewById(R.id.editTextDate);

        dataText1.setText(dateString);

    }
}
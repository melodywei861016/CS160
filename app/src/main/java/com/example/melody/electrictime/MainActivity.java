package com.example.melody.electrictime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, OnClickListener {

    private static Context mContext;
    private static String transportation;
    String[] items = new String[]{"Walking", "Boosted Mini S Board", "Evolve Skateboard", "OneWheel", "MotoTec Skateboard",
            "Segway Ninebot One S1", "Segway i2 SE", "Razor Scooter", "GeoBlade 500", "Hovertrax Hoverboard"};
    boolean[] toPrint = new boolean[]{true, true, true, false, false, false, true, false, false, true};
    double[] item_time = new double[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.dropdown_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        Button convertButton = findViewById(R.id.convert);
        convertButton.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        transportation = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        EditText edit = findViewById(R.id.distance_textField);
        String distance_text = edit.getText().toString();
        Log.i("distance", distance_text);
        double speed = 1.0;
        double range = 0.0;

        if (!isNumeric(distance_text)) {
            Toast.makeText(mContext, "Distance input invalid", Toast.LENGTH_SHORT).show();
        } else {
            double distance = Double.parseDouble(distance_text);

            if (transportation == "Walking") {
                speed = 3.1;
                range = 30;
                toPrint[0] = false;
            } else if (transportation == "Boosted Mini S Board") {
                speed = 18;
                range = 7;
                toPrint[1] = false;
            } else if (transportation == "Evolve Skateboard") {
                speed = 26;
                range = 31;
                toPrint[2] = false;
            } else if (transportation == "OneWheel") {
                speed = 19;
                range = 7;
            } else if (transportation == "MotoTec Skateboard") {
                speed = 22;
                range = 10;
            } else if (transportation == "Segway Ninebot One S1") {
                speed = 12.5;
                range = 15;
            } else if (transportation == "Segway i2 SE") {
                speed = 12.5;
                range = 24;
                toPrint[6] = false;
            } else if (transportation == "Razor Scooter") {
                speed = 10;
                range = 7;
            } else if (transportation == "GeoBlade 500") {
                speed = 15;
                range = 8;
            } else if (transportation == "Hovertrax Hoverboard") {
                speed = 8;
                range = 8;
                toPrint[9] = false;
            }


            if (distance > range || distance < 0) {
                Toast.makeText(mContext, "Distance out of range", Toast.LENGTH_SHORT).show();
            } else {

                item_time[0] = (distance / 3.1 ) * 60.0;
                item_time[1] = (distance / 18 ) * 60.0;
                item_time[2] = (distance / 26 ) * 60.0;
                item_time[6] = (distance / 12.5 ) * 60.0;
                item_time[9] = (distance / 8 ) * 60.0;

                double time = (distance / speed) * 60.0;
                TextView timeTextView = findViewById(R.id.time_result);
                timeTextView.setText(transportation + " takes " + String.format("%.2f", time) + " minutes");
                TextView otherTimeTextView = findViewById(R.id.other_time_result);
                String otherTimeString = "";
                for (int i = 0; i < toPrint.length; i++) {
                    if (toPrint[i] == true) {
                        otherTimeString += items[i] + " takes " + String.format("%.2f", item_time[i]) + " minutes\n";
                    }
                }
                otherTimeTextView.setText(otherTimeString);

            }
        }

        toPrint = new boolean[]{true, true, true, false, false, false, true, false, false, true};
        item_time = new double[10];

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}

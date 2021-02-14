package com.example.powertoolrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // class vars
    boolean powerWasherChecked = false;
    boolean tillerChecked = false;
    // CONSTS
    final double PWR_WSHR_COST = 55.99;
    final double TLLR_COST = 68.99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add icon to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void calcCost(View v) {
        // get elements by id
        EditText numbs = findViewById(R.id.editTextNumberSignedNumberofDays);

        // empty input check
        if (TextUtils.isEmpty(numbs.getText().toString()))
            return;

        // a radiobutton is checked... check
        if (!powerWasherChecked && !tillerChecked)
            return;

        // days within 1-7 range check
        int days = Integer.parseInt(numbs.getText().toString());
        if (!(1 <= days && days <= 7))
            return;

        // finally send the cost to be displayed
        if (powerWasherChecked)
            displayCost(PWR_WSHR_COST * days);
        if (tillerChecked)
            displayCost(TLLR_COST * days);
    }

    public void displayCost(double p) {
        TextView result = findViewById(R.id.textViewResult);
        DecimalFormat currency = new DecimalFormat("###,###,###.00");
        result.setText("$" + currency.format(p));
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.radioButtonPowerWasher:
                if (checked)
                    // power washer checked
                    powerWasherChecked = true;
                tillerChecked = false;
                break;
            case R.id.radioButtonTiller:
                if (checked)
                    // tiller checked
                    tillerChecked = true;
                powerWasherChecked = false;
                break;
        }
    }

}
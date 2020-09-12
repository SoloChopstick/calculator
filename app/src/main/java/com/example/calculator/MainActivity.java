package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int currentValue = 0;
    private TextView currentShownNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentShownNumber = (TextView) findViewById(R.id.result);
    }

    public void showNumber(View view) {
        currentValue++;
        if (currentShownNumber != null) {
            currentShownNumber.setText(Integer.toString(currentValue));
        }
    }

    //cheeky divide by 0
    public void divideByZero(View view) {
        Toast toast = Toast.makeText(this, R.string.divideByZero, Toast.LENGTH_LONG);
        toast.show();
    }
}
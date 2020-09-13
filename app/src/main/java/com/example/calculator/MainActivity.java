package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView currentShown;
    private int[] numericButtons = {R.id.buttonZero, R.id.buttonOne, R.id.buttonTwo, R.id.buttonThree,
            R.id.buttonFour, R.id.buttonFive, R.id.buttonSix, R.id.buttonSeven, R.id.buttonEight, R.id.buttonNine};
    private int[] operatorButtons = {R.id.buttonModular, R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonAddition, R.id.buttonSubstraction};
    private boolean isZero = true;
    private boolean lastNumeric = false;
    private float num1 = 0, num2 = 0;
    private String operator = "";
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find textView
        currentShown = (TextView) findViewById(R.id.result);
        // Find and set OnClickListener to numeric buttons
        setNumericOnClickListener();
        // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
        setClearOnClickListener();
        setEvaluateOnClickListener();
    }

    //On Click listener to evaluate memory
    private void setEvaluateOnClickListener() {
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num2 = Float.parseFloat(sb.toString());
                sb = new StringBuilder();
                float answer = 0;
                switch (operator) {
                    case "+" :
                        answer = addition(num1, num2);
                        break;
                    case "-" :
                        answer = substract(num1, num2);
                        break;
                    case "*" :
                        answer = multiply(num1, num2);
                        break;
                    case "/" :
                        answer = divide(num1, num2);
                        break;
                    case "%" :
                        answer = modular(num1, num2);
                        break;
                    default:
                        divideByZero();
                }
                currentShown.setText(Float.toString(answer));
                num1 = answer;
                num2 = 0;
                operator = "";
                lastNumeric = true;
            }
        });
    }

    //On Click listener to erase memory
    private void setClearOnClickListener() {
        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = 0;
                num2 = 0;
                operator = "";
                currentShown.setText("0");
                lastNumeric = false;
                isZero = true;
                sb = new StringBuilder();
            }
        });
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastNumeric) {
                    Button button = (Button) view;
                    num1 = Float.parseFloat(currentShown.getText().toString());
                    operator = button.getText().toString();
                    currentShown.append(button.getText());
                    lastNumeric = false;
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                //Starting with 0 as default showing
                if (isZero) {
                    currentShown.setText(button.getText());
                    isZero = false;
                } else {
                    currentShown.append(button.getText());
                    //If we have an operator selected
                    if (operator != "") {
                        sb = sb.append(button.getText());
                    }
                }
                lastNumeric = true;
            }
        };
        //assign listener to all buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private float addition (float num1, float num2) {
        return (num1 + num2);
    }

    private float substract (float num1, float num2) {
        return (num1 - num2);
    }

    private float multiply (float num1, float num2) {
        return (num1 * num2);
    }

    private float divide (float num1, float num2) {
        //divide by 0 error;
        if (num2 == 0) divideByZero();
        return (num1 / num2);
    }

    private float modular (float num1, float num2) {
        return (num1 % num2);
    }

    //cheeky divide by 0
    public void divideByZero() {
        Toast toast = Toast.makeText(this, R.string.divideByZero, Toast.LENGTH_LONG);
        toast.show();
    }
}
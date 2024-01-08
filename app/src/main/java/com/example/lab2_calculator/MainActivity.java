package com.example.lab2_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstNum = 0;
    double secondNum = 0;
    String operation = null;
    boolean isEnteringSecondNumber = false;
    TextView screen;

    public void performOperation() {
        secondNum = Double.parseDouble(screen.getText().toString());
        switch (operation) {
            case "/":
                firstNum /= secondNum;
                break;
            case "x":
                firstNum *= secondNum;
                break;
            case "+":
                firstNum += secondNum;
                break;
            case "-":
                firstNum -= secondNum;
                break;
            case "%":
                firstNum /= 100;
                break;
        }
        if (isWholeNumber(firstNum)) {
            screen.setText(String.valueOf((int) firstNum));
        } else {
            screen.setText(String.valueOf(firstNum));
        }
    }

    public boolean isWholeNumber(double number) {
        return number % 1 == 0; // If the number is a integer, return true
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button AC = findViewById(R.id.AC);
        Button del = findViewById(R.id.del);
        Button divide = findViewById(R.id.divide);
        Button sub = findViewById(R.id.sub);
        Button plus = findViewById(R.id.plus);
        Button percent = findViewById(R.id.percent);
        Button multiply = findViewById(R.id.multiply);
        Button equal = findViewById(R.id.equal);
        Button dot = findViewById(R.id.dot);

        screen = findViewById(R.id.screen);

        ArrayList<Button> nums = new ArrayList<Button>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        // Press number button
        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (isEnteringSecondNumber) {
                    screen.setText(b.getText().toString());
                    isEnteringSecondNumber = false;
                } else {
                    if (screen.getText().toString().equals("0") || screen.getText().toString().equals("0.0")) {
                        screen.setText(b.getText().toString());
                    } else {
                        screen.setText(screen.getText().toString() + b.getText().toString());
                    }
                }
            });
        }

        ArrayList<Button> opers = new ArrayList<Button>();
        opers.add(plus);
        opers.add(divide);
        opers.add(multiply);
        opers.add(percent);
        opers.add(sub);
        opers.add(equal);

        for (Button b : opers) {
            b.setOnClickListener(view -> {
                if (!isEnteringSecondNumber) {
                    firstNum = Double.parseDouble(screen.getText().toString());
                    if (!isWholeNumber(firstNum)) {
                        operation = b.getText().toString();
                        screen.setText(String.valueOf(firstNum));
                        isEnteringSecondNumber = true;
                    } else {
                        operation = b.getText().toString();
                        screen.setText(String.valueOf((int) firstNum));
                        isEnteringSecondNumber = true;
                    }
                } else {
                    // Handle consecutive operator presses
                    secondNum = Double.parseDouble(screen.getText().toString());
                    performOperation(); // Perform the previous operation
                    // screen.setText(String.valueOf(firstNum)); // Update the screen with the result
                    // firstNum = Double.parseDouble(screen.getText().toString()); // Update firstNum
                    operation = b.getText().toString(); // Update the operation
                }
            });
        }

        AC.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                screen.setText(num.substring(0, num.length() - 1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });

        dot.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString() + ".");
            }
        });

        equal.setOnClickListener(view -> {
            secondNum = Double.parseDouble(screen.getText().toString());
            performOperation(); // Perform the final operation
            isEnteringSecondNumber = false; // Reset the flag
        });
    }
}
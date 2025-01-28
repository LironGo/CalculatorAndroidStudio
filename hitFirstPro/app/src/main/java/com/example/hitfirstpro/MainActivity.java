package com.example.hitfirstpro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    double firstNumber;
    double secondNumber;
    char operator;
    boolean equalFlag = false;
    boolean operatorFlag = false;
    boolean clearFlag = false;
    boolean dotFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.textViewResult);
        result.setText("");
    }

    public void numFunction(View view) {
        clearFlag = false;
        if (result.getText() != "" && equalFlag == true) {
            result.setText("");
            equalFlag = false;
        }
        Button button = (Button) view;
        result.append(button.getText().toString());
    }

    public void operatorFunction(View view) {
        if (result.getText()=="")
        {
            return;
        }
        clearFlag = false;
        if (operatorFlag == true)
        {
            return;
        }
        char button = ((Button) view).getText().toString().charAt(0);
        operator = button;
        firstNumber = Double.parseDouble(result.getText().toString());
        result.setText("");
        operatorFlag = true;

    }

    public void dotFunction(View view) {
        if (result.getText() != "" && equalFlag == true)
        {
            return;
        }
        if (dotFlag == true)
        {
            return;
        }
        Button button = (Button) view;
        result.append(button.getText().toString());
        dotFlag = true;
    }

    public void equalsFunction(View view)
    {
        if (result.getText() == "" && operatorFlag == false)
        {
            return;
        }
        if (clearFlag == true)
        {
            return;
        }
        secondNumber =  Double.parseDouble(result.getText().toString());
        double resultComp;
        switch (operator)
        {
            case '+':
                resultComp = firstNumber + secondNumber;
                result.setText(String.valueOf(resultComp));
                break;
            case '-':
                resultComp = firstNumber - secondNumber;
                result.setText(String.valueOf(resultComp));
                break;
            case 'x':
                resultComp = firstNumber * secondNumber;
                result.setText(String.valueOf(resultComp));
                break;
            case '/':
                if (secondNumber != 0)
                {
                    resultComp = firstNumber / secondNumber;
                    result.setText(String.valueOf(resultComp));
                }
                else
                {
                    result.setText("Math Error");
                }
                break;
        }
        equalFlag = true;
        operatorFlag = false;
        clearFlag = false;
        dotFlag = false;
    }

    public void clearFunction(View view) {
        result.setText("");
        clearFlag = true;
    }

    public void deleteNumFunction(View view) {
        if (result.getText()=="" || equalFlag == true)
        {
            return;
        }
        int screenNumber = Integer.parseInt(result.getText().toString());
        result.setText(String.valueOf(screenNumber/10));
    }
}
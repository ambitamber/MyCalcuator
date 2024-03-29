package com.example.mycalcuator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private String pendingoperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttondot = (Button) findViewById(R.id.buttonperiod);

        Button buttonequal = (Button) findViewById(R.id.buttonequal);
        Button buttondivde = (Button) findViewById(R.id.buttondivde);
        Button buttonmultiply = (Button) findViewById(R.id.buttonmultple);
        Button buttonminus = (Button) findViewById(R.id.buttonminus);
        final Button buttonplus = (Button) findViewById(R.id.buttonplus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);

        Button buttonneg = findViewById(R.id.buttonneg);

        buttonneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newNumber.getText().toString();
                if(value.length()==0){
                    newNumber.setText("-");
                }
                else{
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    }
                    catch (NumberFormatException e){
                        newNumber.setText("");
                    }
                }
            }
        });

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                }
                catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingoperation = op;
                displayOperation.setText(pendingoperation);
            }
        };
        buttonequal.setOnClickListener(opListener);
        buttondivde.setOnClickListener(opListener);
        buttonminus.setOnClickListener(opListener);
        buttonmultiply.setOnClickListener(opListener);
        buttonplus.setOnClickListener(opListener);
    }
    private void performOperation(Double value,String operation){
        if(null == operand1){
            operand1 = value;
        }
        else {
            if (pendingoperation.equals("=")){
                pendingoperation = operation;
            }
            switch (pendingoperation){
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0){
                        operand1 = 0.0;
                    }
                    else{
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}

package com.example.myapplicationConverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Spinner type_select, from_select, to_select;
    TextView output;
    Button convertButton;
    ArrayList<String> arrayList_parent;
    ArrayList<String> arrayList_Weight, arrayList_Temperature, arrayList_Distance;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayAdapter<String> arrayAdapter_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type_select = findViewById(R.id.type_spinner);
        from_select = findViewById(R.id.fromType);
        to_select = findViewById(R.id.toType);
        input = findViewById(R.id.inputNumber);
        output = findViewById(R.id.outputNumber);
        convertButton = findViewById(R.id.button);

        arrayList_parent = new ArrayList<>();
        arrayList_parent.add("Weight");
        arrayList_parent.add("Distance");
        arrayList_parent.add("Temperature");

        arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(),
            android.R.layout.simple_spinner_item, arrayList_parent);

        type_select.setAdapter(arrayAdapter_parent);

        // Sub selection for weight, distance, temp
        arrayList_Weight = new ArrayList<>();
        arrayList_Weight.add("Kilogram");
        arrayList_Weight.add("Pounds");
        arrayList_Weight.add("Ounce");
        arrayList_Weight.add("Ton");

        arrayList_Temperature = new ArrayList<>();
        arrayList_Temperature.add("Celsius");
        arrayList_Temperature.add("Fahrenheit");
        arrayList_Temperature.add("Kelvin");

        arrayList_Distance = new ArrayList<>();
        arrayList_Distance.add("Kilometer");
        arrayList_Distance.add("Centimeter");
        arrayList_Distance.add("Mile");
        arrayList_Distance.add("Yard");
        arrayList_Distance.add("Inch");
        arrayList_Distance.add("Foot");

        type_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayList_Weight);
                } else if (position == 1) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayList_Distance);
                } else if (position == 2) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, arrayList_Temperature);
                }
                from_select.setAdapter(arrayAdapter_child);
                to_select.setAdapter(arrayAdapter_child);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertButton.setOnClickListener(v -> {
            String valueEntered = input.getText().toString();
            if (valueEntered.isEmpty()) {
                output.setText("Please enter a value.");
                return;
            }
            double value = Double.parseDouble(valueEntered);
            String type = type_select.getSelectedItem().toString();

            if (type.equals("Weight")) {
                ConvertWeight(value);
            } else if (type.equals("Distance")) {
                ConvertDistance(value);
            } else if (type.equals("Temperature")) {
                ConvertTemperature(value);
            }
        });
    }

    private void ConvertDistance(double value) {
        String from = from_select.getSelectedItem().toString();
        String to = to_select.getSelectedItem().toString();
        double result = 0;

        if (from.equals("Inch")) {
            if (to.equals("Centimeter")) {
                result = value * 2.54;
            } else if (to.equals("Foot")) {
                result = value / 12;
            } else if (to.equals("Yard")) {
                result = value / 36;
            } else if (to.equals("Mile")) {
                result = value / 63360;
            } else if (to.equals("Kilometer")) {
                result = value / 39370.1;
            } else {
                result = value;
            }
        } else if (from.equals("Foot")) {
            if (to.equals("Centimeter")) {
                result = value * 30.48;
            } else if (to.equals("Inch")) {
                result = value * 12;
            } else if (to.equals("Yard")) {
                result = value / 3;
            } else if (to.equals("Mile")) {
                result = value / 5280;
            } else if (to.equals("Kilometer")) {
                result = value / 3280.84;
            } else {
                result = value;
            }
        } else if (from.equals("Yard")) {
            if (to.equals("Centimeter")) {
                result = value * 91.44;
            } else if (to.equals("Foot")) {
                result = value * 3;
            } else if (to.equals("Inch")) {
                result = value * 36;
            } else if (to.equals("Mile")) {
                result = value / 1760;
            } else if (to.equals("Kilometer")) {
                result = value / 1094;
            } else {
                result = value;
            }
        } else if (from.equals("Mile")) {
            if (to.equals("Centimeter")) {
                result = value * 160934;
            } else if (to.equals("Foot")) {
                result = value * 5280;
            } else if (to.equals("Yard")) {
                result = value * 1760;
            } else if (to.equals("Inch")) {
                result = value * 63360;
            } else if (to.equals("Kilometer")) {
                result = value * 1.60934;
            } else {
                result = value;
            }
        } else if (from.equals("Kilometer")) {
            if (to.equals("Centimeter")) {
                result = value * 100000;
            } else if (to.equals("Mile")) {
                result = value / 1.60934;
            } else if (to.equals("Yard")) {
                result = value * 1094;
            } else if (to.equals("Inch")) {
                result = value * 39370.1;
            } else if (to.equals("Foot")) {
                result = value * 3280.84;
            } else {
                result = value;
            }
        }

        output.setText(String.format("%.2f", result));
    }

    private void ConvertTemperature(double value) {
        String from = from_select.getSelectedItem().toString();
        String to = to_select.getSelectedItem().toString();
        double result = 0;

        if (from.equals("Celsius")) {
            if (to.equals("Fahrenheit")) {
                result = (value * 1.8) + 32;
            } else if (to.equals("Kelvin")) {
                result = value + 273.15;
            } else {
                result = value;
            }
        } else if (from.equals("Fahrenheit")) {
            if (to.equals("Celsius")) {
                result = (value - 32) / 1.8;
            } else if (to.equals("Kelvin")) {
                result = ((value - 32) / 1.8) + 273.15;
            } else {
                result = value;
            }
        } else if (from.equals("Kelvin")) {
            if (to.equals("Celsius")) {
                result = value - 273.15;
            } else if (to.equals("Fahrenheit")) {
                result = ((value - 273.15) * 1.8) + 32;
            } else {
                result = value;
            }
        }

        output.setText(String.format("%.2f", result));
    }

    private void ConvertWeight(double value) {
        String from = from_select.getSelectedItem().toString();
        String to = to_select.getSelectedItem().toString();
        double result = 0;

        if (from.equals("Kilogram")) {
            if (to.equals("Pounds")) {
                result = value * 2.20462;
            } else if (to.equals("Ounce")) {
                result = value * 35.274;
            } else if (to.equals("Ton")) {
                result = value / 907.185;
            } else {
                result = value;
            }
        } else if (from.equals("Pounds")) {
            if (to.equals("Kilogram")) {
                result = value * 0.453592;
            } else if (to.equals("Ounce")) {
                result = value * 16;
            } else if (to.equals("Ton")) {
                result = value / 2000;
            } else {
                result = value;
            }
        } else if (from.equals("Ounce")) {
            if (to.equals("Kilogram")) {
                result = value / 35.274;
            } else if (to.equals("Pounds")) {
                result = value / 16;
            } else if (to.equals("Ton")) {
                result = value / 35274;
            } else {
                result = value;
            }
        } else if (from.equals("Ton")) {
            if (to.equals("Kilogram")) {
                result = value * 907.185;
            } else if (to.equals("Pounds")) {
                result = value * 2000;
            } else if (to.equals("Ounce")) {
                result = value * 35274;
            } else {
                result = value;
            }
        }

        output.setText(String.format("%.2f", result));
    }
}

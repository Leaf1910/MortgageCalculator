package com.example.mortgagecalculator;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mortgagecalculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mortgageAmount, tenure, interestRate;
    private TextView resultText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mortgageAmount = findViewById(R.id.mortgageAmount);
        tenure = findViewById(R.id.tenure);
        interestRate = findViewById(R.id.interestRate);
        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(v -> calculateEMI());
    }

    private void calculateEMI() {
        double principal = Double.parseDouble(mortgageAmount.getText().toString());
        double annualInterest = Double.parseDouble(interestRate.getText().toString());
        int tenureYears = Integer.parseInt(tenure.getText().toString());

        double monthlyInterest = annualInterest / 12 / 100;
        int tenureMonths = tenureYears * 12;

        double emi = (principal * monthlyInterest * Math.pow(1 + monthlyInterest, tenureMonths)) /
                (Math.pow(1 + monthlyInterest, tenureMonths) - 1);

        resultText.setText(String.format("Monthly EMI: %.2f", emi));
    }
}
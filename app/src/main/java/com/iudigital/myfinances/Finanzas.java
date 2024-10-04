package com.iudigital.myfinances;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Finanzas extends AppCompatActivity {
    private Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanzas);

        mSpinner = findViewById(R.id.mSpinner);

        ArrayList<String> elementos =new ArrayList<>();
        elementos.add("INGRESO");
        elementos.add("EGRESO");
        ArrayAdapter adp= new ArrayAdapter<>(Finanzas.this, android.R.layout.simple_spinner_dropdown_item,elementos);

        mSpinner.setAdapter(adp);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento =(String)  mSpinner.getAdapter().getItem(position);
                Toast.makeText(Finanzas.this,"selecionaste: "+ elemento,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }
}
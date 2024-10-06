package com.iudigital.myfinances;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iudigital.myfinances.entidades.Finance;
import com.iudigital.myfinances.entidades.Form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Finanzas extends AppCompatActivity {
    private Spinner mSpinner;
    Button btnGuardarCon,btnConsulrtar;
    EditText txtConcepto,txtValor;
    TextView txtSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanzas);

        mSpinner = findViewById(R.id.mSpinner);
        btnConsulrtar = findViewById(R.id.btnConsultar);
        btnGuardarCon = findViewById(R.id.btnGuardarCon);
        txtConcepto = findViewById(R.id.txtConcepto);
        TextView fecha = findViewById(R.id.txtFecha);
        txtValor = findViewById(R.id.txtValor);
        txtSaludo = findViewById(R.id.textSaludo);
        LocalDate fechaActual =LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        fecha.setText(fechaActual.format(formatter));
        String documentoUsuario = obtenerDocumentoUsuarioActual();
        List<Form> forms = Form.find(Form.class, "documento = ?", documentoUsuario);
        txtSaludo.setText(("BIENBENIDO!\n").concat(forms.get(0).getNombre()).concat("  ").concat(forms.get(0).getApellido()));


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
    public void guardarConsulta(View view) {
        String tipo = mSpinner.getSelectedItem().toString();
        String concepto = txtConcepto.getText().toString();
        String valor = txtValor.getText().toString();

        String documentoUsuario = obtenerDocumentoUsuarioActual();

        if (documentoUsuario != null) {
            List<Form> forms = Form.find(Form.class, "documento = ?", documentoUsuario);

            if (!forms.isEmpty()) {
                Form form = forms.get(0);
                String fechaActual = LocalDate.now().toString();
                Finance finance = new Finance(form, tipo, concepto, valor,fechaActual);
                finance.save();

                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }


    public String obtenerDocumentoUsuarioActual() {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        return preferences.getString("documento", null); // Devuelve null si no está logueado
    }
    public void cerrarSesion() {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public  void consultar(View view){
        Intent intent= new Intent(Finanzas.this,Consulta.class);
        startActivity(intent);
    }
    public void  home(View view){
        cerrarSesion();
        Intent intent= new Intent(Finanzas.this,MainActivity.class);
        startActivity(intent);
    }
}
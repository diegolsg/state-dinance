package com.iudigital.myfinances;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iudigital.myfinances.entidades.Finance;
import com.iudigital.myfinances.entidades.Form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consulta extends AppCompatActivity {

    Button btnBuscar;
    EditText txtFechaInicio, txtFechaFin;
    private ListView listViewConsulta;
    private TextView txtEgreso;
    private TextView txtIngreso;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String fechaInicio = txtFechaInicio.findViewById(R.id.txtFechaInicio).toString();
//        String fechaFin = txtFechaFin.findViewById(R.id.txtFechaFin).toString();
        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFin = findViewById(R.id.txtFechaFin);
        listViewConsulta = findViewById(R.id.listViewConsulta);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtEgreso = findViewById(R.id.txtEgreso);
        txtIngreso = findViewById(R.id.txtIngreso);
        txtTotal = findViewById(R.id.txtTotal);

    }
    public void  atras(View view){
        Intent intent= new Intent(Consulta.this,Finanzas.class);
        startActivity(intent);
    }

    public void  home3(View view){
        cerrarSesion();
        Intent intent= new Intent(Consulta.this,MainActivity.class);
        startActivity(intent);
    }
    public void cerrarSesion() {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void buscarRegistro(View view) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            String fechaInicioStr = txtFechaInicio.getText().toString();
            String fechaFinStr = txtFechaFin.getText().toString();

            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);

            String documentoUsuario = obtenerDocumentoUsuarioActual();
            List<Form> idForm = Form.find(Form.class,"documento = ?",documentoUsuario);
            Long idFinanzas =idForm.get(0).getId();

            List<Finance> financeList = Finance.find(Finance.class,
                    "FORM = ? AND FECHA BETWEEN ? AND ?",
                     idFinanzas.toString(),fechaInicio.toString(), fechaFin.toString());
            sumarIngresos(financeList);
            List<Map<String, String>> data = new ArrayList<>();

            for (Finance finance : financeList) {
                if (finance.getFecha() != null && finance.getConcepto() != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("fecha", finance.getFecha().toString());
                    map.put("concepto", finance.getConcepto().concat(" : " ).concat(finance.getValor()));
                    data.add(map);
                }
            }

            SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,  // Usa el layout simple_list_item_2
                    new String[] {"fecha", "concepto"},  // Las claves del mapa
                    new int[] {android.R.id.text1, android.R.id.text2}  // Los TextViews donde se mostrará la información
            );

            listViewConsulta.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al buscar registros", Toast.LENGTH_SHORT).show();
        }
    }

    public String obtenerDocumentoUsuarioActual() {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        return preferences.getString("documento", null); // Devuelve null si no está logueado
    }

     public void sumarIngresos(List<Finance> finances){
        Double totalIngresos = 0.0;
        Double totalEgresos =0.0;
        Double total =0.0;
        for(Finance suma: finances){
            if(suma.getTipo().equals("EGRESO")){
                totalEgresos = Double.valueOf(suma.getValor());
                totalEgresos+=totalEgresos;
                txtEgreso.setText(totalEgresos.toString());
            }else{
                totalIngresos = Double.valueOf(suma.getValor());
                totalIngresos+= totalIngresos;
                txtIngreso.setText(totalIngresos.toString());

            }
        }
        total = totalIngresos - totalEgresos;
        txtTotal.setText(total.toString());
     }

}
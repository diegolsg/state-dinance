package com.iudigital.myfinances;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iudigital.myfinances.entidades.Form;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar,btnIngresar;
    EditText txtUsuario, txtPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIngresar = findViewById(R.id.btnIngresar);

    }

    public void  registrar(View view){
        Intent intent= new Intent(MainActivity.this,Formulario.class);
        startActivity(intent);

    }

    public  void ingresar(View view){
        String pass = txtPassword.getText().toString();
        String usuario = txtUsuario.getText().toString();
        String email = txtUsuario.getText().toString();

        List<Form> resultado = Form.find(Form.class, "usuario = ? or email = ? and password = ?",
                usuario, email,pass);

        if (resultado.size()>0){
            guardarSesionUsuario(resultado.get(0).getDocumento());
            Intent intent= new Intent(MainActivity.this,Finanzas.class);
            startActivity(intent);
            txtUsuario.setText("");
            txtPassword.setText("");
        }else{
            txtUsuario.setText("");
            txtPassword.setText("");
            Toast.makeText(MainActivity.this,"verifique sus credenciales o realice el registro",
                    Toast.LENGTH_LONG).show();
        }




    }
    public void guardarSesionUsuario(String documento) {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("documento", documento);
        editor.apply();
    }


}
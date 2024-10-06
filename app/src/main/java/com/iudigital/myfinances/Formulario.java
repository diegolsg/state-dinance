package com.iudigital.myfinances;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Formulario extends AppCompatActivity {

    Button btnGuardar,btnActualizar;
    EditText txtDocumento,txtNombre,txtApellido,txtTelefono,txtEmail,txtContrasena,txtVeficacion,txtUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        txtDocumento = findViewById(R.id.txtDocumento);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtContrasena = findViewById(R.id.txtContrasena);
        txtVeficacion = findViewById(R.id.txtVerificacion);
        txtUsuario = findViewById(R.id.txtUsuario);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnGuardar = findViewById(R.id.btnGuardar);

    } public void  home(View view){
        cerrarSesion();
        Intent intent= new Intent(Formulario.this,MainActivity.class);
        startActivity(intent);
    }
    public void cerrarSesion() {
        SharedPreferences preferences = getSharedPreferences("miSesion", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


    public  void guardar(View view){
        String documento = txtDocumento.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String telefono =txtTelefono.getText().toString();
        String email = txtEmail.getText().toString();
        String contrasena = txtContrasena.getText().toString();
        String verifica = txtVeficacion.getText().toString();
        String usuarioForm = txtUsuario.getText().toString();


        Form formulario = new Form(documento,nombre,apellido,telefono,
                email,contrasena,verifica,usuarioForm);
        if (contrasena.equals(verifica)) {
            formulario.save();
            txtNombre.setText("");
            txtApellido.setText("");
            txtDocumento.setText("");
            txtEmail.setText("");
            txtContrasena.setText("");
            txtVeficacion.setText("");
            txtTelefono.setText("");
            txtUsuario.setText("");
            Intent intent= new Intent(Formulario.this,MainActivity.class);
            startActivity(intent);

        }else{
            txtContrasena.setText("");
            txtVeficacion.setText("");
            Toast.makeText(Formulario.this,"contraseñas no coincioden",Toast.LENGTH_LONG).show();

        }

    }
}
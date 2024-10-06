package com.iudigital.myfinances.entidades;

import com.orm.SugarRecord;

import java.util.List;

public class Form extends SugarRecord<Form> {
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;
    private String confirmacion;
    private String usuario;

    public Form(String documento, String nombre, String apellido, String telefono,
                String email, String password, String confirmacion, String usuario) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.confirmacion = confirmacion;
        this.usuario = usuario;
    }

    public Form() {
    }

    public Form(String usuario, String password, String email) {
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

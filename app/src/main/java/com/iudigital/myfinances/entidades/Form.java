package com.iudigital.myfinances.entidades;

import com.orm.SugarRecord;

import java.util.List;

public class Form extends SugarRecord<Form> {
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String telefono;
    private String email;
    private String password;
    private String confirmacion;
    private String usuario;

    public Form(String nombre, String usuario, String confirmacion, String email, String telefono,
                String numeroDocumento, String apellido, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.confirmacion = confirmacion;
        this.email = email;
        this.telefono = telefono;
        this.numeroDocumento = numeroDocumento;
        this.apellido = apellido;
        this.password = password;
    }

    public Form() {
    }

    public List<Finance> getFinances() {
        return Finance.find(Finance.class, "form = ?", String.valueOf(this.getId()));
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

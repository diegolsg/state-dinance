package com.iudigital.myfinances.entidades;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Finance extends SugarRecord<Finance> {
    private Form form;
    private String tipo;
    private String concepto;
    private String valor;
    private String fecha;

    public Finance(Form form, String tipo, String concepto, String valor, String fecha) {
        this.form = form;
        this.tipo = tipo;
        this.concepto = concepto;
        this.valor = valor;
        this.fecha = fecha;
    }

    public Finance() {
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

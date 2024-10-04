package com.iudigital.myfinances.entidades;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Finance extends SugarRecord<Finance> {
    private Form form;
    private String concepto;
    private BigDecimal ingreso;
    private BigDecimal egreso;
    private LocalDate fecha;

    public Finance(Form form, String concepto, BigDecimal ingreso,
                   BigDecimal egreso, LocalDate fecha) {
        this.form = form;
        this.concepto = concepto;
        this.ingreso = ingreso;
        this.egreso = egreso;
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getEgreso() {
        return egreso;
    }

    public void setEgreso(BigDecimal egreso) {
        this.egreso = egreso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

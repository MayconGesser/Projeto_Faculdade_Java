/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author Richard
 */
public class RegistroChamado implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String hora;
    private String data;
    private String assunto;
    private Tecnico tecnico;
    private Chamado chamado;

    public RegistroChamado(String assunto, Chamado chamado, Tecnico tecnico) {
        Calendar c = Calendar.getInstance();
        this.hora = DateFormat.getTimeInstance().format(c.getTime());
        this.data = DateFormat.getDateInstance().format(c.getTime());
        this.assunto = assunto;
        this.tecnico = tecnico;
        this.chamado = chamado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;

/**
 *
 * @author Richard
 */
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    private long numeroContrato;
    private String nomeEmpresa;

    public Empresa(long numeroContrato, String nomeEmpresa) {
        this.numeroContrato = numeroContrato;
        this.nomeEmpresa = nomeEmpresa;
    }

    public long getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(long numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    @Override
    public String toString() {
        return numeroContrato + " - " + nomeEmpresa;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import apresentacao.TelaPrincipal;

/**
 *
 * @author Richard
 */
public class ControladorPrincipal {

    private ControleChamados ctrChamados;
    private ControleClientes ctrClientes;
    private ControleTecnicos ctrTecnicos;
    private ControleEmpresas ctrEmpresa;
    private TelaPrincipal telaPrincipal;

    public ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal(this);
        this.ctrTecnicos = new ControleTecnicos();
        this.ctrChamados = new ControleChamados();
        this.ctrClientes = new ControleClientes();
        this.ctrEmpresa = new ControleEmpresas();
    }
    
    public void start(){
        this.telaPrincipal.setVisible(true);
    }

    public ControleEmpresas getCtrEmpresa() {
        return ctrEmpresa;
    }

    public void setCtrEmpresa(ControleEmpresas ctrEmpresa) {
        this.ctrEmpresa = ctrEmpresa;
    }

    public ControleChamados getCtrChamados() {
        return ctrChamados;
    }

    public void setCtrChamados(ControleChamados ctrChamados) {
        this.ctrChamados = ctrChamados;
    }

    public ControleClientes getCtrClientes() {
        return ctrClientes;
    }

    public void setCtrClientes(ControleClientes ctrClientes) {
        this.ctrClientes = ctrClientes;
    }

    public ControleTecnicos getCtrTecnicos() {
        return ctrTecnicos;
    }

    public void setCtrTecnicos(ControleTecnicos ctrTecnicos) {
        this.ctrTecnicos = ctrTecnicos;
    }

}

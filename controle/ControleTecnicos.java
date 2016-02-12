/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Persistencia.TecnicoDAO;
import apresentacao.TelaCadastroTecnico;
import entidade.Tecnico;

/**
 *
 * @author Richard
 */
public class ControleTecnicos implements IControlador {

    private TelaCadastroTecnico telaCadastroTecnico;
    private TecnicoDAO tecnicoDAO;

    public ControleTecnicos() {

        this.tecnicoDAO = new TecnicoDAO();

    }

    @Override
    public Tecnico inserir(long n, String nome) {
        Tecnico tec = new Tecnico(nome, n);
        tecnicoDAO.put(tec);
        return tec;
    }

    public void cadastrarTecnico() {
        this.telaCadastroTecnico = new TelaCadastroTecnico(this);
        this.telaCadastroTecnico.setVisible(true);
    }

    public void fecharTelaTecnico() {
        this.telaCadastroTecnico.setVisible(false);
    }

}

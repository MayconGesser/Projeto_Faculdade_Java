/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import controle.ControladorPrincipal;
import entidade.Chamado;
import entidade.ClienteEmpresa;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Richard
 */
public class TelaPrincipal extends JFrame {

    private ControladorPrincipal controlador;

    private Container conteiner;

    private JButton btEmpresa;
    private JButton btCliente;
    private JButton btTecnico;
    private JButton btChamado;
    private JButton btAlterarChamado;
    private JButton btRelatorio;
    private GerenciadorEventos gerenciadorEventos;

    public TelaPrincipal(ControladorPrincipal ctr) {
        super("Sistema de chamados - HelpDesk!");

        this.controlador = ctr;
        this.gerenciadorEventos = new GerenciadorEventos();
        this.conteiner = getContentPane();
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializar();
    }

    private void inicializar() {

        conteiner.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        btEmpresa = new JButton("Cadastrar Empresa");
        btEmpresa.setPreferredSize(new Dimension(150, 20));
        btEmpresa.addActionListener(gerenciadorEventos);
        btEmpresa.setActionCommand(btEmpresa.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        conteiner.add(btEmpresa, cts);

        btCliente = new JButton("Cadastrar cliente");
        btCliente.setPreferredSize(new Dimension(150, 20));
        btCliente.addActionListener(gerenciadorEventos);
        btCliente.setActionCommand(btCliente.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        conteiner.add(btCliente, cts);

        btTecnico = new JButton("Cadastrar Tecnico");
        btTecnico.setPreferredSize(new Dimension(150, 20));
        btTecnico.addActionListener(gerenciadorEventos);
        btTecnico.setActionCommand(btTecnico.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        conteiner.add(btTecnico, cts);

        btChamado = new JButton("Cadastrar Chamado");
        btChamado.setPreferredSize(new Dimension(150, 20));
        btChamado.addActionListener(gerenciadorEventos);
        btChamado.setActionCommand(btChamado.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
        conteiner.add(btChamado, cts);

        btAlterarChamado = new JButton("Alterar/Finalizar Chamado");
        btAlterarChamado.setPreferredSize(new Dimension(200, 20));
        btAlterarChamado.addActionListener(gerenciadorEventos);
        btAlterarChamado.setActionCommand(btAlterarChamado.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
        conteiner.add(btAlterarChamado, cts);

        btRelatorio = new JButton("Relatorio");
        btRelatorio.setPreferredSize(new Dimension(150, 20));
        btRelatorio.addActionListener(gerenciadorEventos);
        btRelatorio.setActionCommand(btRelatorio.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 6;
        conteiner.add(btRelatorio, cts);

    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //cadastrar empresa
            if (e.getActionCommand().equals(btEmpresa.getActionCommand())) {
                controlador.getCtrEmpresa().cadastrarEmpresa();
                //cadastrar cliente
            } else if (e.getActionCommand().equals(btCliente.getActionCommand())) {
                controlador.getCtrClientes().cadastrarCliente();
                //cadastrar tecnico
            } else if (e.getActionCommand().equals(btTecnico.getActionCommand())) {
                controlador.getCtrTecnicos().cadastrarTecnico();
            } //cadastrar chamado
            else if (e.getActionCommand().equals(btChamado.getActionCommand())) {
                controlador.getCtrChamados().cadastrarChamado();
            } //alterar chamado ou finalizar
            else if (e.getActionCommand().equals(btAlterarChamado.getActionCommand())) {
                controlador.getCtrChamados().alterarChamado();
            } else if (e.getActionCommand().equals(btRelatorio.getActionCommand())) {
                controlador.getCtrChamados().abrirRelatorio();
            }
        }

    }

}

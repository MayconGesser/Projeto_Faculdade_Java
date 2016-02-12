/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import controle.ControleTecnicos;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Richard
 */
public class TelaCadastroTecnico extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleTecnicos controleTecnicos;
    private Container container;
    private GerenciadorEventos gerenciadorEventos;

    private JButton btSalvar;
    private JButton btCancelar;

    private JLabel lbNome;
    private JLabel lbTelefone;

    private JTextField tfNome;
    private JTextField tfTelefone;

    public TelaCadastroTecnico(ControleTecnicos ctr) {
        super("Cadastro de Tecnico");

        this.gerenciadorEventos = new GerenciadorEventos();
        this.controleTecnicos = ctr;
        setLocationRelativeTo(null);
        setSize(400, 300);
        this.container = getContentPane();
        inicializar();
    }

    private void inicializar() {

        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        lbNome = new JLabel("Nome Tecnico: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbNome, cts);

        tfNome = new JTextField();
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfNome, cts);

        lbTelefone = new JLabel("Telefone: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbTelefone, cts);
        tfTelefone = new JTextField();
        tfTelefone.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfTelefone, cts);

        btSalvar = new JButton("Salvar");
        btSalvar.setPreferredSize(new Dimension(150, 20));
        btSalvar.setActionCommand(btSalvar.getText());
        btSalvar.addActionListener(gerenciadorEventos);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(btSalvar, cts);

        btCancelar = new JButton("Cancelar");
        btCancelar.setPreferredSize(new Dimension(150, 20));
        btCancelar.setActionCommand(btCancelar.getText());
        btCancelar.addActionListener(gerenciadorEventos);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(btCancelar, cts);

    }

    private void limparTela() {

        tfNome.setText("");
        tfTelefone.setText("");
    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSalvar.getActionCommand())) {

                boolean varControle = true;

                Long telefone = new Long(0); //como nao existe telefone 0, nenhum input vai sobrescrever esse valor
                String nome = "";

                try {
                    telefone = Long.parseLong(tfTelefone.getText());
                    nome = tfNome.getText();
                } catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                    varControle = false;
                } finally {
                    if (varControle) {
                        controleTecnicos.inserir(telefone, nome);
                        limparTela();
                        controleTecnicos.fecharTelaTecnico();
                    }
                }

            } else if (e.getActionCommand().equals(btCancelar.getActionCommand())) {
                new TelaCancelar().setVisible(true);
            }
        }

    }
    private class TelaCancelar extends JFrame {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private JLabel mensagem;
        private JButton Sim;
        private JButton Nao;

        public TelaCancelar() {

            Container container = getContentPane();
            container.setLayout(new FlowLayout());
            setSize(300, 125);

            setLocationRelativeTo(null);

            mensagem = new JLabel("Tem certeza que deseja cancelar o Cadastro?");

            Sim = new JButton("Sim");
            Nao = new JButton("Nao");

            Sim.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleTecnicos.fecharTelaTecnico();
                    setVisible(false);
                }
            });

            Nao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            container.add(mensagem);
            container.add(Sim);
            container.add(Nao);

        }

    }
}

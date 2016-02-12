/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import Persistencia.EmpresaDAO;
import controle.ControleClientes;
import entidade.ClienteEmpresa;
import entidade.Empresa;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Richard
 */
public class TelaCadastroCliente extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleClientes ctrCliente;
    private EmpresaDAO empresaDAO;
    private Container container;
    private GerenciadorEventos gerenciadorEventos;

    private JLabel lbEmpresa;
    private JComboBox cbEmpresas;

    private JLabel lbCpf;
    private JTextField tfCpf;
    private JLabel lbNome;
    private JTextField tfNome;
    private JLabel lbTelefone;
    private JTextField tfTelefone;

    private JButton btSalvar;
    private JButton btCancelar;

    public TelaCadastroCliente(ControleClientes ctr) {
        super("Cadastro de Cliente");
        this.gerenciadorEventos = new GerenciadorEventos();
        this.empresaDAO = new EmpresaDAO();
        this.ctrCliente = ctr;
        this.container = getContentPane();

        setLocationRelativeTo(null);
        setSize(400, 300);

        inicializar();
    }

    private void inicializar() {

        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        lbEmpresa = new JLabel("Empresas: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbEmpresa, cts);

        cbEmpresas = new JComboBox();
        cbEmpresas.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = empresaDAO.voltaEmpresa().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbEmpresas.addItem(empresaDAO.voltaEmpresa().get(iterator.next()));
        }
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(cbEmpresas, cts);

        lbCpf = new JLabel("CPF: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbCpf, cts);
        tfCpf = new JTextField();
        tfCpf.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfCpf, cts);

        lbNome = new JLabel("Nome: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbNome, cts);

        tfNome = new JTextField();
        tfNome.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(tfNome, cts);

        lbTelefone = new JLabel("Telefone: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbTelefone, cts);

        tfTelefone = new JTextField();
        tfTelefone.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(tfTelefone, cts);

        btSalvar = new JButton("Salvar");
        btSalvar.setPreferredSize(new Dimension(150, 20));
        btSalvar.setActionCommand(btSalvar.getText());
        btSalvar.addActionListener(gerenciadorEventos);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(btSalvar, cts);

        btCancelar = new JButton("Cancelar");
        btCancelar.setPreferredSize(new Dimension(150, 20));
        btCancelar.setActionCommand(btCancelar.getText());
        btCancelar.addActionListener(gerenciadorEventos);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(btCancelar, cts);

    }

    private void limparTela() {
        tfCpf.setText("");
        tfNome.setText("");
        tfTelefone.setText("");
    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSalvar.getActionCommand())) {

                boolean varControle = true;

                Long CPFvalido = new Long(-1);
                try {
                    CPFvalido = Long.parseLong(tfCpf.getText());
                } catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input no campo CPF; certifique-se que o input contem apenas numeros", "Erro de input", JOptionPane.ERROR_MESSAGE);
                }

                boolean validaCPF = true;

            	//tem q fazer dentro de um if, se fosse fora esse metodo iria executar
                //mesmo depois do catch, com um valor erroneo, e sempre daria errado
                if (CPFvalido != -1) {
                    validaCPF = ctrCliente.getClienteDAO().validarCPF(CPFvalido);
                }

                if (validaCPF) {

                    Long cpf = new Long(0);
                    String nome = "";
                    Long telefone = new Long(0);
                    Empresa emp = null;

                    try {
                        emp = (Empresa) cbEmpresas.getSelectedItem();
                        cpf = Long.parseLong(tfCpf.getText());
                        nome = tfNome.getText();
                        telefone = Long.parseLong(tfTelefone.getText());
                    } catch (NumberFormatException | InputMismatchException ex) {
                        JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                        varControle = false;
                    } finally {
                        if (varControle) {
                            ClienteEmpresa ct = ctrCliente.incluiNovoCliente(emp, cpf, nome, telefone);
                            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso, nome: "
                                    + ct.getNome() + " - CPF: " + ct.getCpf() + " - Empresa: " + ct.getEmpresa());
                            limparTela();
                            ctrCliente.fecharTelaCliente();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "CPF ja esta cadastrado, CPF: " + tfCpf.getText());
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
                    ctrCliente.fecharTelaCliente();
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

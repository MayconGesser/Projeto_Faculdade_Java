package apresentacao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import controle.ControleEmpresas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaCadastroEmpresa extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControleEmpresas controladorEmp;

    //label e preenchimento de Numero de Contrato da Empresa
    private JLabel lbNumContrato;
    private JTextField tfNumContrato;

    //label e preenchimento de Nome da Empresa
    private JLabel lbNomeEmp;
    private JTextField tfNomeEmp;

    //botoes de salvar e cancelar
    private JButton btSalvar;
    private JButton btCancelar;

    private Container container;
    private GerenciadorEventos gerenciadorEventos;

    public TelaCadastroEmpresa(ControleEmpresas controladorEmp) {
        super("Cadastro de Empresa");
        this.controladorEmp = controladorEmp;
        this.gerenciadorEventos = new GerenciadorEventos();
        inicializar();

        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    private void inicializar() {

        container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        //inicializa e adiciona elementos de nome da empresa
        lbNomeEmp = new JLabel();
        lbNomeEmp.setText("Nome da Empresa:");
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbNomeEmp, cts);

        tfNomeEmp = new JTextField();
        tfNomeEmp.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfNomeEmp, cts);

        //inicializa e adiciona elementos de numero de contrato
        lbNumContrato = new JLabel();
        lbNumContrato.setText("Numero de contrato da Empresa:");
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbNumContrato, cts);

        tfNumContrato = new JTextField();
        tfNumContrato.setPreferredSize(new Dimension(200, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfNumContrato, cts);

        //inicializa e adiciona botoes
        btSalvar = new JButton("Salvar");
        btSalvar.setActionCommand(btSalvar.getText());
        btSalvar.addActionListener(gerenciadorEventos);
        btSalvar.setSize(new Dimension(20, 20));
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(btSalvar, cts);

        btCancelar = new JButton("Cancelar");
        btCancelar.setActionCommand(btCancelar.getText());
        btCancelar.addActionListener(gerenciadorEventos);
        btCancelar.setSize(new Dimension(20, 20));
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(btCancelar, cts);
    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //essa variavel nao precisa existir, mas agora deixa
            boolean varControle = true;

            //dar algum valor pq o java espera algum valor para as variaveis
            Long numContrato = Long.MIN_VALUE;
            String nome = null;

            if (e.getActionCommand().equals(btSalvar.getActionCommand())) {
                //pega as informacoes nos campos de texto e cria um novo objeto empresa
                try {
                    numContrato = Long.parseLong(tfNumContrato.getText());
                    nome = tfNomeEmp.getText();

                } catch (InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                    varControle = false;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs est�o nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                    varControle = false;

                } finally {
                    if (varControle) {
                        controladorEmp.inserir(numContrato, nome);
                        controladorEmp.fecharTela();
                    } else {
                        System.out.println("Erro (debug)");
                    }
                }

                //apaga informacoes nos campos (pq seria feio ficar preenchido depois de salvo)
                tfNomeEmp.setText("");
                tfNumContrato.setText("");

//                //print apenas para debugar
//                controladorEmp.printar();
            } else if (e.getActionCommand().equals(btCancelar.getActionCommand())) {
                new TelaCancelar().setVisible(true);
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
                        controladorEmp.fecharTela();
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
}

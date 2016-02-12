package apresentacao;

import Persistencia.TecnicoDAO;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controle.ControleChamados;
import entidade.Chamado;
import entidade.RegistroChamado;
import entidade.Status;
import entidade.Tecnico;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.InputMismatchException;
import java.util.Iterator;
import javax.swing.JComboBox;

public class TelaRegistAcomp extends JFrame {

    private static final long serialVersionUID = 1L;
    private ControleChamados ctrChamados;

    private JLabel lCodCham;
    private JTextField tfCodCham;
    private JTextArea displayCham;
    private JButton btSalvar;
    private JButton btCancelar;
    private Container container;
    private GerenciadorEventos gerenciador;

    private JButton btBuscar;
    private JLabel lbTitulo;
    private JTextField tfTitulo;

    private JLabel lbStatus;
    private JComboBox cbStatus;

    private JLabel lbCausa;
    private JTextField tfCausa;
    private JLabel lbSolucao;
    private JTextField tfSolucao;
    private JLabel lbAssunto;
    private JTextField tfAssunto;

    private JLabel lbTecnico;
    private JComboBox cbTecnico;

    private TecnicoDAO tecnicoDAO;

    public TelaRegistAcomp(ControleChamados ctr) {

        super("Alterar ou finalizar Chamado");
        this.ctrChamados = ctr;
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);
        gerenciador = new GerenciadorEventos();
        this.tecnicoDAO = new TecnicoDAO();

        inicializar();

    }

    private void inicializar() {

        GridBagConstraints cts = new GridBagConstraints();

        lCodCham = new JLabel("Codigo do chamado: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lCodCham, cts);

        tfCodCham = new JTextField();
        tfCodCham.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfCodCham, cts);

        lbTitulo = new JLabel("Titulo: ");
        lbTitulo.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbTitulo, cts);

        tfTitulo = new JTextField();
        cts.fill = GridBagConstraints.HORIZONTAL;
        tfTitulo.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfTitulo, cts);

        lbStatus = new JLabel("Status: ");
        lbStatus.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbStatus, cts);

        cbStatus = new JComboBox();
        cbStatus.addItem("Em atendimento");
        cbStatus.addItem("Esperando resposta do usuario");
        cbStatus.addItem("Encerrado");
        cbStatus.addItem("Sem solucao");
        cbStatus.addActionListener(gerenciador);
        cbStatus.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(cbStatus, cts);

        lbTecnico = new JLabel("Tecnico: ");
        lbTecnico.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbTecnico, cts);

        cbTecnico = new JComboBox();
        cbTecnico.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = tecnicoDAO.voltaCashTecnico().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbTecnico.addItem(tecnicoDAO.voltaCashTecnico().get(iterator.next()));
        }
        cbTecnico.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbTecnico, cts);

        lbAssunto = new JLabel("Assunto Tratado:");
        lbAssunto.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbAssunto, cts);

        tfAssunto = new JTextField();
        tfAssunto.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfAssunto, cts);

        lbCausa = new JLabel("Causa: ");
        lbCausa.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 5;
        container.add(lbCausa, cts);

        tfCausa = new JTextField();
        tfCausa.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 5;
        container.add(tfCausa, cts);

        lbSolucao = new JLabel("Solucao: ");
        lbSolucao.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 6;
        container.add(lbSolucao, cts);

        tfSolucao = new JTextField();
        tfSolucao.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 6;
        container.add(tfSolucao, cts);

        btBuscar = new JButton("Buscar");
        btBuscar.setActionCommand(btBuscar.getText());
        btBuscar.addActionListener(gerenciador);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(btBuscar, cts);

        //botao salvar, instancia e adiciona
        btSalvar = new JButton("Salvar");
        btSalvar.setActionCommand(btSalvar.getText());
        btSalvar.addActionListener(gerenciador);
        btSalvar.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(btSalvar, cts);

        //botao cancelar, instancia e adiciona
        btCancelar = new JButton("Cancelar");
        btCancelar.setActionCommand(btCancelar.getText());
        btCancelar.addActionListener(gerenciador);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 7;
        container.add(btCancelar, cts);
    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSalvar.getActionCommand())) {
            	try{
                Chamado c = ctrChamados.voltaChamadoCodigo(Integer.parseInt(tfCodCham.getText()));
                ctrChamados.alterarChamado(c, (String) cbStatus.getSelectedItem(),
                        tfCausa.getText(), tfSolucao.getText());
                RegistroChamado reg = ctrChamados.inserirRegistroChamado(tfAssunto.getText(), c, (Tecnico) cbTecnico.getSelectedItem());
                ctrChamados.fecharTelaAlteracao();
                System.err.println("Status: " + c.getStatus() + " Causa: " + c.getCausaProblema()
                        + "Solucao: " + c.getSolucaoProblema());
                System.err.println("Assunto: " + reg.getAssunto() + "Tecnico: " + reg.getTecnico().getNome()
                        + " Chamado codigo: " + reg.getChamado().getCodigo());
            	}catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
            	}   

            } else if (e.getActionCommand().equals(btCancelar.getActionCommand())) {
                new TelaCancelar().setVisible(true);
            } else if (e.getActionCommand().equals(btBuscar.getActionCommand())) {
            	Chamado c = null;
            	try{
            		c = ctrChamados.voltaChamadoCodigo(Integer.parseInt(tfCodCham.getText()));
            	}catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
            	}
                if (c != null) {
                    btBuscar.setVisible(false);
                    btSalvar.setVisible(true);
                    lbTitulo.setVisible(true);
                    tfTitulo.setVisible(true);
                    tfTitulo.setText("titulo: " + c.getTitulo());
                    lbTecnico.setVisible(true);
                    cbTecnico.setVisible(true);
                    lbStatus.setVisible(true);
                    cbStatus.setVisible(true);
                    lbAssunto.setVisible(true);
                    tfAssunto.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Chamado com este codigo não existe!");
                }

            } else if (cbStatus.getSelectedItem().equals("Sem solucao")
                    || cbStatus.getSelectedItem().equals("Encerrado")) {
                lbCausa.setVisible(true);
                tfCausa.setVisible(true);
                lbSolucao.setVisible(true);
                tfSolucao.setVisible(true);
            } else if (cbStatus.getSelectedItem().equals("Em atendimento")
                    || cbStatus.getSelectedItem().equals("Esperando resposta do usuario")) {
                lbCausa.setVisible(false);
                tfCausa.setVisible(false);
                lbSolucao.setVisible(false);
                tfSolucao.setVisible(false);
            }

//        @Override
//        public void focusGained(FocusEvent arg0) {
//            // TODO Auto-generated method stub			
//        }
//        @Override
//        public void focusLost(FocusEvent e) {
//            JTextField fonte = (JTextField) e.getSource(); //cast da fonte	
//            int codigo = Integer.MIN_VALUE; //tem q setar um valor pro java nao berrar
//            try {
//                codigo = Integer.parseInt(fonte.getText());
//            } catch (NumberFormatException ex) {
//            };
//
//            if (codigo != Integer.MIN_VALUE) {
//                try {
//                    ctrChamados.setChamadoAlterado(ctrChamados.buscaPeloCodigo(codigo));
//                    displayCham.setText(ctrChamados.retornaDetalhesChamado(ctrChamados.getChamadoAlterado()));
//                } catch (NullPointerException ex) {
//                    JOptionPane.showMessageDialog(null, "Chamado nao encontrado");
//                }
//            }
//        }
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
                    ctrChamados.fecharTelaAlteracao();
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

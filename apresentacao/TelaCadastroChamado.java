/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import Persistencia.ClienteDAO;
import Persistencia.TecnicoDAO;
import controle.ControleChamados;
import controle.ControleClientes;
import entidade.Chamado;
import entidade.ClienteEmpresa;
import entidade.Tecnico;
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
public class TelaCadastroChamado extends JFrame {

    private Container container;
    private ControleChamados ctrChamados;
    private GerenciadorEventos gerenciadorEventos;
    private ControleClientes ctrClientes;

    private JLabel lbTecnico;
    private JComboBox cbTecnico;

    private JLabel lbClienteEmpresa;
    private JComboBox cbCliente;

    private JLabel lbProblema;
    private JComboBox cbProblema;

    private JLabel lbProblemaDesempenho;
    private JTextField tfProblemaDesempenho;
    private JLabel lbDuracao;
    private JTextField tfDuracao;

    private JLabel lbTitulo;
    private JTextField tfTitulo;
    private JLabel lbDescricao;
    private JTextField tfDescricao;
    private JLabel lbPrioridade;
    private JComboBox<String> tfPrioridade;

    private JLabel lbSO;
    private JComboBox cbSO;
    private JLabel lbVersaoSO;
    private JTextField tfVersaoSO;

    private JLabel lbBancoDados;
    private JComboBox cbBancoDados;

    private JLabel lbRede;
    private JComboBox cbRede;
    private JLabel lbEnderecoRede;
    private JTextField tfEnderecoRede;

    private JButton btSalvar;
    private JButton btCancelar;

    private TecnicoDAO tecnicoDAO;
    private ClienteDAO clienteDAO;

    public TelaCadastroChamado(ControleChamados ctr) {

        super("Cadastro de chamado!");

        ctrClientes = new ControleClientes();

        this.tecnicoDAO = new TecnicoDAO();
        this.clienteDAO = new ClienteDAO();

        setSize(800, 400);
        setLocationRelativeTo(null);
        this.container = getContentPane();
        this.ctrChamados = ctr;
        this.gerenciadorEventos = new GerenciadorEventos();

        inicializar();
    }

    private void inicializar() {

        container.setLayout(new GridBagLayout());
        GridBagConstraints cts = new GridBagConstraints();

        lbTecnico = new JLabel("Tecnico: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbTecnico, cts);

        cbTecnico = new JComboBox();
        cbTecnico.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = tecnicoDAO.voltaCashTecnico().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbTecnico.addItem(tecnicoDAO.voltaCashTecnico().get(iterator.next()));
        }
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(cbTecnico, cts);

        lbClienteEmpresa = new JLabel("Cliente: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbClienteEmpresa, cts);

        cbCliente = new JComboBox();
        cbCliente.setPreferredSize(new Dimension(400, 20));
        Iterator iterator1 = clienteDAO.voltaCashCliente().keySet().iterator();
        while (iterator1.hasNext()) {
            this.cbCliente.addItem(clienteDAO.voltaCashCliente().get(iterator1.next()));
        }
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(cbCliente, cts);

        lbProblema = new JLabel("Escolha o problema: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbProblema, cts);

        cbProblema = new JComboBox();
        cbProblema.addItem("Rede");
        cbProblema.addItem("Banco de dados");
        cbProblema.addItem("Desempenho");
        cbProblema.addActionListener(gerenciadorEventos);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(cbProblema, cts);

        lbRede = new JLabel("Tipo Conexao: ");
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbRede, cts);

        cbRede = new JComboBox();
        cbRede.addItem("ADSL");
        cbRede.addItem("Radio");
        cbRede.addItem("Cable Modem");
        cbRede.addItem("Outra");
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbRede, cts);

        lbEnderecoRede = new JLabel("Endereco de Rede: ");
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbEnderecoRede, cts);

        tfEnderecoRede = new JTextField();
        tfEnderecoRede.setPreferredSize(new Dimension(150, 20));
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfEnderecoRede, cts);

        lbBancoDados = new JLabel("Tipo BD");
        lbBancoDados.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbBancoDados, cts);

        cbBancoDados = new JComboBox();
        cbBancoDados.addItem("SQLServer");
        cbBancoDados.addItem("Oracle");
        cbBancoDados.addItem("MySql");
        cbBancoDados.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbBancoDados, cts);

        lbProblemaDesempenho = new JLabel("Informar a operação: ");
        lbProblemaDesempenho.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbProblemaDesempenho, cts);

        tfProblemaDesempenho = new JTextField();
        tfProblemaDesempenho.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(tfProblemaDesempenho, cts);

        lbDuracao = new JLabel("Duracao: ");
        lbDuracao.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbDuracao, cts);

        tfDuracao = new JTextField();
        tfDuracao.setPreferredSize(
                new Dimension(150, 20));
        tfDuracao.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfDuracao, cts);

        lbTitulo = new JLabel("Titulo: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
        container.add(lbTitulo, cts);

        tfTitulo = new JTextField();
        tfTitulo.setPreferredSize(
                new Dimension(300, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 5;
        container.add(tfTitulo, cts);

        lbDescricao = new JLabel("Descricao: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 6;
        container.add(lbDescricao, cts);

        tfDescricao = new JTextField();
        tfDescricao.setPreferredSize(
                new Dimension(300, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 6;
        container.add(tfDescricao, cts);

        lbPrioridade = new JLabel("Prioridade: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(lbPrioridade, cts);

        tfPrioridade = new JComboBox<String>();
        tfPrioridade.setPreferredSize(new Dimension(150, 20));
        tfPrioridade.addItem("Normal");
        tfPrioridade.addItem("Importante");
        tfPrioridade.addItem("Urgente");
        tfPrioridade.addItem("Crítica");        
        tfPrioridade.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 7;
        container.add(tfPrioridade, cts);

        lbSO = new JLabel("Sistema Operacional: ");
        cts.gridx = 0;
        cts.gridy = 8;
        container.add(lbSO, cts);

        cbSO = new JComboBox();
        cbSO.addItem(
                "Windows");
        cbSO.addItem(
                "Linux");
        cts.gridx = 1;
        cts.gridy = 8;
        container.add(cbSO, cts);
        lbVersaoSO = new JLabel("Versão SO: ");
        cts.gridx = 0;
        cts.gridy = 9;

        container.add(lbVersaoSO, cts);
        tfVersaoSO = new JTextField();
        tfVersaoSO.setPreferredSize(
                new Dimension(150, 20));
        cts.gridx = 1;
        cts.gridy = 9;
        container.add(tfVersaoSO, cts);

        btSalvar = new JButton("Salvar");
        cts.fill = GridBagConstraints.HORIZONTAL;
        btSalvar.setActionCommand(btSalvar.getText());
        btSalvar.addActionListener(gerenciadorEventos);
        cts.gridx = 0;
        cts.gridy = 10;

        container.add(btSalvar, cts);
        btCancelar = new JButton("Cancelar");
        cts.fill = GridBagConstraints.HORIZONTAL;
        btCancelar.setActionCommand(btCancelar.getText());
        btCancelar.addActionListener(gerenciadorEventos);
        cts.gridx = 1;
        cts.gridy = 10;
        container.add(btCancelar, cts);

    }

    private class GerenciadorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSalvar.getActionCommand())) {
                
                if (cbProblema.getSelectedItem().equals("Rede")) {
                	
                	String titulo = "";
                	String descricao = "";
                	Tecnico t = null;
                	ClienteEmpresa CE = null;
                	String SO = "";
                	String verSO = "";
                	String tipoRede = "";
                	String endRede = "";
                	
                	int prioridade = 0; 
                	
                	if(tfPrioridade.getSelectedItem().equals("Normal")){
                		prioridade = 1;
                	}else if(tfPrioridade.getSelectedItem().equals("Importante")){
                		prioridade = 2;
                	}else if(tfPrioridade.getSelectedItem().equals("Urgente")){
                		prioridade = 3;
                	}else if(tfPrioridade.getSelectedItem().equals("Crítica")){
                		prioridade = 4;
                	}
                	
                    try{
                    	titulo = tfTitulo.getText();
                    	descricao = tfDescricao.getText();
                    	t = (Tecnico) cbTecnico.getSelectedItem();
                    	CE = (ClienteEmpresa) cbCliente.getSelectedItem();
                    	SO = (String) cbSO.getSelectedItem();
                    	verSO = tfVersaoSO.getText();
                    	tipoRede = (String) cbRede.getSelectedItem();
                    	endRede = tfEnderecoRede.getText();
                    	
                    	Chamado c = ctrChamados.InserirChamadoRede(titulo, descricao, prioridade, t, CE, SO, verSO, tipoRede, endRede);
                    	JOptionPane.showMessageDialog(null, "Codigo chamado criado: " + c.getCodigo());
                    	ctrChamados.fecharTela();
                    	
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                	}
                } else if (cbProblema.getSelectedItem().equals("Desempenho")) {	
                	
                	
                	int prioridade = 0; 
                	
                	if(tfPrioridade.getSelectedItem().equals("Normal")){
                		prioridade = 1;
                	}else if(tfPrioridade.getSelectedItem().equals("Importante")){
                		prioridade = 2;
                	}else if(tfPrioridade.getSelectedItem().equals("Urgente")){
                		prioridade = 3;
                	}else if(tfPrioridade.getSelectedItem().equals("Crítica")){
                		prioridade = 4;
                	}
                	
                    try{
                    Chamado c = ctrChamados.InserirChamadoDesempenho(tfTitulo.getText(), tfDescricao.getText(), prioridade,
                            (Tecnico) cbTecnico.getSelectedItem(), (ClienteEmpresa) cbCliente.getSelectedItem(), (String) cbSO.getSelectedItem(),
                            tfVersaoSO.getText(), tfProblemaDesempenho.getText(), Integer.parseInt(tfDuracao.getText()));
                    JOptionPane.showMessageDialog(null, "Codigo chamado criado: " + c.getCodigo());
                    ctrChamados.fecharTela();
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                	} 
                } else if (cbProblema.getSelectedItem().equals("Banco de dados")) {
                	
                	int prioridade = 0; 
                	
                	if(tfPrioridade.getSelectedItem().equals("Normal")){
                		prioridade = 1;
                	}else if(tfPrioridade.getSelectedItem().equals("Importante")){
                		prioridade = 2;
                	}else if(tfPrioridade.getSelectedItem().equals("Urgente")){
                		prioridade = 3;
                	}else if(tfPrioridade.getSelectedItem().equals("Crítica")){
                		prioridade = 4;
                	}
                	
                    try{
                    Chamado c = ctrChamados.InserirChamadoBancoDeDados(tfTitulo.getText(), tfDescricao.getText(), prioridade,
                            (Tecnico) cbTecnico.getSelectedItem(), (ClienteEmpresa) cbCliente.getSelectedItem(), (String) cbSO.getSelectedItem(),
                            tfVersaoSO.getText(), (String) cbBancoDados.getSelectedItem());
                    		ctrChamados.fecharTela();
                    		JOptionPane.showMessageDialog(null, "Codigo chamado criado: " + c.getCodigo());
                    		
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Erro de input; certifique-se que os inputs estao nos formatos corretos", "Erro de input", JOptionPane.ERROR_MESSAGE);
                	}
                }

            } else if (e.getActionCommand().equals(btCancelar.getActionCommand())) {
                new TelaCancelar().setVisible(true);
            } else if (cbProblema.getSelectedItem().equals("Rede")) {

                lbRede.setVisible(true);
                tfEnderecoRede.setVisible(true);
                cbRede.setVisible(true);
                lbEnderecoRede.setVisible(true);

                lbBancoDados.setVisible(false);
                cbBancoDados.setVisible(false);
                lbDuracao.setVisible(false);
                tfDuracao.setVisible(false);
                lbProblemaDesempenho.setVisible(false);
                tfProblemaDesempenho.setVisible(false);

            } else if (cbProblema.getSelectedItem().equals("Desempenho")) {

                lbDuracao.setVisible(true);
                tfDuracao.setVisible(true);
                lbProblemaDesempenho.setVisible(true);
                tfProblemaDesempenho.setVisible(true);

                lbBancoDados.setVisible(false);
                cbBancoDados.setVisible(false);
                lbRede.setVisible(false);
                tfEnderecoRede.setVisible(false);
                cbRede.setVisible(false);
                lbEnderecoRede.setVisible(false);
            } else if (cbProblema.getSelectedItem().equals("Banco de dados")) {

                lbBancoDados.setVisible(true);
                cbBancoDados.setVisible(true);

                lbDuracao.setVisible(false);
                tfDuracao.setVisible(false);
                lbProblemaDesempenho.setVisible(false);
                tfProblemaDesempenho.setVisible(false);
                lbRede.setVisible(false);
                tfEnderecoRede.setVisible(false);
                cbRede.setVisible(false);
                lbEnderecoRede.setVisible(false);

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
                    ctrChamados.fecharTela();
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

package apresentacao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controle.ControleChamados;

public class TelaRelatorioChamado extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleChamados CC;
	private JLabel lEmitir;	
	private JButton btEmitir;
	private JButton btApagar;
	private JTextArea arearelatorios;
	private JComboBox<String> lstProb;
	private Container container;
	private GE gerenciador;
	
	public TelaRelatorioChamado(ControleChamados c){	
		super("Relatório de chamados encerrados");
		this.CC = c;
		container = getContentPane();
		container.setLayout(new GridBagLayout());		
		setSize(700, 700);
		gerenciador = new GE();
		inicializar();
	}
	
	private void inicializar(){
		
		GridBagConstraints cts = new GridBagConstraints();
				
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
		container.add(new JLabel("Selecione tipo de problema:"), cts);
		
		//instancia, adiciona valores e adiciona combo
		lstProb = new JComboBox<String>();
		lstProb.addItem("Problema de Rede/Conexão");
		lstProb.addItem("Problema de Banco de Dados");
		lstProb.addItem("Problema de desempenho");
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
		container.add(lstProb, cts);
		
		//instancia e adiciona area de texto
		arearelatorios = new JTextArea();
		arearelatorios.setPreferredSize(new Dimension(500, 500));		
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
		container.add(arearelatorios, cts);
		
		//instancia e adiciona botoes
		btApagar = new JButton("Apagar");
		btApagar.setActionCommand(btApagar.getText());
		btApagar.addActionListener(gerenciador);
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
		container.add(btApagar, cts);
		
		btEmitir = new JButton("Emitir relatórios");
		btEmitir.setActionCommand(btEmitir.getText());
		btEmitir.addActionListener(gerenciador);
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
		container.add(btEmitir, cts);
	}
	
	public void incluirTexto(String txt){
		arearelatorios.setText(txt);
	}
	
	private class GE implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btEmitir.getText())){
				String relatorio = CC.emitirRelatorios(lstProb.getSelectedIndex());
				if(relatorio.equals("")){ incluirTexto("Não há chamados deste tipo de problema para exibir");
				}else{
					incluirTexto(CC.emitirRelatorios(lstProb.getSelectedIndex()));
				}
			}
			else if(e.getActionCommand().equals(btApagar.getActionCommand())){
				incluirTexto("");
			}
		}
		
	}
}

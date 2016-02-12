/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Persistencia.ChamadoDAO;
import apresentacao.TelaCadastroChamado;
import apresentacao.TelaRegistAcomp;
import apresentacao.TelaRelatorioChamado;
import entidade.Chamado;
import entidade.ClienteEmpresa;
import entidade.RegistroChamado;
import entidade.Tecnico;
import java.util.Collection;

/**
 *
 * @author Richard
 */
public class ControleChamados {

    private TelaCadastroChamado telaChamado;
    private TelaRegistAcomp telaAlteracaoChamado;
    private ChamadoDAO chamadoDAO;
    private Chamado chamadoalterado;

    public ControleChamados() {

        this.chamadoDAO = new ChamadoDAO();

    }

    public Chamado alterarChamado(Chamado chamado, String status, String causa, String solucao) {

        chamado.setStatus(status);
        chamado.setCausaProblema(causa);
        chamado.setSolucaoProblema(solucao);

        return chamado;
    }

    public Chamado InserirChamadoRede(String titulo, String descricao, int prioridade, Tecnico tecnico, ClienteEmpresa cliente,
            String so, String versaoSO, String conexao, String enderecoRede) {

        Chamado chamado = new Chamado(prioridade, titulo, descricao, prioridade,
                tecnico, cliente, enderecoRede, versaoSO, conexao, enderecoRede);
        chamadoDAO.put(chamado);
        return chamado;
    }

    public Chamado InserirChamadoBancoDeDados(String titulo, String descricao, int prioridade, Tecnico tecnico, ClienteEmpresa cliente,
            String so, String versaoSO, String bancoDeDados) {

        Chamado chamado = new Chamado(titulo, descricao, prioridade, tecnico, cliente, so, versaoSO, bancoDeDados);
        chamadoDAO.put(chamado);
        return chamado;
    }

    public Chamado InserirChamadoDesempenho(String titulo, String descricao, int prioridade, Tecnico tecnico, ClienteEmpresa cliente,
            String so, String versaoSO, String operacao, double tempo) {

        Chamado chamado = new Chamado(titulo, descricao, prioridade, tecnico, cliente, so, versaoSO, operacao, tempo);
        chamadoDAO.put(chamado);
        return chamado;
    }

    public RegistroChamado inserirRegistroChamado(String assunto, Chamado chamado, Tecnico tec) {
        RegistroChamado registro = new RegistroChamado(assunto, chamado, tec);
        chamadoDAO.putRegistro(registro);
        return registro;
    }

    public void cadastrarChamado() {
        this.telaChamado = new TelaCadastroChamado(this);
        this.telaChamado.setVisible(true);

    }

    public void fecharTela() {
        this.telaChamado.setVisible(false);
    }

    public Chamado voltaChamadoCodigo(Integer codigo) {
        return chamadoDAO.get(codigo);

    }

    public void setChamadoAlterado(Chamado c) {
        this.chamadoalterado = c;
    }

    public Chamado getChamadoAlterado() {
        return this.chamadoalterado;
    }

    public String emitirRelatorios(int tipoproblema) {
    	
    	Chamado ref = null;
    	
    	String problema = ""; 
    	
    	switch(tipoproblema){
    		case 0 :
    			problema = "Problema de Rede";
    			break;
    		
    		case 1 :
    			problema = "Banco de Dados";
    			break;
    		
    		case 2 :
    			problema = "Problema de Desempenho";
    			break;
    	}

        Collection<Chamado> chamados = chamadoDAO.getChamados();
        Collection<RegistroChamado> registros = chamadoDAO.getRegistros();

        String relatorio = "";

        for (Chamado c : chamados) {
            if (c.getTipoProblema().equals(problema)) {
            	
            	ref = c;
            	int p = ref.getPrioridade();
            	String prioridade = "";
            	switch(p){
            		case 1 :
            			prioridade = "Normal";
            			break;
            		
            		case 2 :
            			prioridade = "Importante";
            			break;
            			
            		case 3 :
            			prioridade = "Urgente";
            			break;
            			
            		case 4 :
            			prioridade = "Crítica";
            			break;
            	}
                relatorio += "\n" + "--------" + "\nData de abertura do chamado: " +
                    	ref.getData() + 
                    	"\nHorário de abertura do chamado: " + ref.getHora() + 
                    	"\nTítulo do chamado: " + ref.getTitulo() + 
                    	"\nCódigo do chamado: " + ref.getCodigo() +
                    	"\nDescrição do chamado: " + ref.getDescricao() + 
                    	"\nPrioridade do chamado: " + prioridade + 
                    	"\nStatus do chamado: " + ref.getStatus() + 
                    	"\nTipo de problema do chamado: " + ref.getTipoProblema() + 
                    	"\nTécnico responsável pelo chamado: " + ref.getTecnico() + 
                    	"\nCliente requisitor do chamado: " + ref.getCliente() + "\n";
                
                for(RegistroChamado rc : registros){
                	if(rc.getChamado().equals(ref)){
                		relatorio += "\nRelatório de registros de acompanhamento:" + 
                	"\nData: " + rc.getData() + 
                	"\nHora: " + rc.getHora() + 
                	"\nAssunto: " + rc.getAssunto() +
                	"\nTécnico responsável: " + rc.getTecnico().getNome() +
                	"\nCausa do problema: " + ref.getCausaProblema() + 
                	"\nSolução do problema: " + ref.getSolucaoProblema();
                	}
                }
            }
        }

        return relatorio;
    }

    public Chamado buscaPeloCodigo(int codigoChamado) {

        Chamado guardaChamado = null;

        Collection<Chamado> chamados = chamadoDAO.getChamados();

        for (Chamado chamado : chamados) {
            if (codigoChamado == chamado.getCodigo()) {
                guardaChamado = chamado;
            }
        }
        return guardaChamado;
    }

    public String retornaDetalhesChamado(Chamado c) {

        String detalhes = "\n" + "--------" + "\n" + "Data de abertura do chamado: "
                + c.getData() + "\n" + "Hororio de abertura do chamado: " + c.getHora() + "\n"
                + "Titulo do chamado: " + c.getTitulo() + "\n" + "Descriï¿½ï¿½o do chamado: " + c.getDescricao() + "\n"
                + "Prioridade do chamado" + c.getPrioridade() + "\n" + "Status do chamado: " + c.getStatus() + "\n"
                + "Tipo de problema do chamado: " + c.getTipoProblema() + "\n" + "Tecnico responsovel pelo chamado: "
                + c.getTecnico() + "\n" + "Cliente requisitor do chamado: " + c.getCliente() + "\n";

        return detalhes;
    }

    public void alterarChamado() {
        this.telaAlteracaoChamado = new TelaRegistAcomp(this);
        telaAlteracaoChamado.setVisible(true);
    }

    public void abrirRelatorio() {
        new TelaRelatorioChamado(this).setVisible(true);
    }

    public void fecharTelaAlteracao() {
        this.telaAlteracaoChamado.setVisible(false);
    }
    
    public int validarQtdChamados(ClienteEmpresa cliente){
        
        int retorno = 0;
        Collection<Chamado> chamados = chamadoDAO.getChamados();
        
        for (Chamado chamado : chamados) {
            if (cliente == chamado.getCliente()) {
                retorno ++;
            }
        }
        return retorno;
    }

}

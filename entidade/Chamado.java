/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author Richard
 */
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigo;

    private String data;
    private String hora;

    private String titulo;
    private String descricao;
    private int prioridade;
    private String status;
    private String tipoProblema;
    private Tecnico tecnico;
    private ClienteEmpresa cliente;

    //sistema operacional do cliente e versao
    private String sistemaOperacional;
    private String versaoSO;
    //problema de banco de dados
    private String bancoDeDados;
    //quando o chamado for fechado, solucao
    private String causaProblema;
    private String solucaoProblema;
    //problema de rede
    private String tipoConexao;
    private String enderecoRede;
    //problema desempenho
    private String operacao;
    private double duracaoOperacao;

    //construtor para problemaRede
    public Chamado(Integer codigo, String titulo, String descricao, int prioridade, Tecnico tecnico,
            ClienteEmpresa cliente, String sistemaOperacional, String versaoSO, String tipoConexao, String enderecoRede) {

        Calendar c = Calendar.getInstance();
        this.hora = DateFormat.getTimeInstance().format(c.getTime());
        this.data = DateFormat.getDateInstance().format(c.getTime());
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = "Iniciado";
        this.tipoProblema = "Problema de Rede";
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.tipoConexao = tipoConexao;
        this.enderecoRede = enderecoRede;

    }

    //construtor para problemaBancoDeDados
    public Chamado(String titulo, String descricao, int prioridade, Tecnico tecnico,
            ClienteEmpresa cliente, String sistemaOperacional, String versaoSO, String bancoDeDados) {

        Calendar c = Calendar.getInstance();
        this.hora = DateFormat.getTimeInstance().format(c.getTime());
        this.data = DateFormat.getDateInstance().format(c.getTime());
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = "iniciado";
        this.tipoProblema = "Banco de Dados";
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.bancoDeDados = bancoDeDados;
    }

    //construtor para problema de desempenho
    public Chamado(String titulo, String descricao, int prioridade, Tecnico tecnico,
            ClienteEmpresa cliente, String sistemaOperacional, String versaoSO, String operacao, double tempoOperacao) {

        Calendar c = Calendar.getInstance();
        this.hora = DateFormat.getTimeInstance().format(c.getTime());
        this.data = DateFormat.getDateInstance().format(c.getTime());
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = "Iniciado";
        this.tipoProblema = "Problema de Desempenho";
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.operacao = operacao;
        this.duracaoOperacao = tempoOperacao;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public ClienteEmpresa getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEmpresa cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoProblema() {
        return tipoProblema;
    }

    public void setTipoProblema(String tipoProblema) {
        this.tipoProblema = tipoProblema;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getVersaoSO() {
        return versaoSO;
    }

    public void setVersaoSO(String versaoSO) {
        this.versaoSO = versaoSO;
    }

    public String getBancoDeDados() {
        return bancoDeDados;
    }

    public void setBancoDeDados(String bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public String getCausaProblema() {
        return causaProblema;
    }

    public void setCausaProblema(String causaProblema) {
        this.causaProblema = causaProblema;
    }

    public String getSolucaoProblema() {
        return solucaoProblema;
    }

    public void setSolucaoProblema(String solucaoProblema) {
        this.solucaoProblema = solucaoProblema;
    }

    public String getTipoConexao() {
        return tipoConexao;
    }

    public void setTipoConexao(String tipoConexao) {
        this.tipoConexao = tipoConexao;
    }

    public String getEnderecoRede() {
        return enderecoRede;
    }

    public void setEnderecoRede(String enderecoRede) {
        this.enderecoRede = enderecoRede;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getDuracaoOperacao() {
        return duracaoOperacao;
    }

    public void setDuracaoOperacao(double duracaoOperacao) {
        this.duracaoOperacao = duracaoOperacao;
    }
    
    @Override
    public boolean equals(final Object c){
    	Chamado comp = (Chamado) c;   	
    	
    	if(this.getCodigo() != comp.getCodigo()) return false;
    	if(!this.getData().equals(comp.getData())) return false;
    	if(!this.getTitulo().equals(comp.getTitulo())) return false;
    	
    	return true;
    }

}

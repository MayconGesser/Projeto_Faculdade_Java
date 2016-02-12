/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Persistencia.EmpresaDAO;
import entidade.Empresa;

import java.util.ArrayList;
import java.util.Collection;

import apresentacao.TelaCadastroEmpresa;
import javax.swing.JOptionPane;

public class ControleEmpresas implements IControlador {

    private EmpresaDAO mapeadorEmpresa;
    private TelaCadastroEmpresa telaEmpresa;
    private Collection<Empresa> empresas;

    public ControleEmpresas() {
        this.mapeadorEmpresa = new EmpresaDAO();
        //fiz isso soh pra nao quebrar os metodos dessa classe por enquanto, sei q
        //nao deveria ficar aqui
        this.empresas = mapeadorEmpresa.getEmpresas();
        this.telaEmpresa = new TelaCadastroEmpresa(this);
    }

    public Empresa retorna(long nmr, String nome) {
        Empresa retorno = null;
        for (Empresa empresa : empresas) {
            if (empresa.getNomeEmpresa().equals(nome) && empresa.getNumeroContrato() == nmr) {
                retorno = empresa;
            }
        }
        return retorno;
    }

        public int validar(long contrato, String nome) {
    	
    	this.empresas = mapeadorEmpresa.getEmpresas();
    	
        int retorno = 0;
        
        for (Empresa empresa : empresas) {
        	//1 o contrato e o nome da empresa ja estao em uso
            if (empresa.getNumeroContrato() == contrato && empresa.getNomeEmpresa().equals(nome)) {
                retorno = 1;
            } 
            //2 o nome da empresa ja esta em uso
            else if (empresa.getNumeroContrato() != contrato && empresa.getNomeEmpresa().equals(nome)) {
                retorno = 2;
            } 
            //3 o numero de contrato da empresa ja esta em uso
            else if(empresa.getNumeroContrato() == contrato && !empresa.getNomeEmpresa().equals(nome)){
                retorno = 3;
            }
            //4 nenhum atributo da empresa esta em uso
            else if(empresa.getNumeroContrato() != contrato && !empresa.getNomeEmpresa().equals(nome)){
            	retorno = 4;
            }
        }
        return retorno;
    }

    @Override
    public Empresa inserir(long n, String nome) {
    	
    	Long num = new Long(n);
    	boolean opcao = checar(num, nome);    	
    	this.empresas = mapeadorEmpresa.getEmpresas();
    	
    	if(!opcao){
	    	Empresa empresa = new Empresa(n, nome);
	    	mapeadorEmpresa.put(empresa);
	    	return empresa;
    	}
    	
    	JOptionPane.showMessageDialog(null, "Empresa ja cadastrada");
    	return null;
    }

//    public void printar() {
//        mapeadorEmpresa.printar();
//   }
    
      public boolean checar(Long num, String nome){
    	empresas = mapeadorEmpresa.getEmpresas();
    	
    	for(Empresa e : empresas){
    		if(e.getNomeEmpresa().equals(nome) || e.getNumeroContrato() == num){
    			return true;
    		}    		
    	}
    	
    	return false;
    }

    public void cadastrarEmpresa() {
        this.telaEmpresa.setVisible(true);
    }

    public void fecharTela() {
        this.telaEmpresa.setVisible(false);
    }
}

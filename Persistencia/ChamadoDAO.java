/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Chamado;
import entidade.RegistroChamado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Richard
 */
public class ChamadoDAO {

    private HashMap<Integer, Chamado> cashChamado;
    private HashMap<Integer, RegistroChamado> cashRegistroChamado;
    private static final String nomeArquivo = "chamados.dat";
    private static final String nomeArquivoRegistro = "registroChamados.dat";

    public ChamadoDAO() {
        cashChamado = new HashMap<>();
        cashRegistroChamado = new HashMap<>();
        load();
    }

    public int gerarCodigo() {
        return cashChamado.keySet().size() + 1;
    }

    public int gerarCodigoRegistroChamado() {
        return cashRegistroChamado.keySet().size() + 1;
    }

    public void put(Chamado chamado) {
        chamado.setCodigo(gerarCodigo());
        cashChamado.put(chamado.getCodigo(), chamado);
        persit();
    }

    public void putRegistro(RegistroChamado registroChamado) {
        
        cashRegistroChamado.put(registroChamado.getCodigo(), registroChamado);
        persit();
    }

    public Chamado get(Integer codigoChamado) {
        return cashChamado.get(codigoChamado);
    }

    private void load() {

        try {
            //chamado
            FileInputStream fis = new FileInputStream(nomeArquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            cashChamado = (HashMap<Integer, Chamado>) ois.readObject();
            ois.close();
            fis.close();

            //registro  chamado
            FileInputStream fisR = new FileInputStream(nomeArquivoRegistro);
            ObjectInputStream oisR = new ObjectInputStream(fisR);
            cashRegistroChamado = (HashMap<Integer, RegistroChamado>) oisR.readObject();
            oisR.close();
            fisR.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo " + nomeArquivo);
            System.err.println("Erro ao abrir o arquivo " + nomeArquivoRegistro);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entrada ou saida de dados " + nomeArquivo);
            System.err.println("Erro de entrada ou saida de dados " + nomeArquivoRegistro);
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao processar registros dos arquivos " + nomeArquivo);
            System.err.println("Erro ao processar registros dos arquivos " + nomeArquivoRegistro);
            System.err.println(ex.getMessage());
        }
    }

    public void persit() {

        try {
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cashChamado);
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();

            FileOutputStream fosR = new FileOutputStream(nomeArquivoRegistro);
            ObjectOutputStream oosR = new ObjectOutputStream(fosR);
            oosR.writeObject(cashRegistroChamado);
            oosR.flush();
            fosR.flush();
            oosR.close();
            fosR.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo não encontrado " + nomeArquivo);
            System.err.println("Arquivo não encontrado " + nomeArquivoRegistro);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro na entrada e saida de dados " + nomeArquivo);
            System.err.println("Erro na entrada e saida de dados " + nomeArquivoRegistro);
            System.err.println(ex.getMessage());
        }

    }

    public Collection<Chamado> getChamados() {
        return cashChamado.values();
    }
    
    public Collection<RegistroChamado> getRegistros(){
    	return cashRegistroChamado.values();
    }
}

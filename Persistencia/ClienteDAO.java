/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.ClienteEmpresa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Richard
 */
public class ClienteDAO {

    private HashMap<Long, ClienteEmpresa> cashClientes;
    private static final String nomeArquivo = "clientes.dat";

    public ClienteDAO() {
        this.cashClientes = new HashMap<>();
        load();
    }

    public boolean validarCPF(long cpf) {
        
        boolean retorno = false;
        
        ClienteEmpresa c = cashClientes.get(cpf);
        if (c == null) {
            retorno = true;
        }
        return retorno;
    }

    public int gerarCodigo() {
        return cashClientes.keySet().size() + 1;
    }

    public HashMap<Long, ClienteEmpresa> voltaCashCliente() {
        return cashClientes;

    }

    public void put(ClienteEmpresa cliente) {
        cliente.setCodigo(gerarCodigo());
        cashClientes.put(cliente.getCpf(), cliente);
        persit();
    }

    public ClienteEmpresa get(Long codigoCliente) {
        return cashClientes.get(codigoCliente);
    }

    private void load() {

        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            cashClientes = (HashMap<Long, ClienteEmpresa>) ois.readObject();

            ois.close();
            fis.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo " + nomeArquivo);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entrada ou saida de dados " + nomeArquivo);
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao processar registros dos arquivos " + nomeArquivo);
            System.err.println(ex.getMessage());
        }
    }

    public void persit() {

        try {
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(cashClientes);

            oos.flush();
            fos.flush();

            oos.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo não encontrado " + nomeArquivo);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro na entrada e saida de dados " + nomeArquivo);
            System.err.println(ex.getMessage());
        }

    }
}

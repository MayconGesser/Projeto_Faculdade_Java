/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import entidade.Tecnico;
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
public class TecnicoDAO {

    private HashMap<Integer, Tecnico> cashTecnicos;
    private static final String nomearq = "tecnicos.dat";

    public TecnicoDAO() {
        this.cashTecnicos = new HashMap<>();
        load();
    }

    public int gerarCodigo() {
        return cashTecnicos.keySet().size() + 1;
    }

    public HashMap<Integer, Tecnico> voltaCashTecnico() {
        return cashTecnicos;

    }

    public void put(Tecnico tecnico) {

        cashTecnicos.put(gerarCodigo(), tecnico);
        persit();
    }

    public Tecnico get(Integer codigo) {
        return cashTecnicos.get(codigo);
    }

    private void load() {

        try {
            FileInputStream fis = new FileInputStream(nomearq);
            ObjectInputStream ois = new ObjectInputStream(fis);

            cashTecnicos = (HashMap<Integer, Tecnico>) ois.readObject();

            ois.close();
            fis.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo " + nomearq);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entrada ou saida de dados " + nomearq);
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao processar registros dos arquivos " + nomearq);
            System.err.println(ex.getMessage());
        }
    }

    public void persit() {

        try {
            FileOutputStream fos = new FileOutputStream(nomearq);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(cashTecnicos);

            oos.flush();
            fos.flush();

            oos.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo não encontrado " + nomearq);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro na entrada e saida de dados " + nomearq);
            System.err.println(ex.getMessage());
        }

    }

}

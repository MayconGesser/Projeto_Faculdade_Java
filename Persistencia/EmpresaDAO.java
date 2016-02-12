package Persistencia;

import entidade.Empresa;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

//vou deixar para instanciar os streams dentro dos metodos pq cuida do problema
//do open/close
public class EmpresaDAO {

    private HashMap<Long, Empresa> cacheEmpresas;
    private Set<Long> chaves;

    private static final String nomearq = "empresas.dat";

    public EmpresaDAO() {
        cacheEmpresas = new HashMap<>();
        load();
        //nao vou instanciar os streams no construtor pois os metodos cuidarao disso
    }

    public void put(Empresa ep) {
        //sei q a chave nao deveria ser essa, por enquanto fica assim
        cacheEmpresas.put(ep.getNumeroContrato(), ep);
        persit();
    }

    private void load() {

        try {
            FileInputStream fis = new FileInputStream(nomearq);
            ObjectInputStream ois = new ObjectInputStream(fis);

            cacheEmpresas = (HashMap<Long, Empresa>) ois.readObject();

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

            oos.writeObject(cacheEmpresas);

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

    //para uso da classe ControleEmpresas

    public Collection<Empresa> getEmpresas() {
        return cacheEmpresas.values();
    }

//    public void printar() {
//        carregar();
//        Collection<Empresa> col = cacheEmpresas.values();
//
//        for (Empresa e : col) {
//            System.out.println("Nome: " + e.getNomeEmpresa());
//            System.out.println("Num. Contrato: " + e.getNumeroContrato());
//        }
//    }
    public HashMap<Long, Empresa> voltaEmpresa() {
        
        return this.cacheEmpresas;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Persistencia.ClienteDAO;
import apresentacao.TelaCadastroCliente;
import entidade.ClienteEmpresa;
import entidade.Empresa;

/**
 *
 * @author Richard
 */
public class ControleClientes {

    private TelaCadastroCliente telaCliente;
    private ClienteDAO clienteDAO;

    public ControleClientes() {
        this.clienteDAO = new ClienteDAO();
         
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void cadastrarCliente() {
        
        this.telaCliente = new TelaCadastroCliente(this);
        telaCliente.setVisible(true);
    }

    public ClienteEmpresa incluiNovoCliente(Empresa empresa, long cpf, String nome, long telefone) {

        ClienteEmpresa cliente = new ClienteEmpresa(1, empresa, cpf, nome, telefone);
        clienteDAO.put(cliente);
        return cliente;
    }

//    public ClienteEmpresa inserir(Empresa empresa, long cpf, String nome, long telefone) {
//        ClienteEmpresa cliente = new ClienteEmpresa(empresa, cpf, nome, telefone);
//        clientes.add(cliente);
//        return cliente;
//    }
//    //valida o a empresa e o cliente empresa informado na hora de começar a inserção de um chamado
//    public boolean validarCliente(int nmrContrato, String nomeEmpresa, long cpf, String nomeUsuario, long telefone) {
//
//        boolean retorno = false;
//        for (ClienteEmpresa clienteEmpresa : clientes) {
//            if (clienteEmpresa.getEmpresa().getNumeroContrato() == nmrContrato
//                    && clienteEmpresa.getEmpresa().getNomeEmpresa().endsWith(nomeEmpresa)
//                    && clienteEmpresa.getCpf() == cpf
//                    && clienteEmpresa.getNome().equals(nomeUsuario) && clienteEmpresa.getTelefone() == telefone) {
//                retorno = true;
//            }
//        }
//        return retorno;
//    }
//
//    public ClienteEmpresa retornaCliente(long nmrContrato, String nomeEmpresa, long cpf, String nomeUsuario, long telefone) {
//
//        ClienteEmpresa retorno = null;
//        for (ClienteEmpresa clienteEmpresa : clientes) {
//            if (clienteEmpresa.getEmpresa().getNumeroContrato() == nmrContrato
//                    && clienteEmpresa.getEmpresa().getNomeEmpresa().equals(nomeEmpresa)
//                    && clienteEmpresa.getCpf() == cpf
//                    && clienteEmpresa.getNome().equals(nomeUsuario) && clienteEmpresa.getTelefone() == telefone) {
//                return clienteEmpresa;
//            }
//
//        }
//        return retorno;
//    }
    public void fecharTelaCliente() {
        this.telaCliente.setVisible(false);
    }

}

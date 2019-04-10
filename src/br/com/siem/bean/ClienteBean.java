package br.com.siem.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.siem.dao.ClienteDAO;
import br.com.siem.domain.Cliente;
import br.com.siem.util.JSFUtil;

//essa não é uma classe java comum pra isso eu preciso colocar suas anotações.
//uma anotação (@<anotação> )é uma for de configurar uma classe java
//eu vou configurar essa classe pra dizer que ela é uma manage bean

@ManagedBean(name = "MBCliente") // vou usar o manage bean do javax.faces.bean
//MBCliente é o nome onde o .xhtml vai descobrir o nome da classe (linha 13)
@ViewScoped // agora eu vou configurar o escopo da mandage bean: request, view e session
public class ClienteBean {

	// Para salvar eu vou precisar de uma variável para salvar os dados
	// essa variável vai servir para salvar, editar e excluir um cliente
	private Cliente cliente; // atenção! cliente é diferente de clientes!
	private ArrayList<Cliente> clientes; //lista de clientes
	private ArrayList<Cliente> clientesFiltrados; //lista de clientes filtrados na tela
	
	// como eu preciso pegar dados e passar dados, preciso de gets e sets...
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(ArrayList<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	@PostConstruct // roda antes de exibir os dados na tela!
	public void preparaPesquisa() {
		try {
			ClienteDAO cdao = new ClienteDAO();
			clientes = cdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void prepararNovoCliente() {
		cliente = new Cliente();
	}

	public void novo() {
		try {
			ClienteDAO cdao = new ClienteDAO();
			cdao.salvar(cliente);
			clientes = cdao.listar();
			JSFUtil.adicionarMensagemSucesso("Muito bem! O cliente " + cliente.getNome() + " foi adicionado com sucesso em meu sistema!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Ocorreu um erro ao cadastrar " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			
			ClienteDAO cdao = new ClienteDAO();
			cdao.editar(cliente);
			clientes = cdao.listar();
			JSFUtil.adicionarMensagemSucesso("Cadastro atualizado.");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Houve um erro na atualização - "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			
			ClienteDAO cdao = new ClienteDAO();
			cdao.excluir(cliente);
			clientes = cdao.listar();
			JSFUtil.adicionarMensagemSucesso("Os dados do cliente: " + cliente.getNome() + " foram deletados com sucesso.");
		
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Houve um erro ao tentar deletar os dados - " + e.getMessage());
			e.printStackTrace();
		}
	}
}

package br.com.siem.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.siem.dao.ClienteDAO;
import br.com.siem.domain.Cliente;

//essa não é uma classe java comum pra isso eu preciso colocar suas anotações.
//uma anotação (@<anotação> )é uma for de configurar uma classe java
//eu vou configurar essa classe pra dizer que ela é uma manage bean

@ManagedBean(name = "MBCliente") // vou usar o manage bean do javax.faces.bean
//MBCliente é o nome onde o .xhtml vai descobrir o nome da classe (linha 13)
@ViewScoped // agora eu vou configurar o escopo da mandage bean: request, view e session
public class ClienteBean {

	private ListDataModel<Cliente> clientes;
	// Para salvar eu vou precisar de uma variável para salvar os dados
	// essa variável vai servir para salvar, editar e excluir um cliente
	private Cliente cliente; // atenção! cliente é diferente de clientes!

	// como eu preciso pegar dados e passar dados, preciso de gets e sets...
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}

	@PostConstruct // roda antes de exibir os dados na tela!
	public void proparaPesquisa() {
		try {
			ClienteDAO cdao = new ClienteDAO();
			ArrayList<Cliente> lista = cdao.listar();
			clientes = new ListDataModel<Cliente>(lista);
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
			
			ArrayList<Cliente> lista = cdao.listar();
			clientes = new ListDataModel<Cliente>(lista);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




















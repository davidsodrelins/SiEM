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

@ManagedBean(name = "MBCliente") //vou usar o manage bean do javax.faces.bean
//MBCliente é o nome onde o .xhtml vai descobrir o nome da classe (linha 13)
@ViewScoped  //agora eu vou configurar o escopo da mandage bean: request, view e session
public class ClienteBean {
	
	private ListDataModel<Cliente> clientes;

	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	@PostConstruct //roda antes de exibir os dados na tela!
	public void proparaPesquisa() {
		try {
			ClienteDAO cdao = new ClienteDAO();
			ArrayList<Cliente> lista = cdao.listar();
			clientes = new ListDataModel<Cliente>(lista);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}

/*
 * Classe de conexão da classe cliente com o banco de dados
 */
package br.com.siem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.siem.domain.Cliente;
import br.com.siem.factory.ConexaoFactory;

public class ClienteDAO {

	public void salvar(Cliente c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("CALL PROC_IN_CLIENT(?,?,? )");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, c.getNome());
		comando.setString(2, c.getEmail());
		comando.setString(3, c.getTelefone());

		comando.executeUpdate();
	}

	public void excluir(Cliente c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("CALL PROC_DEL_CLIENT(?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, c.getId_cliente());

		comando.executeUpdate();
	}

	public void editar(Cliente c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("CALL PROC_UP_CLIENT(?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, c.getNome());
		comando.setString(2, c.getEmail());
		comando.setString(3, c.getTelefone());
		comando.setLong(4, c.getId_cliente());

		comando.executeUpdate();
	}

	public Cliente buscarPorCodigo(Cliente c) throws SQLException {

		

		Cliente pesquisa = null;

		
		return pesquisa;
	}

	public ArrayList<Cliente> listar() throws SQLException {


		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		return listaClientes; //retorna a lista de clientes
	}

	public static void main(String[] args) {

		// Teste de busca por codigo
		System.out.println("Busca por Código (2 e 3)");

		Cliente c1 = new Cliente();
		c1.setId_cliente(2L);

		Cliente c2 = new Cliente();
		c2.setId_cliente(3L);

		ClienteDAO cdao = new ClienteDAO();

		try {
			Cliente c3 = cdao.buscarPorCodigo(c1);
			Cliente c4 = cdao.buscarPorCodigo(c2);
			System.out.println(c3);
			System.out.println(c4);

		} catch (SQLException e) {
			System.out.println("Erro na busca");
			e.printStackTrace();
		}

		// teste de listagem
		System.out.println("Listar todos os Clientes");

		try {
			ArrayList<Cliente> lista = cdao.listar(); // tenho que adicionar meu retorno na lista

			for (Cliente clienteLista : lista) {
				System.out.println(clienteLista);
			}
		} catch (SQLException e) {
			System.out.println("Erro na listagem");
			e.printStackTrace();
		}

//		Cliente c1 = new Cliente();

//		c1.setNome("reste");
//		c1.setEmail("test@hotmail.com");
//		c1.setTelefone("rrr");
//		c1.setId_cliente(2L);
//
//		ClienteDAO cdao = new ClienteDAO();
//
//		try {
//			cdao.editar(c1);
//			System.out.println("Cliente editado!! AAewwwww");
//
//		} catch (SQLException e) {
//			System.out.println("Erro ao editar o cliente, verique o log abaixo.");
//			e.printStackTrace();
//		}
//
//		

	}

}

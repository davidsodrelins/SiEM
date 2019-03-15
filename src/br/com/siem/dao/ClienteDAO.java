/*
 * Classe de conexão da classe cliente com o banco de dados
 */
package br.com.siem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		sql.append("delete from cliente ");
		sql.append("where id _cliente = ");
		sql.append("? ");

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
	
	
	public static void main(String[] args) {

		Cliente c1 = new Cliente();
		
		c1.setNome("reste");
		c1.setEmail("test@hotmail.com");
		c1.setTelefone("rrr");
		c1.setId_cliente(2L);

		ClienteDAO cdao = new ClienteDAO();

		try {
			cdao.editar(c1);
			System.out.println("Cliente editado!! AAewwwww");

		} catch (SQLException e) {
			System.out.println("Erro ao editar o cliente, verique o log abaixo.");
			e.printStackTrace();
		}

		
		
	}

}

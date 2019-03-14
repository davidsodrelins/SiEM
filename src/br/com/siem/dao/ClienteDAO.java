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
		sql.append("INSERT INTO cliente ");
		sql.append("(nome, email, telefone) ");
		sql.append(" VALUES (?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, c.getNome());
		comando.setString(2, c.getEmail());
		comando.setString(3, c.getTelefone());

		comando.executeUpdate();
	}

//	public static void main(String[] args) {
//
//		Cliente c1 = new Cliente();
//		
//		c1.setNome("George");
//		c1.setEmail("george@hotmail.com");
//		c1.setTelefone("71992032424");
//
//		ClienteDAO cdao = new ClienteDAO();
//
//		try {
//			cdao.salvar(c1);
//			System.out.println("Cliente Salvo!! AAewwwww");
//
//		} catch (SQLException e) {
//			System.out.println("Erro ao Salvar o cliente, verique o log abaixo.");
//			e.printStackTrace();
//		}
//
//	}

}

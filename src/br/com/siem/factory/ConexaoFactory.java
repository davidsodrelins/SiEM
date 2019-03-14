/*
 * Esta classe eh responsavel por abrir a conexao com o banco de dados.
 */

package br.com.siem.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";

	public static Connection conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

//	public static void main(String[] args) {
//		try {
//			Connection conexao = ConexaoFactory.conectar();
//			System.out.println("Conectado ao banco de dados com Sucesso!! AAewwwww");
//		} catch (SQLException e) {
//			System.out.println("Deu ruim! Algo de errado não está certo... Olha abaixo o log!");
//			e.printStackTrace();
//		}
//	}
}

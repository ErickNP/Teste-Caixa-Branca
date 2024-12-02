package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A classe User é responsável por criar uma conexão com o banco de dados MySQL
 * e também por realizar validação através do login e senha dos usuários.
 */
public class User {

	/**
	 * Estabelece uma conexão com o banco de dados MySQL.
	 * 
	 * @return Retorna a conexão com o banco de dados ou null em caso de falha.
	 */
	public Connection conectarBD() {
		Connection conn = null;
		try {
			// Carrega o driver do MySQL
			Class.forName("com.mysql.Driver.Manager").newInstance();
			
			// Estabelece a conexão com o banco de dados MySQL.
			String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
			conn = DriverManager.getConnection(url);
			
		} catch (Exception e) {
			// Tratar erros de conexão, atualmente não implementado.
		}
		return conn;
	}

	/**
	 * Nome do usuário.
	 */
	public String nome = "";

	/**
	 * Resultado da função 'verificarUsuario'. 
	 * Retorna true se o usuário for encontrado, false caso contrário.
	 */
	public boolean result = false;

	/**
	 * Verifica se o usuário existe no banco de dados com o login e senha
	 * fornecidos.
	 * 
	 * @param login O login do usuário.
	 * @param senha A senha do usuário.
	 * @return Retorna true se o usuário for encontrado, caso contrário retorna
	 *         false.
	 */
	public boolean verificarUsuario(String login, String senha) {
		String sql = "";

		// Estabelece a conexão com o banco de dados MySQL.
		Connection conn = conectarBD();

		// Cria a instrução SQL para verificar os dados do usuário.
		sql += "select nome from usuarios ";
		sql += "where login = " + "'" + login + "'";
		sql += "  and senha = " + "'" + senha + "';";

		try {
			// Executa a consulta ao MySQL.
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// Identifica se foi encontrado um resultado na busca.
			if (rs.next()) {
				result = true; // Usuário encontrado.
				nome = rs.getString("nome");
			}
		} catch (Exception e) {
			// Tratar erros de consulta, atualmente não implementado.
		}
		return result; // Retorna o resultado da verificação, retorna false por padrão.
	}
} // Fim da class
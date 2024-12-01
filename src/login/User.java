package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	public Connection conectarBD() {
		Connection conn = null; /*Nó 1*/
		try { //Nó 2
			Class.forName("com.mysql.Driver.Manager").newInstance();
			String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
			conn = DriverManager.getConnection(url);
			/*Nó 3*/ } /*Nó 4*/catch (Exception e) { /*Nó 5*/}
		return conn;/*Nó 6*/}
	
	public String nome=""; /*Nó 7*/
	public boolean result = false; /*Nó 8*/
	
	public boolean verificarUsuario (String login, String senha) {
		String sql = "";
		Connection conn = conectarBD(); /*Nó 9*/
		//INSTRUÇÃO SQL
		sql += "select nome from usuarios ";
		sql += "where login = " + "'" + login + "'";
		sql += "  and senha = " + "'" + senha + "';";
		try { /*Nó 10*/
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) { /*Nó 11*/
				result = true;
				nome = rs.getString("nome"); /*Nó 12*/}
			/*Nó 13*/} /*Nó 14*/catch (Exception e) {/*Nó 15*/}
		return result; /*Nó 16*/}
	}//fim da class
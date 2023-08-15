package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
		private static Connection conexao;
		private static Conexao instancia;
		private static final String DATABASE = "rinha_de_galo";
		private static final String USER     = "root";
		private static final String PSW      = "aluno";
		private static final String URL      = "jdbc:mysql://localhost/";
		
		
		
		public Conexao() {}
		
		public static Conexao getInstancia() {
			if (instancia == null) { 
				instancia = new Conexao(); 
			}
			return instancia;	
		}
		
		public static Connection conectar() {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/"+ DATABASE + "?serverTimezone=UTC", USER, PSW);
			} catch (Exception e) { 
				e.printStackTrace(); 
				
			}  
			
			return conexao;		
		}
		
		public boolean fecharConexao() { 
			try { 
				conexao.close(); 
			} 
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

			return true;
		}
}
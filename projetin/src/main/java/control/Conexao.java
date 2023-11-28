package control;
/* 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
		private static Connection conexao;
		private static Conexao instancia;
		private static final String DATABASE = "rinha_de_galo";
		private static final String USER     = "root";
		private static final String PSW      = "123456789";
		private static final String URL = "jdbc:mysql://localhost/";

		
		
		
		public Conexao() {}
		
		public static Conexao getInstancia() {
			if (instancia == null) { 
				instancia = new Conexao(); 
			}
			return instancia;	
		}
		
		public Connection conectar() {
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
}*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao implements AutoCloseable {
    private Connection conexao;
    private static Conexao instancia;

    private static final String DATABASE = "rinha_de_galo";
    private static final String USER = "caio";
    private static final String HOST = "rinha-de-galo99-do-user-14972645-0.c.db.ondigitalocean.com";
    private static final int PORTA = 25060;
    private static final String PSW = "AVNS_hUdV2AgeE1JtSjVA3s6";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + DATABASE + "?serverTimezone=UTC";

    private Conexao() {
    }

    public static Conexao getInstancia() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }

    public Connection conectar() {
        try {
            conexao = DriverManager.getConnection(URL, USER, PSW);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexao;
    }

    @Override
    public void close() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

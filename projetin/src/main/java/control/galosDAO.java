package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Galos;

public class galosDAO {
	
	public boolean inserir(Galos x) {
		
		Conexao c = Conexao.getInstancia();
		
		Connection cn = c.conectar();
		
		String query = "INSERT INTO `galos` (`id_galo`, `raca_galo`, `poder_de_combate`) VALUES (?, ?, ?)";
				
		
		try {
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setInt(1, x.getIdGalo());
			ps.setString(2,  x.getRaca());
			ps.setInt(1, x.getPower());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
		
		
	}
	
	
	
}
